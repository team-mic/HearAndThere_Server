package team_mic.here_and_there.backend.map_banner.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.map_banner.domain.entity.MapZone;

@Repository
public interface MapZoneRepository extends JpaRepository<MapZone, Integer> {
  Boolean existsByLanguageAndParentArea(Language language, String parentName);
  List<MapZone> findAllByLanguageOrderByZoneKeyAsc(Language language);
}
