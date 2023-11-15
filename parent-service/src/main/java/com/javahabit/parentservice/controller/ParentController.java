package com.javahabit.parentservice.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ParentController {

    final
    RestTemplate restTemplate;

    public ParentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/parent")
    @Observed(
            name = "user.name",
            contextualName = "Parent-->child",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public String sayHi(){
        log.info("Parent was called ...");
        log.info("Say Hi to Grandchild ...");
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:6060/child-service/child",
                HttpMethod.GET,
                null,
                String.class
        );

        String responseFromChild = response.getBody();
        return "Grandchild said: " +  responseFromChild;

    }

}
