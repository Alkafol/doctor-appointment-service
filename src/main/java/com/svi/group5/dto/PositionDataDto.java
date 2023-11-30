package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDataDto {
    @JsonProperty("positionId")
    Long id;
    @JsonProperty("positionName")
    String name;
}
