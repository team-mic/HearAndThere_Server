package team_mic.here_and_there.backend.map_banner.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        .entryPointLatitude("37.55644856502666")
        .entryPointLongitude("126.91757322671965")
        .build();

    //when
    MapZone savedMapZone = mapZoneRepository.save(mapZone);

    //then
    assertEquals(savedMapZone.getId(), 1);
    assertEquals(savedMapZone.getZoneName(), "마포-은평");
  }
}
