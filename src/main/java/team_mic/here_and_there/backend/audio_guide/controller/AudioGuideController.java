package team_mic.here_and_there.backend.audio_guide.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideCategory;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideCategoryListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideOrderingListDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;
import team_mic.here_and_there.backend.audio_guide.exception.WrongCategoryException;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
import team_mic.here_and_there.backend.audio_guide.service.AudioTrackService;

@Api(tags = "오디오 가이드 API")
@RestController
@RequiredArgsConstructor
public class AudioGuideController {

  private final AudioGuideService audioGuideService;
  private final AudioTrackService audioTrackService;

  @ApiOperation(value = "정렬기준(조회수, 재생수, 랜덤)으로 count 개수만큼의 오디오 가이드 리스트 조회",
      notes = "[order param 종류]\n" +
          "1.(메인 화면 상단, 오디오가이드 화면 more audio guides, more audio guides view all) random : 오디오 가이드가 count 개수 만큼 랜덤으로 제공됩니다.\n" +
          "2.(오디오가이드 화면 most popular, most popular view all) playingcount : 오디오 가이드가 재생수 기준으로 내림차순 정렬되어서 count 개수만큼 제공됩니다.\n" +
          "3.(오디오가이드 화면 trending) viewcount : 오디오 가이드가 조회수 기준으로 내림차순 정렬되어서 count 개수만큼 제공됩니다.\n" +
          "* order param 과 lan param 은 필수이지만, count 는 필수 param 이 아닙니다. view all 에서 사용해야 하는 전체 오디오가이드가 정렬기준으로 필요하다면 count param 없이 요청하세요.\n"+
          "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No Parameter Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/v1/audio-guides")
  public ResponseEntity<ResAudioGuideOrderingListDto> getTopNAudioGuidesListByOrder(
      @ApiParam(value = "가이드의 정렬 기준", required = true, example = "playingcount")
      @RequestParam(value = "order") String order,
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "응답받고 싶은 가이드 개수", example = "4")
      @RequestParam(value = "count", required = false) Integer guideCount) {

    if (order == null || language == null) {
      throw new NoParameterException();
    }

    if(!order.equals("viewcount") && !order.equals("playingcount") && !order.equals("random")){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); //TODO : custom exception
    }

    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.getTopNAudioGuidesListByOrder(order, guideCount, language));
  }


  @ApiOperation(value = "메인 화면의 카테고리별 오디오 가이드 리스트",
      notes = "[category param 종류]\n" +
          "1.(메인 화면 중간) history : History 카테고리에 해당되는 오디오 가이드 fix data 4개가 내려옵니다.\n" +
          "2.(메인 화면 하단) excursion : Excursion 카테고리에 해당되는 오디오 가이드 fix data 4개가 내려옵니다.\n" +
          "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No Parameter Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/v1/audio-guides/main")
  public ResponseEntity<ResAudioGuideCategoryListDto> getAudioGuideCategoryList(
      @ApiParam(value = "메인화면 오디오 가이드의 카테고리", required = true, example = "random")
      @RequestParam(value = "category") String category,
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language) {

    if (category == null || language == null) {
      throw new NoParameterException();
    }

    if(!category.equals(AudioGuideCategory.HISTORY.getQueryName()) &&
        !category.equals(AudioGuideCategory.EXCURSION.getQueryName())){
      throw new WrongCategoryException();
    }

    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.getAudioGuideCategoryList(category, language));
  }

  /*
  @ApiOperation(value = "오디오 가이드의 트랙들에 대한 Direction 폴리라인 위경도 정보",
      notes = "오디오 가이드의 Direction 폴리라인 위경도 리스트 정보를 제공합니다.\n "
          + "path-variable 에 오디오 가이드의 id를 넣어주세요.\n"
          + "위경도 정보에 대한 폴리라인 list 는 클라이언트 개발의 편의를 위해 [ {A점 위도, A점 경도}, {B점 위도, B점 경도}, {B점 위도, B점 경도}, {C점 위도, C점 경도}, {C점 위도, C점 경도},{D점 위도, D점 경도}...] 와 같은 형식의 포맷입니다.\n"
          + "현재 DB에 저장된 오디오 가이드의 id는 1~9까지 존재합니다.\n"
          + "For Test Application : **북촌 가이드의 id는 9입니다. 북촌 가이드의 경우에만 테스트가 가능합니다.**")
  @ApiImplicitParam(name = "audio-guide-id", value = "오디오 가이드의 id", required = true,
      dataType = "Long", paramType = "path", defaultValue = "9"
  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
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
  }*/
}