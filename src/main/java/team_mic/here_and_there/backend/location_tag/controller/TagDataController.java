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

  //test 용
  @ApiIgnore
  @PostMapping("/data/tags/gongneung")
  public ResponseEntity<Void> insertGongneungTag() {

    tagDataService.insertGongneungTag();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/tags/gongneung-to-insadong")
  public ResponseEntity<Void> insertTagsFromGongneungToInsadong() {

    tagDataService.insertTagsFromGongneungToInsadong();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/tags/deoksugung-to-buamdong")
  public ResponseEntity<Void> insertTagsFromDeoksugungToBuamdong() {

    tagDataService.insertTagsFromDeoksugungToBuamdong();

    return ResponseEntity.status(HttpStatus.OK).build();
  }


}
