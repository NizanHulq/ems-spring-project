package com.voltras.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.util.List;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module()).registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .enable(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE);

    public static String parseToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T readAsObject(String data, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(data, clazz);
    }

    public static <T> List<T> parseIntoListModel(Object data, Class<T> clazz)
            throws JsonMappingException, JsonProcessingException {
        var jsonItems = parseToString(data);
        return objectMapper.readValue(jsonItems,
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> T parseIntoSingleModel(Object data, Class<T> clazz)
            throws JsonMappingException, JsonProcessingException {
        var jsonItems = parseToString(data);
        return readAsObject(jsonItems, clazz);
    }
}
