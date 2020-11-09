package team_mic.here_and_there.backend.attraction.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.attraction.domain.entity.TouristArea;
import team_mic.here_and_there.backend.common.domain.Language;

@Repository
public interface TouristAreaRepository extends JpaRepository<TouristArea, Long> {
  List<TouristArea> findAllByLanguageOrderByIdAsc(Language language);
}
