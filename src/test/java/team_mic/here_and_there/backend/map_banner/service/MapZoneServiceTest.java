package team_mic.here_and_there.backend.map_banner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.map_banner.domain.entity.MapZone;
import team_mic.here_and_there.backend.map_banner.domain.repository.MapZoneRepository;
import team_mic.here_and_there.backend.map_banner.dto.response.ResMapBannerZonesListDto;
import team_mic.here_and_there.backend.map_banner.exception.InvalidParentAreaNameException;

@ExtendWith(MockitoExtension.class)
public class MapZoneServiceTest {
  @Mock
  private MapZoneRepository mapZoneRepository;

  @InjectMocks
  private MapZoneService mapZoneService;

  @Test
  public void getMapBannerZonesList_LIST_타입_테스트() {
    //given
    Language givenLanguage = Language.KOREAN;
    String givenParentAreaName = "seoul";
    DisplayDataType givenDataType = DisplayDataType.LIST;

    given(mapZoneRepository.existsByLanguageAndParentArea(givenLanguage, givenParentAreaName))
        .willReturn(true);

    given(mapZoneRepository.findAllByParentAreaAndLanguageOrderByZoneKeyAsc(givenParentAreaName, givenLanguage))
        .willReturn(List.of(
            MapZone.builder()
                .zoneKey(1)
                .build(),
            MapZone.builder()
                .zoneKey(2)
                .build()));

    //when
    ResMapBannerZonesListDto resMapBannerZonesListDto =
        mapZoneService.getMapBannerZonesList(givenLanguage, givenParentAreaName, givenDataType);

    //then
    then(mapZoneRepository)
        .should()
        .existsByLanguageAndParentArea(givenLanguage, givenParentAreaName);

    then(mapZoneRepository)
        .should()
        .findAllByParentAreaAndLanguageOrderByZoneKeyAsc(givenParentAreaName, givenLanguage);

    assertEquals(resMapBannerZonesListDto.getZoneData().getClass(), ArrayList.class);
    assertEquals(((List)resMapBannerZonesListDto.getZoneData()).size(), 2);

    assertEquals(resMapBannerZonesListDto.getAreaName(), givenParentAreaName);
    assertEquals(resMapBannerZonesListDto.getZoneDataType(), givenDataType.getType());
    assertEquals(resMapBannerZonesListDto.getLanguage(), givenLanguage.getVersion());
    assertEquals(resMapBannerZonesListDto.getGuideMessage(), "다양한 오디오 가이드를 지도에서 확인해보세요!");
  }


  @Test
  public void getMapBannerZonesList_MAP_타입_테스트() throws NoSuchMethodException {
    //given
    Language givenLanguage = Language.ENGLISH;
    String givenParentAreaName = "seoul";
    DisplayDataType givenDataType = DisplayDataType.MAP;

    given(mapZoneRepository.existsByLanguageAndParentArea(givenLanguage, givenParentAreaName))
        .willReturn(true);

    given(mapZoneRepository.findAllByParentAreaAndLanguageOrderByZoneKeyAsc(givenParentAreaName, givenLanguage))
        .willReturn(List.of(
            MapZone.builder()
                .zoneKey(1)
                .build()));

    //when
    ResMapBannerZonesListDto resMapBannerZonesListDto =
        mapZoneService.getMapBannerZonesList(givenLanguage, givenParentAreaName, givenDataType);

    //then
    then(mapZoneRepository)
        .should()
        .existsByLanguageAndParentArea(givenLanguage, givenParentAreaName);

    then(mapZoneRepository)
        .should()
        .findAllByParentAreaAndLanguageOrderByZoneKeyAsc(givenParentAreaName, givenLanguage);

    assertEquals(resMapBannerZonesListDto.getZoneData().getClass(), LinkedHashMap.class);
    assertEquals(((Map)resMapBannerZonesListDto.getZoneData()).size(), 1);

    assertEquals(resMapBannerZonesListDto.getAreaName(), givenParentAreaName);
    assertEquals(resMapBannerZonesListDto.getZoneDataType(), givenDataType.getType());
    assertEquals(resMapBannerZonesListDto.getLanguage(), givenLanguage.getVersion());
    assertEquals(resMapBannerZonesListDto.getGuideMessage(), "Check Out the Audio Guides on the Map!");
  }

  @Test
  public void getMapBannerZonesList_should_throw_InvalidParentAreaNameException_테스트() throws NoSuchMethodException {
    //given
    String givenAreaName = "seoul";
    Language givenLanguage = Language.KOREAN;
    DisplayDataType givenDataType = DisplayDataType.LIST;

    given(mapZoneRepository.existsByLanguageAndParentArea(givenLanguage, givenAreaName))
        .willReturn(false);

    //when
    try{
      mapZoneService.getMapBannerZonesList(givenLanguage, givenAreaName, givenDataType);
      fail("Should throw exception");
    }catch (InvalidParentAreaNameException e){
      System.out.println("InvalidParentAreaNameException occurs..");
    }

    //then
    then(mapZoneRepository)
        .should(never())
        .findAllByParentAreaAndLanguageOrderByZoneKeyAsc(givenAreaName, givenLanguage);
  }
}
