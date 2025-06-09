package com.vishalrajput.service1.controller;

import com.vishalrajput.service1.dto.CryptoPayloadRequest;
import com.vishalrajput.service1.dto.CryptoPayloadResponse;
import com.vishalrajput.service1.dto.DecryptRequest;
import com.vishalrajput.service1.services.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @PostMapping("/encrypt")
    public ResponseEntity<CryptoPayloadResponse> encrypt(@RequestBody CryptoPayloadRequest payload) throws Exception {
        return ResponseEntity.ok(cryptoService.encrypt(payload));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<CryptoPayloadRequest> decrypt(@RequestBody DecryptRequest payload) throws Exception {
        return ResponseEntity.ok(cryptoService.decrypt(payload));
    }
}