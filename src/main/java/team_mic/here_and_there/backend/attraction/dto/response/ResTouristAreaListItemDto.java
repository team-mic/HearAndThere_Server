package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"areaName", "areaCode","hasSigunguAreaCode", "sigunguAreaCode", "areaThumbnailImageUrl", "totalAttractionsCount"})
public class ResTouristAreaListItemDto {
  @ApiModelProperty(notes = "지역명")
  private String areaName;

  @ApiModelProperty(notes = "지역코드 : 필수")
  private Integer areaCode;

  @ApiModelProperty(notes = "지역의 시군구 코드의 존재 여부")
  private Boolean hasSigunguAreaCode;

  @ApiModelProperty(notes = "지역의 시군구 코드 : 도, 특별시의 경우 null")
  private Integer sigunguAreaCode;

  @ApiModelProperty(notes = "지역의 대표 이미지 url")
  private String areaThumbnailImageUrl;

  @ApiModelProperty(notes = "지역의 총 관광지 수")
  private Integer totalAttractionsCount;

  @Builder
  private ResTouristAreaListItemDto(String areaName, Integer areaCode,
      Boolean hasSigunguAreaCode, Integer sigunguAreaCode, String areaThumbnailImageUrl,
      Integer totalAttractionsCount){
    this.areaName = areaName;
    this.areaCode = areaCode;
    this.hasSigunguAreaCode = hasSigunguAreaCode;
    this.sigunguAreaCode = sigunguAreaCode;
    this.areaThumbnailImageUrl = areaThumbnailImageUrl;
    this.totalAttractionsCount = totalAttractionsCount;
  }
}
