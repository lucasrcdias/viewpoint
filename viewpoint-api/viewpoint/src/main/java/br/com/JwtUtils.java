package br.com;

import br.com.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

@Service
public class JwtUtils {

    private static final String apiKey = "viewpointSecret";

    public String generateToken(User user) throws JsonProcessingException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(user.getEmail())
                .setIssuedAt(now)
                .setSubject(user.getEmail())
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public boolean isAuthenticated(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apiKey))
                    .parseClaimsJws(token.trim()).getBody();
            if (Objects.nonNull(claims)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
