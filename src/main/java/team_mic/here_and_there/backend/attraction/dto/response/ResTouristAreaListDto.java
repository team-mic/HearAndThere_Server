package team_mic.here_and_there.backend.attraction.dto.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"language", "touristAreaList"})
public class ResTouristAreaListDto {

  @ApiModelProperty(notes = "언어 버전")
  private String language;

  private List<ResTouristAreaListItemDto> touristAreaList;

  @Builder
  private ResTouristAreaListDto(String language, List<ResTouristAreaListItemDto> touristAreaList){
    this.language = language;
    this.touristAreaList = touristAreaList;
  }
}
