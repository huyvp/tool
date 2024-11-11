package com.tool.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpRequest {
    private static ResponseEntity<String> sendRequest(MultiValueMap<String, String> headers, Map<String, String> param, String url, HttpMethod method) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(buildUri(param, url), method, httpEntity, String.class);
    }

    private static String buildUri(Map<String, String> param, String url) {
        StringBuilder stringBuilder = new StringBuilder(url).append("?");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (!stringBuilder.isEmpty()) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return stringBuilder.toString();
    }
}
