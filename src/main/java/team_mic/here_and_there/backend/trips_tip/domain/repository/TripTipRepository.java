package team_mic.here_and_there.backend.trips_tip.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;

import java.util.List;

@Repository
public interface TripTipRepository extends JpaRepository<TripTip, Long> {
  Optional<TripTip> findByIdAndLanguage(Long id, Language language);
  List<TripTip> findAllByLanguageOrderByViewCountDesc(Language language);
  List<TripTip> findAllByLanguage(Language language);
}
