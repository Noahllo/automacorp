package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.model.WindowEntity;

public class WindowMapper {
    public static WindowDto of (WindowEntity window){
        return new WindowDto(
                window.getId(),
                window.getName(),
                window.getRoomId()
        );
    }
}
