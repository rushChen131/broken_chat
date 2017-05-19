package com.rush.chat.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class GlobalUtils extends PropertyPlaceholderConfigurer {

    private static Map<String, String> properties = new HashMap<String, String>();

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);

        for (String key : props.stringPropertyNames()) {
            properties.put(key, props.getProperty(key));
        }

    }

    public static String getString(String key) {
        return properties.get(key);
    }

}
