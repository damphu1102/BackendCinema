package com.example.backendcinema.Converter;

import com.example.backendcinema.entity.Cinema.StatusActivate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ActivateCinemaConverter implements AttributeConverter<StatusActivate, String> {
    @Override
    public String convertToDatabaseColumn(StatusActivate attribute) {
        if (attribute == null){
            return null;
        }
        return attribute.getActiveVietnamese();
    }

    @Override
    public StatusActivate convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        for (StatusActivate statusActivate : StatusActivate.values()){
            if (statusActivate.getActiveVietnamese().equals(dbData)){
                return statusActivate;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
