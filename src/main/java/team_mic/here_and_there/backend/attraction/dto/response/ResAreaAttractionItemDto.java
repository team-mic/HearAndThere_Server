package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResAreaAttractionItemDto {

    private Long attractionId;

    private String areaName;

    private String imageUrl;

    @JsonProperty("title")
    private String title;

    @JsonSetter("contentid")
    private void setAttractionId(Long attractionId) { this.attractionId = attractionId; }

    @JsonSetter("firstimage")
    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonGetter("imageUrl")
    public String getImageUrl(){
        return this.imageUrl;
    }

    @JsonGetter("attractionId")
    public Long getAttractionId() { return this.attractionId; }

    public void setAreaName(String areaName){ this.areaName = areaName; }
}
