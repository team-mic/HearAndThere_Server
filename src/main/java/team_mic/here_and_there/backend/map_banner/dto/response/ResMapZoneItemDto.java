package team_mic.here_and_there.backend.map_banner.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"zoneKey", "zoneName", "zoneImageUrl", "hasEntryPoint", "entryPointLatitude", "entryPointLongitude"})
public class ResMapZoneItemDto {
  @ApiModelProperty(notes = "지역존 고유 key")
  private Integer zoneKey;

  @ApiModelProperty(notes = "지역존 이름")
  private String zoneName;

  @ApiModelProperty(notes = "지역존 이미지")
  private String zoneImageUrl;

  @ApiModelProperty(notes = "지도 유입 위경도 존재여부")
  private Boolean hasEntryPoint;

  @ApiModelProperty(notes = "지도 유입 위도")
  private Double entryPointLatitude;

  @ApiModelProperty(notes = "지도 유입 경도")
  private Double entryPointLongitude;
}
