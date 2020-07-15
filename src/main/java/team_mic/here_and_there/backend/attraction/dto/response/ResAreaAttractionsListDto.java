package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResAreaAttractionsListDto {

    private List<ResAreaAttractionItemDto> attractionList;

    @JsonSetter("item")
    private void setAttractionList(List<ResAreaAttractionItemDto> attractionList) {
        this.attractionList = attractionList;
    }

    @JsonGetter("attractionsList")
    public List<ResAreaAttractionItemDto> getAttractionList(){
        return this.attractionList;
    }
}
