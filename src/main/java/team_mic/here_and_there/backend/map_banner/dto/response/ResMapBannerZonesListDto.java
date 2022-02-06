package team_mic.here_and_there.backend.map_banner.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"language", "guideMessage", "areaName", "zoneDataType", "zoneData"})
public class ResMapBannerZonesListDto {

  @ApiModelProperty(notes = "언어 버전")
  private String language;

  @ApiModelProperty(notes = "배너 상단 가이드 메세지")
  private String guideMessage;

  @ApiModelProperty(notes = "부모 지역명")
  private String areaName;

  @ApiModelProperty(notes = "응답 display json 데이터 타입")
  private String zoneDataType;

  @ApiModelProperty(notes = "지역존 데이터")
  private Object zoneData; //map or list
}