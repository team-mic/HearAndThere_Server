package team_mic.here_and_there.backend.map_banner.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.map_banner.domain.entity.MapZone;
import team_mic.here_and_there.backend.map_banner.domain.repository.MapZoneRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MapZoneTest {
  @Autowired
  private MapZoneRepository mapZoneRepository;

  @Test
  public void MapZone_엔티티_생성_테스트(){
    //given
    MapZone mapZone = MapZone.builder()
        .zoneName("마포-은평")
        .parentArea("seoul")
        .language(Language.KOREAN)
        .entryPointLatitude(37.55644856502666)
        .entryPointLongitude(126.91757322671965)
        .build();

    //when
    MapZone savedMapZone = mapZoneRepository.save(mapZone);

    //then
    assertEquals(savedMapZone.getId(), 1);
    assertEquals(savedMapZone.getZoneName(), "마포-은평");
  }

  @Test
  public void 부모_areaName_테스트(){
    //given
    String existParentAreaName = "seoul";
    String notExistParentAreaName = "incheon";

    MapZone korMapZone = MapZone.builder()
        .parentArea(existParentAreaName)
        .language(Language.KOREAN)
        .build();

    MapZone engMapZone = MapZone.builder()
        .parentArea(existParentAreaName)
        .language(Language.ENGLISH)
        .build();

    //when
    mapZoneRepository.save(korMapZone);
    mapZoneRepository.save(engMapZone);

    //then
    assertEquals(mapZoneRepository.existsByLanguageAndParentArea(Language.ENGLISH, existParentAreaName), true);
    assertEquals(mapZoneRepository.existsByLanguageAndParentArea(Language.KOREAN, existParentAreaName), true);
    assertEquals(mapZoneRepository.existsByLanguageAndParentArea(Language.ENGLISH, notExistParentAreaName), false);
  }

  @Test
  public void 같은_언어버전과_부모지역의_MapZoneList_keyZone_오름차순_정렬_테스트(){
    //given
    String givenAreaName = "seoul";
    Language givenLanguage = Language.KOREAN;

    List<MapZone> givenMapZoneList = List.of(
        MapZone.builder()
            .zoneKey(2)
            .parentArea(givenAreaName)
            .language(givenLanguage)
            .build(),
        MapZone.builder()
            .zoneKey(1)
            .parentArea(givenAreaName)
            .language(givenLanguage)
            .build(),
        MapZone.builder()
            .zoneKey(3)
            .parentArea(givenAreaName)
            .language(givenLanguage)
            .build());

    //when
    mapZoneRepository.saveAll(givenMapZoneList);

    List<MapZone> savedMapZoneList = mapZoneRepository.findAllByParentAreaAndLanguageOrderByZoneKeyAsc(givenAreaName, givenLanguage);

    //then
    assertEquals(savedMapZoneList.get(0).getZoneKey(), 1);
    assertEquals(savedMapZoneList.get(2).getZoneKey(), 3);
  }

  @Test
  public void 다른_언어버전과_부모지역의_MapZoneList_keyZone_오름차순_정렬_테스트(){
    //given
    String givenAreaName1 = "seoul";
    String givenAreaName2 = "incheon";
    Language givenKorLan = Language.KOREAN;
    Language givenEngLan = Language.ENGLISH;

    List<MapZone> givenMapZoneList = List.of(
        MapZone.builder()
            .zoneKey(2)
            .parentArea(givenAreaName1)
            .language(givenKorLan)
            .build(),
        MapZone.builder()
            .zoneKey(1)
            .parentArea(givenAreaName1)
            .language(givenKorLan)
            .build(),

        MapZone.builder()
            .zoneKey(3)
            .parentArea(givenAreaName2)
            .language(givenEngLan)
            .build(),
        MapZone.builder()
            .zoneKey(2)
            .parentArea(givenAreaName1)
            .language(givenEngLan)
            .build(),
        MapZone.builder()
            .zoneKey(1)
            .parentArea(givenAreaName1)
            .language(givenEngLan)
            .build());

    //when
    mapZoneRepository.saveAll(givenMapZoneList);

    List<MapZone> savedKorMapZoneList = mapZoneRepository.findAllByParentAreaAndLanguageOrderByZoneKeyAsc(
        givenAreaName1,
        Language.KOREAN);
    List<MapZone> savedEngMapZoneList = mapZoneRepository.findAllByParentAreaAndLanguageOrderByZoneKeyAsc(
        givenAreaName2,
        Language.ENGLISH);

    //then
    assertEquals(savedKorMapZoneList.get(1).getParentArea(), givenAreaName1);
    assertEquals(savedKorMapZoneList.get(0).getZoneKey(), 1);
    assertEquals(savedEngMapZoneList.size(), 1);
    assertEquals(savedEngMapZoneList.get(0).getParentArea(), givenAreaName2);
  }
}
