package com.glymed.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.yaml.snakeyaml.Yaml;


public class Config {
    static Properties conf = new Properties();

   /**
     * Method to initialize configuration.
     *
     */
    public static Properties initializeConfig() throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/glymed/resources/config.properties");
            conf.load(fis);
            //set browser property
            String browserProperty = System.getProperty("browser");
            System.out.println(System.getProperty("browser"));
            String browser = (browserProperty != null) ? browserProperty : "CHROME";
            conf.setProperty("BROWSER", browser);
            System.setProperty("BROWSER",browser);
            
            //set environment property
            String platformProperty = System.getProperty("platform");
            System.out.println(System.getProperty("platform"));
            String platform = (platformProperty != null) ? platformProperty : "LOCAL";
            System.out.println(platform);
            Map<String, String> platformConfig = parseYaml("/src/main/java/com/glymed/resources/Sessions.yml", platform);
            loadDeviceProperty(platformConfig);
            
            //set device property
            String deviceProperty = System.getProperty("device");
            System.out.println(System.getProperty("device"));
            String device = (deviceProperty != null) ? deviceProperty : "WEB";
            System.out.println(device);
            conf.setProperty("DEVICE", device);
            Map<String, String> deviceConfig = parseYaml("/src/main/java/com/glymed/resources/responsiveConfig.yml", device);
            loadDeviceProperty(deviceConfig);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return conf;
    }

    
    
    
    /**
     * Method to load properties specific for the emulator/device
     *
     * @param: properties
     *             the map of emulator properties read from the Sessions.yml file
     *
     */

    public static void loadDeviceProperty(Map<String, String> properties) {
        for (Entry<String, String> config : properties.entrySet()) {
        	System.out.println(config.getKey());
        	System.out.println(config.getValue());
        	conf.setProperty(config.getKey(), config.getValue());
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    private static Map<String, String> parseYaml(String filename, String parameter) throws IOException {
        FileInputStream fis = null;
        Map<String, Object> platforms = null;
        Map<String, String> configs = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/" + filename);
            platforms = (Map<String, Object>) new Yaml().load(fis);
            for (Entry<String, Object> key : platforms.entrySet()) {
                if (key.getKey().equalsIgnoreCase(parameter)) {
                    configs = (Map<String, String>) key.getValue();
                    break;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return configs;
    }
    
    
    public static Properties initializeConstants() throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/glymed/resources/constants.properties");
            conf.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return conf;
    }
}
