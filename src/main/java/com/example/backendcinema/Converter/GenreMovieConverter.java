package com.example.backendcinema.Converter;

import com.example.backendcinema.entity.Movie.GenreMovie;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenreMovieConverter implements AttributeConverter<GenreMovie, String> {

    @Override
    public String convertToDatabaseColumn(GenreMovie attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getGenreVietnamese();
    }

    @Override
    public GenreMovie convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (GenreMovie genre : GenreMovie.values()) {
            if (genre.getGenreVietnamese().equals(dbData)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
