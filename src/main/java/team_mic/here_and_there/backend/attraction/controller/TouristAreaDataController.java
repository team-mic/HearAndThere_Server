package team_mic.here_and_there.backend.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.attraction.service.TouristAreaDataService;

@RequiredArgsConstructor
@RestController
public class TouristAreaDataController {

    private final TouristAreaDataService touristAreaDataService;

    @ApiIgnore
    @PostMapping("/data/tourist-areas/images/kor")
    public ResponseEntity<Void> insertKoreanTouristAreaImages(){

        touristAreaDataService.insertKoreanTouristAreaImages();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiIgnore
    @PostMapping("/data/tourist-areas/images/eng")
    public ResponseEntity<Void> insertEnglishTouristAreaImages(){

        touristAreaDataService.insertEnglishTouristAreaImages();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //임시
    @ApiIgnore
    @PostMapping("/data/tourist-areas/attractions-count")
    public ResponseEntity<Void> insertTouristAreaAttractionsCount(){
        touristAreaDataService.insertTouristAreaAttractionsCount();

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
