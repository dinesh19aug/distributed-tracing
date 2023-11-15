package com.javahabit.grandchildservice.service;

import org.springframework.stereotype.Service;

@Service
public class GcService {
    public String createHi(){
        return "Hi! From Grand Child";
    }
}
