package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaCodeAndNameListDto {
    @JsonProperty("item")
    private List<AreaCodeAndNameItemDto> areaCodeAndNameItemList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AreaCodeAndNameItemDto{
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("name")
        private String areaName;
    }
}
