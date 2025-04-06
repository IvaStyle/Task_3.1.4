package com.example.Task314.service;

import com.example.Task314.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public final RestTemplate restTemplate;
    public final String URL = "http://94.198.50.185:7081/api/users";
    public final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public UserServiceImpl (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void getAllUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(URL,String.class);
        HttpHeaders head = response.getHeaders();
        List<String> cookies = head.get("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {
            for (String cookie : cookies) {
                if (cookie.startsWith("JSESSIONID=")) {
                    headers.set("Cookie",cookie.split(";")[0]);
                }
            }
        }
    }

    @Override
    public String createUser(User user) {
        ResponseEntity<String> response = restTemplate.postForEntity(URL,new HttpEntity<>(user, headers),String.class);
        return response.getBody();
    }

    @Override
    public String updateUser(User user) {
        ResponseEntity<String> response = restTemplate
                .exchange(URL,HttpMethod.PUT,new HttpEntity<>(user, headers),String.class);
        return response.getBody();
    }

    @Override
    public String deleteUser(Long id) {
        ResponseEntity<String> response = restTemplate
                .exchange(URL+"/"+id,HttpMethod.DELETE,new HttpEntity<>(headers),String.class);
        return response.getBody();
    }
}