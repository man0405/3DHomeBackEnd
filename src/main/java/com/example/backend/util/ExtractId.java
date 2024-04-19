package com.example.backend.util;

import com.example.backend.services.information.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component

public class ExtractId {
    private static JwtService jwtService;

    public ExtractId(JwtService jwtService) {
        ExtractId.jwtService = jwtService;
    }

    public static Long ExtractIdFromToken(String bear){
        return Long.parseLong(jwtService.extractId(bear.substring(7)));
    }

}
