package com.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/19 5:13 PM
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("result", false);
        gen.writeObjectField("code", HttpStatus.BAD_REQUEST.value());
        gen.writeObjectField("message", value.getMessage());
        gen.writeEndObject();
    }

}
