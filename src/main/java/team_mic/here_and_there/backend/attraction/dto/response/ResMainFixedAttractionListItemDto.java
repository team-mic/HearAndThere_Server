package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"attractionContentId", "attractionContentTypeId", "title", "thumbnailImageUrl"})
public class ResMainFixedAttractionListItemDto {

  @ApiModelProperty(notes = "관광명소 content id")
  private Long attractionContentId;

  @ApiModelProperty(notes = "관광명소 content type id")
  private Long attractionContentTypeId;

  @ApiModelProperty(notes = "관광지 제목")
  private String title;

  @ApiModelProperty(notes = "관광지 대표 이미지 url")
  private String thumbnailImageUrl;

  @Builder
  private ResMainFixedAttractionListItemDto(Long attractionContentId, Long attractionContentTypeId,
      String title, String thumbnailImageUrl){
    this.attractionContentId = attractionContentId;
    this.attractionContentTypeId = attractionContentTypeId;
    this.title = title;
    this.thumbnailImageUrl = thumbnailImageUrl;
  }
}
