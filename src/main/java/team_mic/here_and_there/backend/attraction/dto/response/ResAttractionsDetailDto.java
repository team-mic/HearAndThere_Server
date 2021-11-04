package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.ApiModelProperty;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"language", "contentId", "contentTypeId",
    "detailCommonInfo", "imagesList",
    "hasRelatedAudioGuides", "relatedAudioGuidesCount", "relatedAudioGuideLists"})
public class ResAttractionsDetailDto {

  @ApiModelProperty(value = "언어 버전")
  private String language;

  private Long contentId;

  private Integer contentTypeId;

  @ApiModelProperty(value = "관광지 상세 공통 정보")
  private ResAttractionDetailCommonDto detailCommonInfo;

  @ApiModelProperty(value = "관광지 상세 소개 정보")
  private LinkedHashMap<String, Object> detailIntroductionInfo;

  @ApiModelProperty(value = "관광지 이미지 리스트")
  private List<String> imagesList;

  @ApiModelProperty(value = "관광지 관련 오디오 가이드의 존재여부")
  private Boolean hasRelatedAudioGuides;

  @ApiModelProperty(value = "관광지 관련 오디오 가이드 개수")
  private Integer relatedAudioGuidesCount;

  @ApiModelProperty(value = "관광지 관련 오디오 가이드 정보 리스트")
  private List<ResAudioGuideItemDto> relatedAudioGuideLists;

  @JsonSetter("item")
  private void setDetailCommonInfo(ResAttractionDetailCommonDto detailCommonInfo) {
    this.detailCommonInfo = detailCommonInfo;
  }

  @JsonGetter("detailCommonInfo")
  public ResAttractionDetailCommonDto getDetailCommonInfo() {
    return this.detailCommonInfo;
  }
}
