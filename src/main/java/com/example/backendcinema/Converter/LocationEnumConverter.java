package com.example.backendcinema.Converter;

import com.example.backendcinema.entity.Cinema.LocationEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocationEnumConverter implements AttributeConverter<LocationEnum, String> {

    @Override
    public String convertToDatabaseColumn(LocationEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLocationVietNamese();
    }

    @Override
    public LocationEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (LocationEnum location : LocationEnum.values()) {
            if (location.getLocationVietNamese().equals(dbData)) {
                return location;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
