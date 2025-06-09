package com.vishalrajput.service1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoPayloadRequest {
    private Map<String, Object> efields;
}
