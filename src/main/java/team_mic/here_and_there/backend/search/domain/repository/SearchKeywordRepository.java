package team_mic.here_and_there.backend.search.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;

@Repository
public interface SearchKeywordRepository<T extends SearchKeyword> extends JpaRepository<T, Long> {
  List<SearchKeyword> findAllByLanguageOrderBySearchHitCountsDesc(Language language);

  @Query("select sk from SearchKeyword sk where type = :type")
  List<SearchKeyword> findAllByType(@Param("type") String type);
}
