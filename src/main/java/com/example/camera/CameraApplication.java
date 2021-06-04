package com.example.camera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

@SpringBootApplication
public class CameraApplication {

    /*
    //how restTemplate is configured.
    static ArrayList<HttpMessageConverter<?>> converters = new ArrayList<>(
               Arrays.asList(new FormHttpMessageConverter()));

    static ArrayList<HttpMessageConverter<?>> converters = new ArrayList<>(
            Arrays.asList(new MappingJackson2HttpMessageConverter(), new ResourceHttpMessageConverter(), new FormHttpMessageConverter(), new BufferedImageHttpMessageConverter()));

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(converters);
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(CameraApplication.class, args);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
