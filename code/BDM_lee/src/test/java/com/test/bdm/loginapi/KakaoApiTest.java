package com.test.bdm.loginapi;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class KakaoApiTest {

    @Test
    public void testKakaoApi() {
        String url = "https://kapi.kakao.com/v2/user/me";
        String accessToken = "WsC0UHzMcf2E4h1z6kV4oGXJlK0rgUWMd10KPXSYAAABjeRfGO-nsOtctwzlGQ";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "?secure_resource=true&property_keys=%5B%22secure_resource%22%5D",
                String.class, accessToken);

        System.out.println(response.getBody());
    }
}
