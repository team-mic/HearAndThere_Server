package team_mic.here_and_there.backend.audio_guide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_guide.service.CategoryDataService;

@RequiredArgsConstructor
@RestController
public class CategoryDataController {
  private final CategoryDataService categoryDataService;

  @ApiIgnore
  @PostMapping("/data/categories")
  public ResponseEntity<Void> insertCategories() {

    categoryDataService.insertCategories();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/categories")
  public ResponseEntity<Void> insertCategoriesToGuides() {

    categoryDataService.insertCategoriesToGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
