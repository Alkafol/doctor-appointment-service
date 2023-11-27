package com.svi.group5.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper<T> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String jsonAsString(T body) {
        try {
            return mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot make JSON", e);
        }
    }

    public static String toPrettyFormat(String jsonString) throws IOException {
        try {
            Object json = mapper.readValue(jsonString, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
