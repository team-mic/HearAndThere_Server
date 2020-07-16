package team_mic.here_and_there.backend.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;
import team_mic.here_and_there.backend.attraction.service.AttractionService;

import java.io.UnsupportedEncodingException;


@RequiredArgsConstructor
@RestController
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/attractions")
    public ResponseEntity<ResAreaAttractionsListDto> getAreaAttractions(@RequestParam(value = "area-code") Integer areaCode) throws UnsupportedEncodingException {
        if(areaCode == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.getAreaAttractions(areaCode));
    }
}
