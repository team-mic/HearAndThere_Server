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
  @PostMapping("/audio-courses/bukchon")
  public ResponseEntity<Void> insertBukchonCourses(){

    audioCourseDataService.insertBukchonCourses();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
