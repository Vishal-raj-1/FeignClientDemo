package com.vishalrajput.service1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoPayloadResponse {
    private String jwtToken;
    private String xSignature;
    private String encryptedPayload;
}
