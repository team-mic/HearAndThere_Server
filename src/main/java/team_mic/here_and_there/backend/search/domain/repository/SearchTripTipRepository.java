package team_mic.here_and_there.backend.search.domain.repository;

import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.search.domain.entity.SearchTripTip;

@Repository
public interface SearchTripTipRepository extends SearchKeywordRepository<SearchTripTip> {
}
