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
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJxYmFueGlhb2xpIiwic2NvcGUiOlsieHgiXSwiZXhwIjoxNTQ0ODQ0Mzg1LCJ1dWlkIjoiNWNjNDhhYmRhOTI2NDNlN2IzMDYyZDFjMmFhM2YxYjEiLCJhdXRob3JpdGllcyI6WyJST0xFX1RDTV9FTVBMT1lFRSJdLCJqdGkiOiIzNjY2OTVmMS1iNGMyLTRjZWItYmY5OS01ZmFmMTRmYTY3NzEiLCJjbGllbnRfaWQiOiJocSJ9.R4wGvHJSdCZJIFFEt3DSiH3Hc8jV2uWFoeA9-EzofMvBiep-PE2j52iXs3SMKvv589-R58erLYV4zmqUtZD72X72qZvOmOn3HFpsqXva8lV6JzxiXfELx6WpuppWjMkU56FQSKslBb5DKInZOPn5IEi9wbICAGaOFmv7irZ7yAvjo8PAJmrFvJYnwAuC_iX0QIPKPsEAbIafjVjoQF-MsCpF73rdyqVy0Of3fCUPWifSpoxpvM6Zjvx3AO0lGd2XC389KEPY6gr_wYTx3gnwgJcZG7fw7g3cg5cFuYj014RC2UjvWAiVy5CSGauKxXwHTJl1YOe5tGQn0FlWXRkGEg";
        Jwt jwt = JwtHelper.decode(token);
        log.info(jwt.toString());
    }

}
