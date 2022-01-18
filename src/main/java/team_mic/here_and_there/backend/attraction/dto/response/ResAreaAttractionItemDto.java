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
@JsonPropertyOrder({"attractionContentTypeId", "attractionContentId", "title", "imageUrl"})
public class ResAreaAttractionItemDto {

  @ApiModelProperty(notes = "관광명소 content id")
  private Long attractionContentId;

  @ApiModelProperty(notes = "관광명소 content type id")
  private Long attractionContentTypeId;

  @ApiModelProperty(notes = "관광명소의 대표 이미지")
  private String imageUrl;

  @ApiModelProperty(notes = "관광명소의 제목,이름", required = true)
  @JsonProperty("title")
  private String title;

  @ApiModelProperty(notes = "관광명소 위치의 지역명")
  private String areaName;

  @JsonSetter("contenttypeid")
  private void setAttractionContentTypeId(Long attractionContentTypeId) {
    this.attractionContentTypeId = attractionContentTypeId;
  }

  @JsonSetter("contentid")
  private void setAttractionId(Long attractionContentId) {
    this.attractionContentId = attractionContentId;
  }

  @JsonSetter("firstimage")
  private void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @JsonGetter("imageUrl")
  public String getImageUrl() {
    return this.imageUrl;
  }

  @JsonGetter("attractionContentId")
  public Long getAttractionContentId() {
    return this.attractionContentId;
  }

  @JsonGetter("attractionContentTypeId")
  public Long getAttractionContentTypeId() {
    return this.attractionContentTypeId;
  }

  public void setAreaName(String areaName){
    this.areaName = areaName;
  }
}
