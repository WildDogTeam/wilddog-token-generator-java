package com.wilddog.security.token;

import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TokenGenerator {
    
    public static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private static final int TOKEN_VERSION = 0;

    private final String wilddogSecret;

    public TokenGenerator(String wilddogSecret) {
        super();
        this.wilddogSecret = wilddogSecret;
    }

    public String createToken(Map<String, Object> data) {
        return createToken(data, new TokenOptions());
    }

    public String createToken(Map<String, Object> data, TokenOptions options) {
        if ((data == null || data.size() == 0) && (options == null || (!options.isAdmin() && !options.isDebug()))) {
            throw new IllegalArgumentException("TokenGenerator.createToken: data is empty and no options are set.  This token will have no effect on Firebase.");
        }

        JsonObject claims = new JsonObject();

        claims.addProperty("v", TOKEN_VERSION);
        claims.addProperty("iat", new Date().getTime() / 1000);

        boolean isAdminToken = (options != null && options.isAdmin());
        validateToken("TokenGenerator.createToken", data, isAdminToken);

        if (data != null && data.size() > 0) {
            JsonObject obj = gson.fromJson(gson.toJson(data), JsonObject.class);
            claims.add("d", obj);
        }

        // Handle options
        if (options != null) {
            if (options.getExpires() != null) {
                claims.addProperty("exp", options.getExpires().getTime() / 1000);
            }

            if (options.getNotBefore() != null) {
                claims.addProperty("nbf", options.getNotBefore().getTime() / 1000);
            }

            // Only add these claims if they're true to avoid sending them over the wire when false.
            if (options.isAdmin()) {
                claims.addProperty("admin", options.isAdmin());
            }

            if (options.isDebug()) {
                claims.addProperty("debug", options.isDebug());
            }
        }

        String token = computeToken(claims);
        if (token.length() > 1024) {
            throw new IllegalArgumentException("TokenGenerator.createToken: Generated token is too long. The token cannot be longer than 1024 bytes.");
        }
        return token;
    }

    private String computeToken(JsonObject claims) {
        return JWTEncoder.encode(claims, wilddogSecret);
    }

    private void validateToken(String functionName, Map<String, Object> data, boolean isAdminToken) {
        boolean containsUid = (data != null && data.containsKey("uid"));
        if ((!containsUid && !isAdminToken) || (containsUid && !(data.get("uid") instanceof String))) {
            throw new IllegalArgumentException(functionName + ": Data payload must contain a \"uid\" key that must be a string.");
        } else if (containsUid && data.get("uid").toString().length() > 256) {
            throw new IllegalArgumentException(functionName + ": Data payload must contain a \"uid\" key that must not be longer than 256 characters.");
        }
    }
}
