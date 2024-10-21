package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.SensorDto;
import com.emse.spring.automacorp.model.SensorEntity;

public class SensorMapper {
    public static SensorDto of (SensorEntity sensor){
        return new SensorDto(
                sensor.getId(),
                sensor.getName(),
                sensor.getValue(),
                sensor.getSensorType()
        );
    }
}