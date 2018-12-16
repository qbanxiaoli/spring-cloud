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
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInh4Il0sImV4cCI6MTU0NTA0MzUyNiwidXVpZCI6ImFkMmUzMzMxOTc0YzRjZGFiZjUxMGY0OWY0OWI1N2IxIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9UQ01fQURNSU4iXSwianRpIjoiZGZhOTM0MDQtNjQwYy00Y2UyLWEzNDMtZmU1MTIwMTQ4OTJiIiwiY2xpZW50X2lkIjoiaHEifQ.e4NBFXLUaQtbeD-VpIUHCnOWKsHxKnBy9noaSy8JekSWPef3IQotfcLU__PCgfGlhzdxvueR0630ufx_54RC6QSJJ8gaseJ4HUBl8-7XmB78l5VWQXIsyL0epYE89_sHetWuzBQMAXOAD_GOv-9qi1S8xSpfgdqicrRI7MhRYGnw4dSobjjArgyxZ773U3jKuMEoKN1BcoCC1DwNt8rwUVcwWwAn-8st45t3202zaG_zYK-voL167An-pORUisar3dZ2BISqgkm4WRktsfLkyc_Ihg4qa1tGGDV5fYtb-1ksk4gQdd0s_7t3siLRQVqUwYT7AEKvTj1drFOlX_kBJQ";
        Jwt jwt = JwtHelper.decode(token);
        log.info(jwt.toString());
    }

}
