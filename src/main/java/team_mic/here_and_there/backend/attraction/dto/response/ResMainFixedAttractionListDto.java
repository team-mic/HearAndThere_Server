package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"language", "areaName", "attractionItemList"})
public class ResMainFixedAttractionListDto {

  @ApiModelProperty(notes = "언어 버전")
  private String language;

  @ApiModelProperty(notes = "지역명")
  private String areaName;

  private List<ResMainFixedAttractionListItemDto> attractionItemList;

  @Builder
  private ResMainFixedAttractionListDto(String language, String areaName, List<ResMainFixedAttractionListItemDto> attractionItemList){
    this.language = language;
    this.areaName = areaName;
    this.attractionItemList = attractionItemList;
  }
}
