package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
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

  private String language;

  private Integer pageNumber;

  private String listOrder;

  private String areaName;

  private Integer areaCode;

  private Integer sigunguAreaCode;

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
