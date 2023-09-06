package com.jwtExample.controller;

import com.jwtExample.model.TokenReqRes;
import com.jwtExample.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("generate-token")
    public ResponseEntity<?> generateToken(@RequestBody TokenReqRes tokenReqRes) {
        if(tokenReqRes.getUserName().equals("sidharthdas.1995@gmail.com")
        && tokenReqRes.getPassword().equals("qwerty")) {
            String token = jwtTokenUtil.generateToken(tokenReqRes.getUserName());
            TokenReqRes tokenReqRes1 = new TokenReqRes(null, null, token, "60");
            return ResponseEntity.status(HttpStatus.OK).body(tokenReqRes1);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credential");
        }
    }

    @PostMapping("validate-token")
    public ResponseEntity<?> validateToken(@RequestBody TokenReqRes tokenReqRes) {
        return ResponseEntity.ok(jwtTokenUtil.validateToken(tokenReqRes.getToken()));
    }

    @GetMapping("get-fruits")
    public ResponseEntity<?> getFruits(@RequestHeader(value = "Authorization", required = true)String token) {
        if(token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised. Token Required");
        if(jwtTokenUtil.validateToken(token.substring(7)).equals("valid")) {
            return ResponseEntity.ok(List.of("Apple", "Mango"));
        }
        return ResponseEntity.status(401).body("Invalid Token");

    }
}
