package team_mic.here_and_there.backend.audio_guide.controller;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideDumpService;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;

@RequiredArgsConstructor
@RestController
public class AudioGuideDumpController {

  private final AudioGuideDumpService audioGuideDumpService;

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
