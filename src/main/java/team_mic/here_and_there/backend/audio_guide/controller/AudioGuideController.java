package team_mic.here_and_there.backend.audio_guide.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
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

  private final AudioGuideRepository audioGuideRepository;
  private final AudioTrackRepository audioTrackRepository;
  private final AudioGuideTrackContainerRepository audioGuideTrackContainerRepository;
  private final TagRepository tagRepository;
  private final AudioGuideTagRepository audioGuideTagRepository;

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

  @ApiIgnore
  @PostMapping("/audio-guides/dump")
  public ResponseEntity<Void> addDumpData() {
    //강남
    Set<String> imageSet = new HashSet<>();
    imageSet.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/gangnam.png");
    AudioGuide audioGuide = AudioGuide.builder()
        .images(imageSet)
        .category("shopping")
        .title("The center of shopping and K-culture")
        .build();

    AudioTrack audioTrack = AudioTrack.builder()
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio-files/Whenever+Wherever.mp3")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide)
        .build();

    Tag tag1 = Tag.builder()
        .name("Gangnam")
        .build();
    AudioGuideTag audioGuideTag = AudioGuideTag.builder()
        .tag(tag1)
        .audioGuide(audioGuide)
        .build();

    audioGuideRepository.save(audioGuide);
    audioTrackRepository.save(audioTrack);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer);
    tagRepository.save(tag1);
    audioGuideTagRepository.save(audioGuideTag);

    //광화문
    Set<String> imageSet2 = new HashSet<>();
    imageSet2.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/gwanghwa.png");
    AudioGuide audioGuide2 = AudioGuide.builder()
        .images(imageSet2)
        .category("traditional")
        .title("History and culture breathe")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer2 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide2)
        .build();

    Tag tag2 = Tag.builder()
        .name("Seoul City Hall")
        .build();
    Tag tag3 = Tag.builder()
        .name("Gwanghwa")
        .build();
    AudioGuideTag audioGuideTag2 = AudioGuideTag.builder()
        .tag(tag2)
        .audioGuide(audioGuide2)
        .build();
    AudioGuideTag audioGuideTag3 = AudioGuideTag.builder()
        .tag(tag3)
        .audioGuide(audioGuide2)
        .build();

    audioGuideRepository.save(audioGuide2);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer2);
    tagRepository.save(tag2);
    tagRepository.save(tag3);
    audioGuideTagRepository.save(audioGuideTag2);
    audioGuideTagRepository.save(audioGuideTag3);

    //인사동
    Set<String> imageSet3 = new HashSet<>();
    imageSet3.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/insadong.png");
    AudioGuide audioGuide3 = AudioGuide.builder()
        .images(imageSet3)
        .category("traditional")
        .title("Filled with Hangeul")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer3 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide3)
        .build();

    Tag tag4 = Tag.builder()
        .name("Insa-dong")
        .build();
    AudioGuideTag audioGuideTag4 = AudioGuideTag.builder()
        .tag(tag4)
        .audioGuide(audioGuide3)
        .build();

    audioGuideRepository.save(audioGuide3);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer3);
    tagRepository.save(tag4);
    audioGuideTagRepository.save(audioGuideTag4);

    //종로
    Set<String> imageSet4 = new HashSet<>();
    imageSet4.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/jongno.png");
    AudioGuide audioGuide4 = AudioGuide.builder()
        .images(imageSet4)
        .category("traditional")
        .title("Ancient meets modern")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer4 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide4)
        .build();

    Tag tag5 = Tag.builder()
        .name("Jongno")
        .build();
    AudioGuideTag audioGuideTag5 = AudioGuideTag.builder()
        .tag(tag5)
        .audioGuide(audioGuide4)
        .build();

    audioGuideRepository.save(audioGuide4);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer4);
    tagRepository.save(tag5);
    audioGuideTagRepository.save(audioGuideTag5);

    //북촌
    Set<String> imageSet5 = new HashSet<>();
    imageSet5.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bukchon.png");
    AudioGuide audioGuide5 = AudioGuide.builder()
        .images(imageSet5)
        .category("traditional")
        .title("Beautiful Hanok cafes and restaurants")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer5 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide5)
        .build();

    Tag tag6 = Tag.builder()
        .name("Samcheongdong")
        .build();
    AudioGuideTag audioGuideTag6 = AudioGuideTag.builder()
        .tag(tag6)
        .audioGuide(audioGuide5)
        .build();
    Tag tag7 = Tag.builder()
        .name("Bukchon")
        .build();
    AudioGuideTag audioGuideTag7 = AudioGuideTag.builder()
        .tag(tag7)
        .audioGuide(audioGuide5)
        .build();

    audioGuideRepository.save(audioGuide5);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer5);
    tagRepository.save(tag6);
    audioGuideTagRepository.save(audioGuideTag6);
    tagRepository.save(tag7);
    audioGuideTagRepository.save(audioGuideTag7);

    //동대문
    Set<String> imageSet6 = new HashSet<>();
    imageSet6.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/dongdaemun.png");
    AudioGuide audioGuide6 = AudioGuide.builder()
        .images(imageSet6)
        .category("shopping")
        .title("The mecca of fashion and shopping")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer6 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide6)
        .build();

    Tag tag8 = Tag.builder()
        .name("Dongdaemun")
        .build();
    AudioGuideTag audioGuideTag8 = AudioGuideTag.builder()
        .tag(tag8)
        .audioGuide(audioGuide6)
        .build();

    audioGuideRepository.save(audioGuide6);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer6);
    tagRepository.save(tag8);
    audioGuideTagRepository.save(audioGuideTag8);

    //잠실
    Set<String> imageSet7 = new HashSet<>();
    imageSet7.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/jamsil.png");
    AudioGuide audioGuide7 = AudioGuide.builder()
        .images(imageSet7)
        .category("shopping")
        .title("The center of shopping and entertainment")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer7 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide7)
        .build();

    Tag tag9 = Tag.builder()
        .name("Jamsil")
        .build();
    AudioGuideTag audioGuideTag9 = AudioGuideTag.builder()
        .tag(tag9)
        .audioGuide(audioGuide7)
        .build();

    audioGuideRepository.save(audioGuide7);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer7);
    tagRepository.save(tag9);
    audioGuideTagRepository.save(audioGuideTag9);

    //남대문
    Set<String> imageSet8 = new HashSet<>();
    imageSet8.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/namdaemun.png");
    AudioGuide audioGuide8 = AudioGuide.builder()
        .images(imageSet8)
        .category("shopping")
        .title("Best-known shopping street and attraction")
        .build();

    AudioGuideTrackContainer audioGuideTrackContainer8 = AudioGuideTrackContainer.builder()
        .orderNumber(1)
        .audioTrack(audioTrack)
        .audioGuide(audioGuide8)
        .build();

    Tag tag10 = Tag.builder()
        .name("Myeongdong")
        .build();
    AudioGuideTag audioGuideTag10 = AudioGuideTag.builder()
        .tag(tag10)
        .audioGuide(audioGuide8)
        .build();
    Tag tag11 = Tag.builder()
        .name("Namdaemun")
        .build();
    AudioGuideTag audioGuideTag11 = AudioGuideTag.builder()
        .tag(tag11)
        .audioGuide(audioGuide8)
        .build();

    audioGuideRepository.save(audioGuide8);
    audioGuideTrackContainerRepository.save(audioGuideTrackContainer8);
    tagRepository.save(tag10);
    audioGuideTagRepository.save(audioGuideTag10);
    tagRepository.save(tag11);
    audioGuideTagRepository.save(audioGuideTag11);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
