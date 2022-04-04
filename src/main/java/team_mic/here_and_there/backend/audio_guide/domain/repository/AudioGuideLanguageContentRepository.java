package team_mic.here_and_there.backend.audio_guide.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
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

  @EntityGraph(attributePaths = {"audioGuide.images"}, type = EntityGraph.EntityGraphType.LOAD)
  @Query("select distinct glc "
      +  "from AudioGuideLanguageContent glc "
      +  "inner join glc.audioGuide g "
      +  "left join g.course c "
      +  "left join c.audioCourseElement.languageContents clc "
      +  "left join g.tags t "
      +  "left join t.tag tt "
      +  "where (glc.language=:language and lower(glc.title) like concat('%',:keyword,'%')) "
      +  "or (glc.language=:language and tt.language=:language and lower(tt.name) like concat('%',:keyword,'%')) "
      +  "or (glc.language=:language and clc.language=:language and lower(clc.title) like concat('%',:keyword,'%'))"
  )
  Page<AudioGuideLanguageContent> findDistinctTitleAndTagsAndCourseByContaining(
      @Param("language") Language language,
      @Param("keyword") String keyword,
      Pageable pageable);
}
