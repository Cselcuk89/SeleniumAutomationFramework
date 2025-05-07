package com.selcuk.utilities;

import com.selcuk.constants.ProjectConstants;
import com.selcuk.enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class PropertyUtils {
    private static Properties properties = new Properties();
    private static final Map<String,String> CONFIGMAP = new HashMap<>();
    private PropertyUtils(){}
    static {
        try(FileInputStream fileInputStream = new FileInputStream(ProjectConstants.getConfigFilePath())){
            properties.load(fileInputStream);
            for (Map.Entry<Object,Object> entry : properties.entrySet()){
                CONFIGMAP.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()).trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String get(ConfigProperties key)  {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

}
