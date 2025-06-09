package com.vishalrajput.service1.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.AESEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.vishalrajput.service1.constant.SecurityContant;
import com.vishalrajput.service1.dto.CryptoPayloadRequest;
import com.vishalrajput.service1.dto.CryptoPayloadResponse;
import com.vishalrajput.service1.dto.DecryptRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class CryptoService {

    public static final String EFIELDS_KEY = "efields";

    public CryptoPayloadResponse encrypt(CryptoPayloadRequest request) throws Exception {
        Map<String, Object> jsonPayload = new HashMap<>();
        jsonPayload.put(EFIELDS_KEY, request.getEfields());

        String efieldsRaw = JSONObjectUtils.toJSONString(request.getEfields());

        // JWE Encryption
        JWEHeader header = new JWEHeader(JWEAlgorithm.A256KW, EncryptionMethod.A128CBC_HS256);
        JWEObject jwe = new JWEObject(header, new Payload(efieldsRaw));
        AESEncrypter encrypter = new AESEncrypter(SecurityContant.AES_KEY.getBytes(StandardCharsets.UTF_8));
        jwe.encrypt(encrypter);

        String encryptedPayload = jwe.serialize();
        jsonPayload.put(EFIELDS_KEY, encryptedPayload);

        // HMAC Signing
        JWSSigner signer = new MACSigner(SecurityContant.HAMC_KEY.getBytes(StandardCharsets.UTF_8));
        JWSObject jws = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(JSONObjectUtils.toJSONString(jsonPayload)));
        jws.sign(signer);
        String jwtToken = jws.serialize();

        String[] chunks = jwtToken.split("\\.");
        String xSignature = chunks[0] + ".." + chunks[2];

        return new CryptoPayloadResponse(jwtToken, xSignature, JSONObjectUtils.toJSONString(jsonPayload));
    }

    public CryptoPayloadRequest decrypt(DecryptRequest request) throws Exception {
        JWEObject jwe = JWEObject.parse(request.getEfields());
        jwe.decrypt(new AESDecrypter(SecurityContant.AES_KEY.getBytes(StandardCharsets.UTF_8)));

        Map<String, Object> decryptedMap = JSONObjectUtils.parse(jwe.getPayload().toString());
        return new CryptoPayloadRequest(decryptedMap);
    }
}