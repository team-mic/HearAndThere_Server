package team_mic.here_and_there.backend.search.domain.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.search.domain.entity.SearchAudioGuide;

@Repository
public interface SearchAudioGuideRepository extends SearchKeywordRepository<SearchAudioGuide> {
}
