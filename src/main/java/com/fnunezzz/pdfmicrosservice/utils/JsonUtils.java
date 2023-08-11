package com.fnunezzz.pdfmicrosservice.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {

    /**
     * 
     * @param targerClass Class that should be deserialized
     * @param object JSON String that should be used in deserialization
     * @return target class
     * @throws Exception
     */
    public static <D> D deserialize(Class<D> targetClass, final Object object) throws Exception {
        if (object == null) {
            throw new Exception("String JSON not found");
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(json, targetClass);
    }
}