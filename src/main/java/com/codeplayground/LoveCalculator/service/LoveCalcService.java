package com.codeplayground.LoveCalculator.service;
import java.io.StringReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.codeplayground.LoveCalculator.model.Compatability;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class LoveCalcService {

    public final static String URL = "https://love-calculator.p.rapidapi.com/getPercentage";

    @Value("${lovecalcapi.key}")
    private String apiKey;

    public Compatability getPercentage(String sname, String fname) {

        String url = UriComponentsBuilder
                    .fromUriString(URL)
                    .queryParam("sname", sname)
                    .queryParam("fname", fname)
                    .toUriString();
        
        System.out.printf("URL: %s\n", url); //testing

        RequestEntity<Void> req = RequestEntity.get(url)
                    .header("X-RapidAPI-Key", apiKey)
                    .header("X-RapidAPI-Host", "love-calculator.p.rapidapi.com")
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

        Compatability comp = new Compatability();
        comp.setSname(sname);
        comp.setFname(fname);
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        String body = resp.getBody();
        System.out.printf("Body: %s\n", body); //testing

        JsonReader reader = Json.createReader(new StringReader(body));
        JsonObject obj = reader.readObject();

        String percentage = "%s".formatted(obj.getJsonString("percentage"));
        comp.setPercentage(percentage);
        String result = "%s".formatted(obj.getJsonString("result"));
        comp.setResult(result);

        return comp;
    }
    
}
