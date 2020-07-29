package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"attractionId", "title", "areaName", "imageUrl"})
public class ResAreaAttractionItemDto {

  @ApiModelProperty(notes = "관광명소 id")
  private Long attractionId;

  @ApiModelProperty(notes = "관광명소의 지역명", required = true)
  private String areaName;

  @ApiModelProperty(notes = "관광명소의 대표 이미지")
  private String imageUrl;

  @ApiModelProperty(notes = "관광명소의 제목,이름", required = true)
  @JsonProperty("title")
  private String title;

  @JsonSetter("contentid")
  private void setAttractionId(Long attractionId) {
    this.attractionId = attractionId;
  }

  @JsonSetter("firstimage")
  private void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @JsonGetter("imageUrl")
  public String getImageUrl() {
    return this.imageUrl;
  }

  @JsonGetter("attractionId")
  public Long getAttractionId() {
    return this.attractionId;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

}
