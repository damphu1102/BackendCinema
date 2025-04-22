package com.example.backendcinema.Converter;

import com.example.backendcinema.entity.Movie.StatusMovie;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusMovieConverter implements AttributeConverter<StatusMovie, String> {
    @Override
    public String convertToDatabaseColumn(StatusMovie attribute) {
        if (attribute == null){
            return null;
        }
        return attribute.getStatusVietnamese();
    }

    @Override
    public StatusMovie convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        for (StatusMovie statusMovie : StatusMovie.values()){
            if (statusMovie.getStatusVietnamese().equals(dbData)){
                return statusMovie;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
