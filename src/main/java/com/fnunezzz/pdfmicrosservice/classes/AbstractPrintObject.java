package com.fnunezzz.pdfmicrosservice.classes;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class AbstractPrintObject {
    protected abstract String buildJsonObject(String templateName) throws Exception;

    /**
     * Recurssively validates if object (and it's childs) have any null value.
     * If there's any throws an exception.
     * @param target Generic object that should be validated
     * @throws Exception
     */
    public static boolean anyNull(Object target) throws Exception {
        return Arrays.stream(target.getClass()
                        .getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .anyMatch(f -> {
                    Object fieldValue = getFieldValue(f, target);
                    if (fieldValue == null) {
                        throw new RuntimeException("Template key is null " + f );
                    } else if (!(fieldValue instanceof Integer) && !(fieldValue instanceof String)) {
                        try {
                            return anyNull(fieldValue);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return false;
                });
    }
    private static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
