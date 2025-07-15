package com.example.livekitagentbackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.livekitagentbackend.common.ObjectInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LiveKitService {

    @Value("${livekit.api-key}")
    private String apiKey;

    @Value("${livekit.api-secret}")
    private String apiSecret;

    @Value("${livekit.url}")
    private String livekitUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateAccessToken(String identity, String roomName) {

        Instant now = Instant.now();
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);

        return JWT.create()
                .withIssuer(apiKey)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(1, ChronoUnit.HOURS)))
                .withClaim("video", Map.of(
                        "roomCreate", true,
                        "roomJoin", true,
                        "room", roomName
                ))
                .withSubject(identity)
                .sign(algorithm);

    }


    public void createRoomIfNotExists(String roomName) {

        String token = generateAccessToken("admin", roomName);

        String endpoint = livekitUrl + "/twirp/livekit.RoomService/CreateRoom";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, Object> request = Map.of("name", roomName);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        try {
            restTemplate.exchange(endpoint, HttpMethod.POST, entity, String.class);
        } catch (Exception ex) {
            if (!ex.getMessage().contains("ALREADY_EXISTS")) {
                throw new ObjectInvalidException(ex.getMessage());
            }
        }
    }
}
