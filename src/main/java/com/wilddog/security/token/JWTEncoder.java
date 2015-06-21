package com.wilddog.security.token;

import java.nio.charset.Charset;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonObject;

/**
 * JWT encoder.
 *
 * @author vikrum
 */
public class JWTEncoder {

    private static final String TOKEN_SEP = ".";
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final String HMAC_256 = "HmacSHA256";

    /**
     * Encode and sign a set of claims.
     *
     * @param claims
     * @param secret
     * @return
     */
    public static String encode(JsonObject claims, String secret) {
        String encodedHeader = getCommonHeader();
        String encodedClaims = encodeJson(claims);

        String secureBits = new StringBuilder(encodedHeader).append(TOKEN_SEP).append(encodedClaims).toString();

        String sig = sign(secret, secureBits);

        return new StringBuilder(secureBits).append(TOKEN_SEP).append(sig).toString();
    }

    private static String sign(String secret, String secureBits) {
        try {
            Mac sha256_HMAC = Mac.getInstance(HMAC_256);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(UTF8_CHARSET), HMAC_256);
            sha256_HMAC.init(secret_key);
            byte sig[] = sha256_HMAC.doFinal(secureBits.getBytes(UTF8_CHARSET));
            return Base64.encodeBase64URLSafeString(sig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCommonHeader() {
        JsonObject headerJson = new JsonObject();
        headerJson.addProperty("typ", "JWT");
        headerJson.addProperty("alg", "HS256");
        return encodeJson(headerJson);
    }

    private static String encodeJson(JsonObject jsonData) {
        return Base64.encodeBase64URLSafeString(TokenGenerator.gson.toJson(jsonData).getBytes(UTF8_CHARSET));
    }
}
