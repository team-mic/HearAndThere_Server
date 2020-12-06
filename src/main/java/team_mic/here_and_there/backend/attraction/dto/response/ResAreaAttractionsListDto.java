package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"language", "pageNumber", "listOrder", "areaName", "areaMainImageUrl",
    "areaCode", "sigunguAreaCode", "attractionList"})
public class ResAreaAttractionsListDto {

  @ApiModelProperty(value = "언어 버전")
  private String language;

  @ApiModelProperty(value = "페이지 번호")
  private Integer pageNumber;

  @ApiModelProperty(value = "관광지 목록 정렬기준")
  private String listOrder;

  @ApiModelProperty(value = "지역명")
  private String areaName;

  @ApiModelProperty(value = "지역 코드")
  private Integer areaCode;

  @ApiModelProperty(value = "시군구 코드")
  private Integer sigunguAreaCode;

  @ApiModelProperty(value = "지역 대표 이미지 url")
  private String areaMainImageUrl;

  private List<ResAreaAttractionItemDto> attractionList;

  @JsonSetter("item")
  public void setAttractionList(List<ResAreaAttractionItemDto> attractionList) {
    this.attractionList = attractionList;
  }

  @ApiModelProperty(notes = "지역별 관광명소 리스트")
  @JsonGetter("attractionsList")
  public List<ResAreaAttractionItemDto> getAttractionList() {
    return this.attractionList;
  }
}
