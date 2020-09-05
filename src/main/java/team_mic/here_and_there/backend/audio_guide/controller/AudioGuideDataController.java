package team_mic.here_and_there.backend.audio_guide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideDataService;

@RequiredArgsConstructor
@RestController
public class AudioGuideDataController {

  private final AudioGuideDataService audioGuideDataService;

  @ApiIgnore
  @PostMapping("/audio-guides/audio-tracks/dump")
  public ResponseEntity<Void> insertDumpTracksIntoGuides() {

    audioGuideDataService.insertDumpTracksIntoGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/audio-guides/dump")
  public ResponseEntity<Void> insertDumpGuides() {

    audioGuideDataService.insertDumpGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @DeleteMapping("/audio-guides/audio-tracks/dump")
  public ResponseEntity<Void> deleteAllGuideTrackContainers() {

    audioGuideDataService.deleteAllGuideTrackContainers();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/audio-guides/bukchon")
  public ResponseEntity<Void> insertBukchonGuides() {

    audioGuideDataService.insertBukchonGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/audio-guides/audio-tracks/bukchon")
  public ResponseEntity<Void> insertBukchonTracksIntoGuide() {

    audioGuideDataService.insertBukchonTracksIntoGuide();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
