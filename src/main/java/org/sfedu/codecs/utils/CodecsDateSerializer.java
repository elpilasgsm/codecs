package org.sfedu.codecs.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CodecsDateSerializer extends StdSerializer<Calendar> {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    protected CodecsDateSerializer() {
        super(Calendar.class);
    }

    @Override
    public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(sdf.format(calendar.getTime()));
    }
}
