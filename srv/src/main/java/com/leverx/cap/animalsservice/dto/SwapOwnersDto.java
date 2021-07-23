package com.leverx.cap.animalsservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SwapOwnersDto {

    private final String firstAnimalsId;
    private final String secondAnimalsId;
}
