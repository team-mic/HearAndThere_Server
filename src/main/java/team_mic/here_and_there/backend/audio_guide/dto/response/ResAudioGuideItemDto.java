package team_mic.here_and_there.backend.audio_guide.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResAudioGuideItemDto {

    private Long audioGuideId;

    private String imageUrl;

    private String title;

    private List<String> tags;

    private List<String> audioFileUrl;

    @Builder
    private ResAudioGuideItemDto(Long audioGuideId, String imageUrl, String title, List<String> tags,
                                 List<String> audioFileUrl){
        this.audioGuideId=audioGuideId;
        this.imageUrl=imageUrl;
        this.title=title;
        this.tags=tags;
        this.audioFileUrl=audioFileUrl;
    }
}
