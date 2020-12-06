package team_mic.here_and_there.backend.attraction.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.attraction.domain.entity.TouristArea;
import team_mic.here_and_there.backend.common.domain.Language;

@Repository
public interface TouristAreaRepository extends JpaRepository<TouristArea, Long> {
  List<TouristArea> findAllByLanguageOrderByIdAsc(Language language);
  Optional<TouristArea> findByAreaCodeAndSigunguCodeAndLanguage(Integer areaCode, Integer sigunguCode, Language language);
  Optional<TouristArea> findByAreaCodeAndLanguage(Integer areaCode, Language language);
  Boolean existsByAreaCodeAndSigunguCode(Integer areaCode, Integer sigunguCode);
  Boolean existsByAreaCode(Integer areaCode);

}
