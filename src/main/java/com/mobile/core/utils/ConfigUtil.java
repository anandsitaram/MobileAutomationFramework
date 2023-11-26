package com.mobile.core.utils;

import com.mobile.core.constants.FrameworkConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class ConfigUtil {

    private ConfigUtil() {

    }

    private static Properties properties = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    static {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(FrameworkConstants.PROPERTY_PATH));
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            System.exit(0);
        }


        for (Map.Entry<Object, Object> entry : properties.entrySet()) {

            CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));

        }


    }

    static String getValue(String key) {

        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key))) {
            throw new RuntimeException("Key not present in properties file");
        }
        return CONFIGMAP.get(key);

    }

}


