package team_mic.here_and_there.backend.search.domain.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;
import team_mic.here_and_there.backend.search.domain.entity.SearchTripTip;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;

@Repository
public interface SearchTripTipRepository extends SearchKeywordRepository<SearchTripTip> {
  Optional<SearchKeyword> findByTripTipAndLanguage(TripTip tripTip, Language language);
}
