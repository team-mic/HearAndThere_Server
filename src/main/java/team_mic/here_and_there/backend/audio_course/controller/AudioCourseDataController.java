package team_mic.here_and_there.backend.audio_course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_course.service.AudioCourseDataService;

@RequiredArgsConstructor
@RestController
public class AudioCourseDataController {

  private final AudioCourseDataService audioCourseDataService;
  @ApiIgnore
  @PostMapping("/data/audio-courses/gongneung")
  public ResponseEntity<Void> insertGongneungCourses(){

    audioCourseDataService.insertGongneungCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/namsan")
  public ResponseEntity<Void> insertNamsanCourses(){

    audioCourseDataService.insertNamsanCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/noryangjin")
  public ResponseEntity<Void> insertNoryangjinCourses(){

    audioCourseDataService.insertNoryangjinCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/daehakro")
  public ResponseEntity<Void> insertDaehakroCourses(){

    audioCourseDataService.insertDaehakroCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/dongdaemun")
  public ResponseEntity<Void> insertDongdaemunCourses(){

    audioCourseDataService.insertDongdaemunCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/mangwon")
  public ResponseEntity<Void> insertMangwonCourses(){

    audioCourseDataService.insertMangwonCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/bukchon")
  public ResponseEntity<Void> insertBukchonCourses(){

    audioCourseDataService.insertBukchonCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/jamsil")
  public ResponseEntity<Void> insertJamsilCourses(){

    audioCourseDataService.insertJamsilCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/yongsan")
  public ResponseEntity<Void> insertYongSanCourses(){

    audioCourseDataService.insertYongSanCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/olympic-park")
  public ResponseEntity<Void> insertOlympicParkCourses(){

    audioCourseDataService.insertOlympicParkCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/insadong")
  public ResponseEntity<Void> insertInsadongCourses(){

    audioCourseDataService.insertInsadongCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/deoksugung-palace")
  public ResponseEntity<Void> insertDeoksugungPalaceCourses(){

    audioCourseDataService.insertDeoksugungPalaceCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/gyeongbokgung-palace")
  public ResponseEntity<Void> insertGyeongbokgungPalaceCourses(){

    audioCourseDataService.insertGyeongbokgungPalaceCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/changdeokgung-palace")
  public ResponseEntity<Void> insertChangdeokgungPalaceCourses(){

    audioCourseDataService.insertChangdeokgungPalaceCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/yeouido")
  public ResponseEntity<Void> insertYeouidoCourses(){

    audioCourseDataService.insertYeouidoCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/yeongdeungpo")
  public ResponseEntity<Void> insertYeongdeungpoCourses(){

    audioCourseDataService.insertYeongdeungpoCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/hongdae")
  public ResponseEntity<Void> insertHongdaeCourses(){

    audioCourseDataService.insertHongdaeCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-courses/buam-dong")
  public ResponseEntity<Void> insertBuamdongCourses(){

    audioCourseDataService.insertBuamdongCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
