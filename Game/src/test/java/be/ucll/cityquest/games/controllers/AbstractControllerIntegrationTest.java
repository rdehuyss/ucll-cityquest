package be.ucll.cityquest.games.controllers;

import be.ucll.cityquest.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers;

    public AbstractControllerIntegrationTest() {
        this.headers = new HttpHeaders();
        this.headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        restTemplate = new TestRestTemplate(new RestTemplateBuilder()
                .setConnectTimeout(5000)
                .setReadTimeout(5000));
    }

    protected String httpGet(String url) {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(url),
                HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    protected String httpPost(String url, Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(obj), headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    createURLWithPort(url),
                    HttpMethod.POST, entity, String.class);

            return response.getBody();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected String jsonTestFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createURLWithPort(String uri) {
        if(uri.startsWith("http")) return uri;
        return "http://localhost:" + port + uri;
    }
}

