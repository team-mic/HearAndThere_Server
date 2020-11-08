package team_mic.here_and_there.backend.location_tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.location_tag.service.TagDataService;

@RequiredArgsConstructor
@RestController
public class TagDataController {

  private final TagDataService tagDataService;

  @ApiIgnore
  @PostMapping("/data/tags")
  public ResponseEntity<Void> insertTagsFromGongneungToInsadong() {

    tagDataService.insertTagsFromGongneungToInsadong();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
