package com.wilddog.security.token;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TokenGeneratorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateToken() {
        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", "1");
        authPayload.put("some", "arbitrary");
        authPayload.put("data", "here");

        TokenGenerator tokenGenerator = new TokenGenerator("OPeJAbqF07aWFw5SXvV7sUZnT3SbHVxzeZwqvJHV");
        String token = tokenGenerator.createToken(authPayload);
        
        System.out.println(token);
    }
    
    @Test
    public void testCreateToken2() {
        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", "-Je9A7op-mEPuhrDPN9T");
        authPayload.put("id", "-Je9A7op-mEPuhrDPN9T");
        authPayload.put("wilddog", "tquest");

        TokenGenerator tokenGenerator = new TokenGenerator("OPeJAbqF07aWFw5SXvV7sUZnT3SbHVxzeZwqvJHV");
        String token = tokenGenerator.createToken(authPayload);
        
        System.out.println(token);
    }

}
