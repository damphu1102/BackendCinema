package com.example.backendcinema.Converter;

import com.example.backendcinema.entity.Movie.LanguageMovie;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageMovieConverter implements AttributeConverter<LanguageMovie, String> {
    @Override
    public String convertToDatabaseColumn(LanguageMovie attribute) {
        if (attribute == null){
            return null;
        }
        return attribute.getLanguageVietnamese();
    }

    @Override
    public LanguageMovie convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        for (LanguageMovie languageMovie : LanguageMovie.values()){
            if (languageMovie.getLanguageVietnamese().equals(dbData)){
                return languageMovie;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
