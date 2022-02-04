package team_mic.here_and_there.backend.map_banner.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.map_banner.domain.entity.MapZone;
import team_mic.here_and_there.backend.map_banner.domain.repository.MapZoneRepository;
import team_mic.here_and_there.backend.map_banner.dto.response.ResMapBannerZonesListDto;
import team_mic.here_and_there.backend.map_banner.dto.response.ResMapZoneItemDto;
import team_mic.here_and_there.backend.map_banner.exception.InvalidParentAreaNameException;

@RequiredArgsConstructor
@Service
public class MapZoneService {

  private final MapZoneRepository mapZoneRepository;

  private final static String KOR_GUIDE_MESSAGE = "다양한 오디오 가이드를 지도에서 확인해보세요!";
  private final static String ENG_GUIDE_MESSAGE = "Check Out the Audio Guides on the Map!";

  public ResMapBannerZonesListDto getMapBannerZonesList(Language language, String areaName, DisplayDataType zoneDataType) {
    if(!isValidAreaName(language, areaName)){
      throw new InvalidParentAreaNameException();
    }
    Object zoneData = new Object();
    String guideMessage = "";

    if(language.equals(Language.KOREAN)){
      guideMessage = KOR_GUIDE_MESSAGE;
    }else if(language.equals(Language.ENGLISH)){
      guideMessage = ENG_GUIDE_MESSAGE;
    }

    List<MapZone> mapZoneList = mapZoneRepository.findAllByParentAreaAndLanguageOrderByZoneKeyAsc(areaName, language);

    if(zoneDataType.equals(DisplayDataType.LIST)){
      List<ResMapZoneItemDto> zoneItemDtoList = mapZoneList.stream().map(mapZone -> toMapZoneItemDto(mapZone))
          .collect(Collectors.toList());

      zoneData = zoneItemDtoList;
    }else if(zoneDataType.equals(DisplayDataType.MAP)){
      Map<Integer, ResMapZoneItemDto> zoneItemDtoMap = new LinkedHashMap<>();

      mapZoneList.stream().forEach(mapZone ->
        zoneItemDtoMap.put(mapZone.getZoneKey(), toMapZoneItemDto(mapZone)));

      zoneData = zoneItemDtoMap;
    }

    return ResMapBannerZonesListDto.builder()
        .guideMessage(guideMessage)
        .areaName(areaName)
        .language(language.getVersion())
        .zoneDataType(zoneDataType.getType())
        .zoneData(zoneData)
        .build();
  }

  private ResMapZoneItemDto toMapZoneItemDto(MapZone mapZone) {
    return ResMapZoneItemDto.builder()
        .zoneKey(mapZone.getZoneKey())
        .entryPointLatitude(mapZone.getEntryPointLatitude())
        .entryPointLongitude(mapZone.getEntryPointLongitude())
        .zoneImageUrl(mapZone.getImageUrl())
        .zoneName(mapZone.getZoneName())
        .hashEntryPoint(mapZone.getEntryPointLatitude() == null ? false : true)
        .build();
  }

  private boolean isValidAreaName(Language language, String areaName) {
    return mapZoneRepository.existsByLanguageAndParentArea(language, areaName);
  }
}
