package com.example.backendcinema.Converter;

import com.example.backendcinema.entity.Account.RoleGender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleGenderConverter implements AttributeConverter<RoleGender, String> {

    @Override
    public String convertToDatabaseColumn(RoleGender attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getGenderVietnamese();
    }

    @Override
    public RoleGender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (RoleGender gender : RoleGender.values()) {
            if (gender.getGenderVietnamese().equals(dbData)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
