package com.dlf.model.enums;

import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface ICommEnums {

    String getDesc();

    Integer getCode();

    static <E extends Enum<E> & ICommEnums> Map<Integer, String> getKeyValueMap(String key, Class<E> clazz) {
        Objects.requireNonNull(key);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream()
                .filter(e -> e.name().contains(key.toUpperCase()))
                .collect(Collectors.toMap(e -> e.getCode(), e -> e.getDesc()));
    }
}
