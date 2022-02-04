package team_mic.here_and_there.backend.map_banner.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.map_banner.service.MapZoneDataService;

@RequiredArgsConstructor
@RestController
public class MapZoneDataController {
  private final MapZoneDataService mapZoneDataService;

  @ApiIgnore
  @PostMapping("/data/map-zones")
  public ResponseEntity<Void> insertMapZones() {

    mapZoneDataService.insertMapZones();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
