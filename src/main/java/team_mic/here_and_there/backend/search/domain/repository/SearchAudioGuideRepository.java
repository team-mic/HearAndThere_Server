package team_mic.here_and_there.backend.search.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchAudioGuide;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;

@Repository
public interface SearchAudioGuideRepository extends SearchKeywordRepository<SearchAudioGuide> {
  Optional<SearchKeyword> findByAudioGuideAndLanguage(AudioGuide audioGuide, Language language);
}
