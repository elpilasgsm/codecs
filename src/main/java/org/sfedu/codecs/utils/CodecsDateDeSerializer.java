package org.sfedu.codecs.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CodecsDateDeSerializer extends StdDeserializer<Calendar> {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    protected CodecsDateDeSerializer() {
        super(Calendar.class);
    }

    @Override
    public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Calendar c = Calendar.getInstance();
        String fromUI = jsonParser.readValueAs(String.class);
        try {
            c.setTime(sdf.parse(fromUI));
        } catch (ParseException e) {
            throw new IOException(e);
        }
        return c;
    }

}
