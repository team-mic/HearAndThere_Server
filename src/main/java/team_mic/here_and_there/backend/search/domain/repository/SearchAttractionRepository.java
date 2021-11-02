package team_mic.here_and_there.backend.search.domain.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchAttraction;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;

@Repository
public interface SearchAttractionRepository extends SearchKeywordRepository<SearchAttraction> {
  Optional<SearchKeyword> findByContentTypeIdAndContentIdAndLanguage(Integer contentTypeId, Long contentId, Language language);
}
