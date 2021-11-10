package team_mic.here_and_there.backend.audio_guide.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.common.domain.Language;

@Repository
public interface AudioGuideLanguageContentRepository extends JpaRepository<AudioGuideLanguageContent, Long> {
  List<AudioGuideLanguageContent> findAllByLanguageOrderByViewCountDesc(Language language);
  List<AudioGuideLanguageContent> findAllByLanguageOrderByPlayingCountDesc(Language language);
  Optional<AudioGuideLanguageContent> findByAudioGuide_IdAndLanguage(Long audioGuideId, Language language);
  List<AudioGuideLanguageContent> findAllByLanguage(Language language);

  @Query("select distinct lc "
      +  "from AudioGuideLanguageContent lc "
      +  "join lc.audioGuide.tags t "
      +  "join t.tag tt "
      +  "where (lc.language=:language and lower(lc.title) like concat('%',:keyword,'%')) "
      +  "or (lc.language=:language and tt.language=:language and lower(tt.name) like concat('%',:keyword,'%'))")
  Page<AudioGuideLanguageContent> findDistinctTitleAndTagsByContaining(
      @Param("language") Language language,
      @Param("keyword") String keyword,
      Pageable pageable);
}
