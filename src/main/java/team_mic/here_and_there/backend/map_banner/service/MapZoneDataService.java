package team_mic.here_and_there.backend.map_banner.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.map_banner.domain.entity.MapZone;
import team_mic.here_and_there.backend.map_banner.domain.repository.MapZoneRepository;

@RequiredArgsConstructor
@Service
public class MapZoneDataService {

  private final MapZoneRepository mapZoneRepository;
  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

  public void insertMapZones() {
    String parentArea = "seoul";
    String[] zoneKorNames = new String[]{
        "마포-은평",
        "종로-중구-용산",
        "노원-광진",
        "한강",
        "영등포-여의도-동작",
        "강남-서초-송파"
    };
    String[] zoneEngNames = new String[]{
        "•Mapo\n•Eunpyong",
        "•Jongno\n•Jung-gu\n•Yongsan",
        "•Nowon\n•Gwangjin",
        "Hangang River",
        "•Yeongdeungpo\n•Yeoeuido\n•Dongjak",
        "•Gangnam\n•Seocho\n•Songpa"
    };
    Pair<Double,Double>[] entryPoints = new Pair[]{ //lat, lon
        Pair.of(37.55644856502666, 126.91757322671965),
        Pair.of(37.55129072956092, 126.98821193116808),
        Pair.of(37.620615, 127.084547),
        null,
        Pair.of(37.521728,126.924099),
        Pair.of(37.514691562504964, 127.1059154267186)
    };

    String[] korImages = new String[]{
        "/map-banner-areas/mapo_kor.svg",
        "/map-banner-areas/jongro_kor.svg",
        "/map-banner-areas/nowon_kor.svg",
        "/map-banner-areas/river.svg",
        "/map-banner-areas/yeongdeungpo_kor.svg",
        "/map-banner-areas/gangnam_kor.svg"
    };
    String[] engImages = new String[]{
        "/map-banner-areas/mapo_eng.svg",
        "/map-banner-areas/jongro_eng.svg",
        "/map-banner-areas/nowon_eng.svg",
        "/map-banner-areas/river.svg",
        "/map-banner-areas/yeongdeungpo_eng.svg",
        "/map-banner-areas/gangnam_eng.svg"
    };

    for(int index=0; index < zoneKorNames.length; index++){
      mapZoneRepository.save(MapZone.builder()
              .language(Language.KOREAN)
              .parentArea(parentArea)
              .zoneKey(index+1)
              .zoneName(zoneKorNames[index])
              .entryPointLatitude(entryPoints[index]==null ? null : entryPoints[index].getLeft())
              .entryPointLongitude(entryPoints[index]==null ? null : entryPoints[index].getRight())
              .imageUrl(AWS_CLOUD_FRONT_URL_PREFIX+korImages[index])
        .build());

      mapZoneRepository.save(MapZone.builder()
          .language(Language.ENGLISH)
          .parentArea(parentArea)
          .zoneKey(index+1)
          .zoneName(zoneEngNames[index])
          .entryPointLatitude(entryPoints[index]==null ? null : entryPoints[index].getLeft())
          .entryPointLongitude(entryPoints[index]==null ? null : entryPoints[index].getRight())
          .imageUrl(AWS_CLOUD_FRONT_URL_PREFIX+engImages[index])
          .build());
    }
  }
}
