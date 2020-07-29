package team_mic.here_and_there.backend.audio_guide.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideTrackContainerRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioTrackRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoListDto;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
import team_mic.here_and_there.backend.audio_guide.service.AudioTrackService;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.location_tag.domain.repository.TagRepository;

import java.util.HashSet;
import java.util.Set;

@Api(tags = "오디오 가이드 API")
@RestController
@RequiredArgsConstructor
public class AudioGuideController {

  private final AudioGuideService audioGuideService;
  private final AudioTrackService audioTrackService;

  @ApiOperation(value = "메인 화면의 카테고리별 오디오 가이드 리스트",
      notes = "[파라미터 category 종류]\n" +
          "1.(메인 화면 상단) random : traditional 과 shopping 오디오 가이드 5개가 랜덤으로 섞여서 내려옵니다.\n" +
          "2.(메인 화면 중간) traditional : traditional 카테고리에 해당되는 오디오 가이드 4개가 내려옵니다.\n" +
          "3.(메인 화면 하단) shopping : shopping 카테고리에 해당되는 오디오 가이드 4개가 내려옵니다.\n" +
          "현재 덤프 데이터입니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No 'category' Parameter Error")
  })
  @GetMapping("/audio-guides")
  public ResponseEntity<ResAudioGuideListDto> getAudioGuideList(
      @ApiParam(value = "오디오 가이드의 카테고리 : random / traditional / shopping 중 하나", required = true, example = "random")
      @RequestParam(value = "category") String category) {

    if (category == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.getAudioGuideList(category));
  }

  @GetMapping("/audio-guides/{audio-guide-id:^[0-9]+$}/audio-tracks")
  public ResponseEntity<ResAudioTrackInfoListDto> getAudioGuidesTrackList(
      @PathVariable("audio-guide-id") Long audioGuideId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(audioTrackService.getAudioGuidesTrackList(audioGuideId));
  }
}
