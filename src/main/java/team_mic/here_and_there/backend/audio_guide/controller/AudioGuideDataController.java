package team_mic.here_and_there.backend.audio_guide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideDataService;

@RequiredArgsConstructor
@RestController
public class AudioGuideDataController {

  private final AudioGuideDataService audioGuideDataService;

  @ApiIgnore
  @PostMapping("/data/audio-guides/gongneung")
  public ResponseEntity<Void> insertGongneungGuides() {

    audioGuideDataService.insertGongneungGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/gongneung")
  public ResponseEntity<Void> insertGongneungTracks() {

    audioGuideDataService.insertGongneungTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/namsan")
  public ResponseEntity<Void> insertNamsanGuides() {

    audioGuideDataService.insertNamsanGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/namsan")
  public ResponseEntity<Void> insertNamsanTracks() {

    audioGuideDataService.insertNamsanTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/noryanjin")
  public ResponseEntity<Void> insertNoryanjinGuides() {

    audioGuideDataService.insertNoryanjinGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/noryanjin")
  public ResponseEntity<Void> insertNoryanjinTracks() {

    audioGuideDataService.insertNoryanjinTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/daehak-ro")
  public ResponseEntity<Void> insertDaehakRoGuides() {

    audioGuideDataService.insertDaehakRoGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/daehak-ro")
  public ResponseEntity<Void> insertDaehakRoTracks() {

    audioGuideDataService.insertDaehakRoTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/dongdaemun")
  public ResponseEntity<Void> insertDongdaemunGuides() {

    audioGuideDataService.insertDongdaemunGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/dongdaemun")
  public ResponseEntity<Void> insertDongdaemunTracks() {

    audioGuideDataService.insertDongdaemunTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/mangwon")
  public ResponseEntity<Void> insertMangwonGuides() {

    audioGuideDataService.insertMangwonGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/mangwon")
  public ResponseEntity<Void> insertMangwonTracks() {

    audioGuideDataService.insertMangwonTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/bukchon")
  public ResponseEntity<Void> insertBukchonGuides() {

    audioGuideDataService.insertBukchonGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/bukchon")
  public ResponseEntity<Void> insertBukchonTracks() {

    audioGuideDataService.insertBukchonTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/jamsil/good-neighborhood")
  public ResponseEntity<Void> insertJamsilGoodNeighborhoodGuides() {

    audioGuideDataService.insertJamsilGoodNeighborhoodGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/jamsil/good-neighborhood")
  public ResponseEntity<Void> insertJamsilGoodNeighborhoodTracks() {

    audioGuideDataService.insertJamsilGoodNeighborhoodTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/jamsil/special-tourist")
  public ResponseEntity<Void> insertJamsilSpecialTouristGuides() {

    audioGuideDataService.insertJamsilSpecialTouristGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/jamsil/special-tourist")
  public ResponseEntity<Void> insertJamsilSpecialTouristTracks() {

    audioGuideDataService.insertJamsilSpecialTouristTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/yongsan/center-of-seoul")
  public ResponseEntity<Void> insertYongsanCenterOfSeoulGuides() {

    audioGuideDataService.insertYongsanCenterOfSeoulGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/yongsan/center-of-seoul")
  public ResponseEntity<Void> insertYongsanCenterOfSeoulTracks() {

    audioGuideDataService.insertYongsanCenterOfSeoulTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/yongsan/history")
  public ResponseEntity<Void> insertYongsanHistoryGuides() {

    audioGuideDataService.insertYongsanHistoryGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/yongsan/history")
  public ResponseEntity<Void> insertYongsanHistoryTracks() {

    audioGuideDataService.insertYongsanHistoryTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/olympic-park")
  public ResponseEntity<Void> insertOlympicParkGuides() {

    audioGuideDataService.insertOlympicParkGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/olympic-park")
  public ResponseEntity<Void> insertOlympicParkTracks() {

    audioGuideDataService.insertOlympicParkTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/insadong")
  public ResponseEntity<Void> insertInsadongGuides() {

    audioGuideDataService.insertInsadongGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/insadong")
  public ResponseEntity<Void> insertInsadongTracks() {

    audioGuideDataService.insertInsadongTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/deoksugung-palace")
  public ResponseEntity<Void> insertDeoksugungPalaceGuides() {

    audioGuideDataService.insertDeoksugungPalaceGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/deoksugung-palace")
  public ResponseEntity<Void> insertDeoksugungPalaceTracks() {

    audioGuideDataService.insertDeoksugungPalaceTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
  @ApiIgnore
  @PostMapping("/data/audio-guides/gyeongbokgung-palace")
  public ResponseEntity<Void> insertGyeongbokgungPalaceGuides() {

    audioGuideDataService.insertGyeongbokgungPalaceGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/gyeongbokgung-palace")
  public ResponseEntity<Void> insertGyeongbokgungPalaceTracks() {

    audioGuideDataService.insertGyeongbokgungPalaceTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
  @ApiIgnore
  @PostMapping("/data/audio-guides/changdeokgung-palace")
  public ResponseEntity<Void> insertChangdeokgungPalaceGuides() {

    audioGuideDataService.insertChangdeokgungPalaceGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/changdeokgung-palace")
  public ResponseEntity<Void> insertChangdeokgungPalaceTracks() {

    audioGuideDataService.insertChangdeokgungPalaceTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
  @ApiIgnore
  @PostMapping("/data/audio-guides/yeouido")
  public ResponseEntity<Void> insertYeouidoeGuides() {

    audioGuideDataService.insertYeouidoeGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/yeouido")
  public ResponseEntity<Void> insertYeouidoeTracks() {

    audioGuideDataService.insertYeouidoeTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
  @ApiIgnore
  @PostMapping("/data/audio-guides/yeongdeungpo")
  public ResponseEntity<Void> insertYeongdeungpoGuides() {

    audioGuideDataService.insertYeongdeungpoGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/yeongdeungpo")
  public ResponseEntity<Void> insertYeongdeungpoTracks() {

    audioGuideDataService.insertYeongdeungpoTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/hongdae")
  public ResponseEntity<Void> insertHongdaeGuides() {

    audioGuideDataService.insertHongdaeGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/hongdae")
  public ResponseEntity<Void> insertHongdaeTracks() {

    audioGuideDataService.insertHongdaeTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/buam-dong")
  public ResponseEntity<Void> insertBuamdongGuides() {

    audioGuideDataService.insertBuamdongGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/audio-guides/audio-tracks/buam-dong")
  public ResponseEntity<Void> insertBuamdongTracks() {

    audioGuideDataService.insertBuamdongTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PutMapping("/data/audio-guides/audio-tracks/yongsan/history")
  public ResponseEntity<Void> modifyYongsanHistoryTracks(){
    audioGuideDataService.modifyYongsanHistoryTracks();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PutMapping("/data/audio-guides/sub-categories")
  public ResponseEntity<Void> addSubCategoryToGuides(){
    audioGuideDataService.addSubCategoryToGuides();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
