package app.web.readFromPlc.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonConverter {
    static private ObjectMapper objectMapper = new ObjectMapper();

    static {
        configureObjectMapper();
    }

    public static String toJson(Object object) throws IOException {
        final var json = objectMapper.writeValueAsString(object);
        return json;
    }

    private static void configureObjectMapper() {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
