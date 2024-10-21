package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.RoomDto;
import com.emse.spring.automacorp.model.RoomEntity;

public class RoomMapper {
    public static RoomDto of (RoomEntity room){
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getFloor(),
                room.getCurrentTemperatureValue(),
                room.getTargetTemperature(),
                room.getWindows()
        );
    }
}
