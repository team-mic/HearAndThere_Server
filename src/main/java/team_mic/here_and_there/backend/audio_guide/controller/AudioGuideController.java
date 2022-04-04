package team_mic.here_and_there.backend.audio_guide.controller;

import io.swagger.annotations.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideSubCategoryDetailDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideDirectionsDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideLocationListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideOrderingListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideSubCategoryListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResPatchedSingleAudioGuideDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResSingleAudioGuideDetailDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;
import team_mic.here_and_there.backend.audio_guide.exception.WrongCategoryException;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;

@Api(tags = "오디오 가이드 API")
@RestController
@RequiredArgsConstructor
public class AudioGuideController {

  private final AudioGuideService audioGuideService;


  @ApiOperation(value = "[지도탭] 전체 오디오 가이드의 첫 트랙 위경도 리스트 및 가이드 대표 정보 조회",
      notes = "* 전체 오디오 가이드의 첫 트랙 point 위경도 리스트와 가이드의 대표 정보를 제공합니다.\n"+
          "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/audio-guides/point-locations")
  public ResponseEntity<ResAudioGuideLocationListDto> getAudioGuideLocationList(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(audioGuideService.getAudioGuideLocationMap(language));
  }

  @ApiOperation(value = "오디오 가이드 상세 페이지 조회",
      notes = "* 오디오 가이드 id 에 해당하는 상세페이지 정보를 제공합니다.\n"
          + "[제공되는 정보]\n"
          + "1. 오디오 가이드의 기본 상세 정보 \n"
          + "2. 오디오 가이드의 트랙별 정보 리스트(재생 트랙 순서대로 정렬)\n"
          + "3. 오디오 가이드의 코스 요소별 정보 리스트(코스 요소 순서대로 정렬)\n"
          + "4. 해당 오디오 가이드의 추천 가이드 리스트\n"
          + "5. 해당 오디오 가이드의 추천 글콘텐츠(현재 기획 상 없으므로 항상 빈리스트가 내려갑니다.)\n"+
          "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/audio-guides/{audio-guide-id:^[0-9]+$}")
  public ResponseEntity<ResSingleAudioGuideDetailDto> getSingleAudioGuideDetail(
      @ApiParam(value = "오디오 가이드 id", required = true, example = "1")
      @PathVariable(value = "audio-guide-id") Long audioGuideId,
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "HLS support", example = "true")
      @RequestParam(value = "hls", required = false) Boolean isHlsSupport){


    return ResponseEntity.status(HttpStatus.OK).body(
        audioGuideService.getSingleAudioGuideDetail(audioGuideId, language, isHlsSupport));
  }

  @ApiOperation(value = "오디오 가이드의 조회수/재생수 업데이트",
      notes = "* 오디오 가이드를 클릭하여 상세보기로 넘어갈 경우, viewcount 를 업데이트 해야합니다.\n"
          + "* 오디오 가이드를 재생할 경우, playcount 를 업데이트 해야합니다.\n"
          + "[update-field param 종류]\n"
          + "1. viewcount : 조회수\n"
          + "2. playcount : 재생수\n"
          + "* 반드시 PATCH로 요청해주세요. response 의 경우 update 완료된 조회수를 제공합니다.\n"
          + "* 언어 버전에 맞는 정보가 업데이트 됩니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "parameter Error"),
      @ApiResponse(code = 404, message = "No audio guide data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @PatchMapping("/v1/audio-guides/{audio-guide-id:^[0-9]+$}")
  public ResponseEntity<ResPatchedSingleAudioGuideDto> updateAudioGuideCountingColumn(
      @PathVariable(value = "audio-guide-id") Long audioGuideId,
      @ApiParam(value = "update 할 필드", required = true, example = "playcount")
      @RequestParam(value = "update-field") String updateField,
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language){
    if (!updateField.equals("viewcount") && !updateField.equals("playcount")) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); //TODO : custom exception
    }

    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.updateAudioGuideCountingColumn(audioGuideId, updateField, language));
  }

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
          "1.(메인 화면 중간) art : Art 카테고리에 해당되는 오디오 가이드 4개가 내려옵니다.\n" +
          "2.(메인 화면 하단) excursion : Excursion 카테고리에 해당되는 오디오 가이드 3개가 내려옵니다.\n" +
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
  public ResponseEntity<ResAudioGuideSubCategoryDetailDto> getAudioGuideMainCategoryListV1(
      @ApiParam(value = "메인화면 오디오 가이드의 카테고리", required = true, example = "art")
      @RequestParam(value = "category") String category,
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language) {

    if (category == null || language == null) {
      throw new NoParameterException();
    }

    if(!category.equals("art") && !category.equals("excursion")){
      throw new WrongCategoryException();
    }

    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.getAudioGuideMainCategoryListV1(category, language));
  }

  @ApiOperation(value = "[v2] 메인 화면의 사용자 관심 랜덤 추천 카테고리 오디오 가이드 리스트",
      notes = "메인화면에는 사용자가 관심있는 2개의 랜덤 소카테고리에 해당하는 가이드 리스트가 내려옵니다.\n" +
          "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No Parameter Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/v2/audio-guides/categories/recommended")
  public ResponseEntity<List<ResAudioGuideSubCategoryDetailDto>> getAudioGuideSubCategoryListV2(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    return ResponseEntity.status(HttpStatus.OK).body(audioGuideService.getAudioGuideSubCategoryListV2(language));
  }

  @ApiOperation(value = "오디오 가이드의 트랙들에 대한 Point 위경도와 Direction 폴리라인 위경도 정보",
      notes = "오디오 가이드의 각 트랙 Point 들의 위경도 리스트 정보와 Direction 폴리라인 위경도 리스트 정보를 제공합니다.\n "
          + "path-variable 에 오디오 가이드의 id를 넣어주세요.\n"
          + "위경도 정보에 대한 폴리라인 list 는 클라이언트 개발의 편의를 위해 [ {A점 위도, A점 경도}, {B점 위도, B점 경도}, {B점 위도, B점 경도}, {C점 위도, C점 경도}, {C점 위도, C점 경도},{D점 위도, D점 경도}...] 와 같은 형식의 포맷입니다.\n")
  @ApiImplicitParam(name = "audio-guide-id", value = "오디오 가이드의 id", required = true,
      dataType = "Long", paramType = "path", defaultValue = "1"
  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/v1/audio-guides/{audio-guide-id:^[0-9]+$}/directions")
  public ResponseEntity<ResAudioGuideDirectionsDto> getAudioGuideDirections(
      @PathVariable(value = "audio-guide-id") Long audioGuideId) throws InterruptedException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(audioGuideService.getAudioGuideDirections(audioGuideId));
  }

  @ApiOperation(value = "오디오 가이드 카테고리 테마 리스트",
      notes = "* 오디오 가이드의 소카테고리 리스트(카테고리 테마 이름, 배너 배경색, 배너 아이콘 이미지 svg, 지역, 가이드 개수)를 제공합니다.\n "
          + "* 검색 탭 메인화면의 상단 추천 테마에 사용됩니다.\n")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "No corresponding Audio guide Data in DB")
  })
  @GetMapping("/v1/audio-guides/categories/sub")
  public ResponseEntity<ResAudioGuideSubCategoryListDto> getAudioGuideSubCategoryList(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language){
    return ResponseEntity.status(HttpStatus.OK)
        .body(audioGuideService.getAudioGuideSubCategoryList(language));
  }

  @ApiOperation(value = "오디오 가이드 카테고리 테마 Detail 리스트",
      notes = "* 소카테고리 id 에 해당되는 오디오 가이드 리스트를 제공합니다.\n "
          + "* 검색 탭의 테마 상세페이지에 사용됩니다.\n")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Client Request Path Variable Error : No such subcategory id")
  })
  @GetMapping("/v1/audio-guides/categories/sub/{sub-category-id}")
  public ResponseEntity<ResAudioGuideSubCategoryDetailDto> getAudioGuideListOfSubCategoryId(
      @ApiParam(value = "소카테고리 id", required = true, example = "1")
      @PathVariable(value = "sub-category-id") Integer subCategoryId,
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language){
    return ResponseEntity.status(HttpStatus.OK)
        .body(audioGuideService.getAudioGuideListOfSubCategoryId(language, subCategoryId));
  }
  /*
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