package com.zidio.connect.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinary() {
        Map<String, String> config = new HashMap<>();
        // Replace with your actual Cloudinary credentials
        config.put("cloud_name", "YOUR_CLOUD_NAME");
        config.put("api_key", "YOUR_API_KEY");
        config.put("api_secret", "YOUR_API_SECRET");
        config.put("secure", "true");
        return new Cloudinary(config);
    }
    
}