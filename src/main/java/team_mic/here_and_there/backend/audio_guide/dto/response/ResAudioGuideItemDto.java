package team_mic.here_and_there.backend.audio_guide.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResAudioGuideItemDto {

    @ApiModelProperty(notes = "오디오 가이드 id")
    private Long audioGuideId;

    @ApiModelProperty(notes = "오디오 가이드 메인 이미지 url")
    private String imageUrl;

    @ApiModelProperty(notes = "오디오 가이드 제목")
    private String title;

    @ApiModelProperty(notes = "오디오 가이드의 태그들")
    private List<String> tags;

    @ApiModelProperty(notes = "오디오 가이드 오디오파일 url(테스트 필요)")
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
