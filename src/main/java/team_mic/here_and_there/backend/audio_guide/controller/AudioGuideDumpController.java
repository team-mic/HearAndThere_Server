package team_mic.here_and_there.backend.audio_guide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideDumpService;

@RequiredArgsConstructor
@RestController
public class AudioGuideDumpController {

  private final AudioGuideDumpService audioGuideDumpService;

  @ApiIgnore
  @PostMapping("/audio-guides/audio-tracks/dump")
  public ResponseEntity<Void> insertDumpTracksIntoGuides() {

    audioGuideDumpService.insertDumpTracksIntoGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/audio-guides/dump")
  public ResponseEntity<Void> insertDumpGuides() {

    audioGuideDumpService.insertDumpGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
