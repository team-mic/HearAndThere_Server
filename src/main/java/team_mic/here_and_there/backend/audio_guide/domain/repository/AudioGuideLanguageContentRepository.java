package team_mic.here_and_there.backend.audio_guide.domain.repository;

import java.util.List;
import java.util.Optional;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.common.domain.Language;

@Repository
public interface AudioGuideLanguageContentRepository extends JpaRepository<AudioGuideLanguageContent, Long> {
  List<AudioGuideLanguageContent> findAllByLanguageOrderByViewCountDesc(Language language);
  List<AudioGuideLanguageContent> findAllByLanguageOrderByPlayingCountDesc(Language language);
  Optional<AudioGuideLanguageContent> findByAudioGuide_IdAndLanguage(Long audioGuideId, Language language);
  List<AudioGuideLanguageContent> findAllByLanguage(Language language);
}
