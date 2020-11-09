package team_mic.here_and_there.backend.trips_tip.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;

import java.util.List;

@Repository
public interface TripTipRepository extends JpaRepository<TripTip, Long> {

  List<TripTip> findAllByLanguageOrderByViewCountDesc(Language language);
}
