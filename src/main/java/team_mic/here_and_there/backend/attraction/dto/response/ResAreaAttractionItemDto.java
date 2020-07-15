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
    private String area;

    private String imageUrl;

    @JsonProperty("title")
    private String title;

    @JsonSetter("firstimage")
    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonGetter("imageUrl")
    public String getImageUrl(){
        return this.imageUrl;
    }

    public void setArea(String area){ this.area = area; }
}
