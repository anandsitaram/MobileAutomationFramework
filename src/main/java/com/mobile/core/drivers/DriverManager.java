package com.mobile.core.drivers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class DriverManager {

    private DriverManager(){

    }


    public void initDriver(){


    }

    public static void main(String ...args) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/environment.yml");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> map = objectMapper.readValue(file, Map.class);
        System.out.println(map);
        ArrayList<Map<String, Object>> map1 = (ArrayList<Map<String, Object>>) map.get("platforms");
        System.out.println(map1);
        System.out.println(map1.size());

    }
}
