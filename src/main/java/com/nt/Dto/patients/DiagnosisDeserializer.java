package com.nt.Dto.patients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class DiagnosisDeserializer extends JsonDeserializer<Diagnosis> {

    @Override
    public Diagnosis deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String diagnosis = p.getValueAsString();
        return new Diagnosis(diagnosis);
    }
    
    
    
}
