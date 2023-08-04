package com.masai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeConverterService {

    @Value("${chatgpt.api.key}") // Store your ChatGPT API key in application.properties
    private String chatGPTApiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public String convertCode(String code) {
        // Call the ChatGPT API to perform the code conversion
        String apiUrl = "https://api.openai.com/v1/engines/text-davinci-003/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + chatGPTApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", code);
        requestBody.put("max_tokens", 100); // Adjust as needed

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, Map.class);


        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseEntity.getBody().get("choices");

            if (!choices.isEmpty()) {
                String convertedText = choices.get(0).get("text").toString();
                return convertedText;
            } else {
                return "No conversion result found.";
            }
        } else {
            return "Code conversion failed.";
        }
    }
}

