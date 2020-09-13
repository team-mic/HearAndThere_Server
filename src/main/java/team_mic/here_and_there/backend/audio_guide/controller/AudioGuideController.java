package team_mic.here_and_there.backend.audio_guide.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideDirectionsDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResNearestAudioTrackDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoCategoryParameterException;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
import team_mic.here_and_there.backend.audio_guide.service.AudioTrackService;

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
      @ApiResponse(code = 400, message = "No 'category' Parameter Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/audio-guides")
  public ResponseEntity<ResAudioGuideListDto> getAudioGuideList(
      @ApiParam(value = "오디오 가이드의 카테고리 : random / traditional / shopping 중 하나", required = true, example = "random")
      @RequestParam(value = "category") String category) {

    if (category == null) {
      throw new NoCategoryParameterException();
    }

    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.getAudioGuideList(category));
  }

  @GetMapping("/audio-guides/{audio-guide-id:^[0-9]+$}/directions")
  public ResponseEntity<ResAudioGuideDirectionsDto> getAudioGuideDirections(
      @PathVariable(value = "audio-guide-id") Long audioGuideId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(audioGuideService.getAudioGuideDirections(audioGuideId));
  }

  @ApiOperation(value = "사용자 위치(위도,경도) 기반 반경 내 오디오 트랙의 정보",
      notes = "사용자의 위도,경도를 기준으로 반경 50m 이내에 존재하는 트랙의 정보를 제공합니다.\n "
          + "path-variable 에 오디오 가이드의 id를 넣어주세요.\n"
          + "user-latitude param 과 user-longitude param 에 사용자의 현재 위치 정보를 넣어주세요.\n"
          + "현재 DB에 저장된 오디오 가이드의 id는 1~9까지 존재합니다.\n"
          + "For Test Application : **북촌 가이드의 id는 9입니다. 북촌 가이드의 경우에만 테스트가 가능합니다.**")
  @ApiImplicitParam(name = "audio-guide-id", value = "오디오 가이드의 id", required = true,
      dataType = "Long", paramType = "path", defaultValue = "9"
  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No 'user-latitude' or 'user-longitude' param Error")
  })
  @GetMapping("/audio-guides/{audio-guide-id:^[0-9]+$}/location-based/audio-tracks")
  public ResponseEntity<ResNearestAudioTrackDto> getNearestAudioTracks(
      @PathVariable(value = "audio-guide-id") Long audioGuideId,
      @ApiParam(value = "사용자의 현재 위도", required = true, example = "37.578952")
      @RequestParam(value = "user-latitude") Double userLatitude,
      @ApiParam(value = "사용자의 현재 경도", required = true, example = "126.986701")
      @RequestParam(value = "user-longitude") Double userLongitude) {

    if (userLatitude == null || userLongitude == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(audioTrackService.getNearestAudioTracks(audioGuideId, userLatitude, userLongitude));
  }

  @ApiOperation(value = "오디오 가이드에 속한 오디오 트랙들의 리스트",
      notes = "해당 id의 오디오 가이드에 포함된 오디오 트랙들의 정보를 리스트로 제공합니다.\n "
          + "path-variable 에 오디오 가이드의 id를 넣어주세요.\n"
          + "현재 DB에 저장된 오디오 가이드의 id는 1~9까지 존재합니다.\n"
          + "오디오 가이드 id=1~8까지는 덤프 데이터로 모든 오디오 가이드가 동일한 10개의 오디오 트랙을 가지고 있습니다.\n"
          + "For Test Application : 북촌 가이드의 id는 9입니다.")
  @ApiImplicitParam(name = "audio-guide-id", value = "오디오 가이드의 id", required = true,
      dataType = "Long", paramType = "path", defaultValue = "1"
  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No 'audio-guide-id' path variable Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/audio-guides/{audio-guide-id:^[0-9]+$}/audio-tracks")
  public ResponseEntity<ResAudioTrackInfoListDto> getAudioGuidesTrackList(
      @PathVariable(value = "audio-guide-id") Long audioGuideId) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(audioTrackService.getAudioGuidesTrackList(audioGuideId));
  }
}