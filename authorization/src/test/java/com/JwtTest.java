package com;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/12 20:47
 */
@Slf4j
public class JwtTest {

    @Test
    public void test() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInh4Il0sImV4cCI6MTU0NDc3NzA2OCwidXVpZCI6ImFkMmUzMzMxOTc0YzRjZGFiZjUxMGY0OWY0OWI1N2IxIiwiYXV0aG9yaXRpZXMiOlsiVENNX0FETUlOIl0sImp0aSI6IjI3NWViYjUyLWI3NzMtNGFkNy05MTIxLTg4YTAzMWFiY2U3OCIsImNsaWVudF9pZCI6ImhxIn0.C-zWFf_ztzs3TR06BSQlSlH40s3utU0hnG8wcsyDGaQyyvpyPlLHlOrXzMUl2AWYFY2yFi6mgdFpQ4jhLLPmhk-fBBAP4jzXE0jFDGb2YSZzOgkIbifxQjwGNAW0stJMtcJwxob-1Bcp3Zn00TO9jLRW3_iVjluONVirfMXN651jF0dhwkWPrpDcnZD8Z_MIlmk8D-qjvCgt4H_kpnaL9mnG9oPcoyX4EaF2gs9139rTKkHgru8ZbFI2UxPadOCVQBXdSTu8mKToYdasABaoHFtj24L655W4Zx9Zuq2YyEDfOZYcCvVesCQgQp6K8VfdOjBbNTFszD7Q6gpU6gjd8g";
        Jwt jwt = JwtHelper.decode(token);
        log.info(jwt.toString());
    }

}
