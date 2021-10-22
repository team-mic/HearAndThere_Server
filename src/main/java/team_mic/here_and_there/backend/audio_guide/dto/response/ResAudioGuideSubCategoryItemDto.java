package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"categoryId", "categoryName", "categoryArea", "guideCount",
    "bannerBackgroundColorHex", "bannerIconImage"})
public class ResAudioGuideSubCategoryItemDto {

  @ApiModelProperty(notes = "카테고리 테마 id")
  private Integer categoryId;
  @ApiModelProperty(notes = "카테고리 테마 이름")
  private String categoryName;
  @ApiModelProperty(notes = "카테고리 테마에 속한 가이드의 지역")
  private String categoryArea;
  @ApiModelProperty(notes = "카테고리 테마에 속한 가이드 개수")
  private Integer guideCount;
  @ApiModelProperty(notes = "카테고리 테마 배너 배경 색 hex 값")
  private String bannerBackgroundColorHex;
  @ApiModelProperty(notes = "카테고리 테마 배너 아이콘 이미지 url")
  private String bannerIconImage;
}
