package team_mic.here_and_there.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionItemDto;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultAttractionListDto {
  private List<ResAreaAttractionItemDto> attractionsList;
  private Integer totalAttractionCount;

  @JsonSetter("item")
  public void setAttractionList(List<ResAreaAttractionItemDto> attractionList) {
    this.attractionsList = attractionList;
  }
}
