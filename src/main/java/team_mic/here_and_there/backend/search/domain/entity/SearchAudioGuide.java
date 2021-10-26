package team_mic.here_and_there.backend.search.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.common.domain.Language;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DiscriminatorValue("audio_guide")
public class SearchAudioGuide extends SearchKeyword{
  @ManyToOne
  @JoinColumn(name = "audio_guide_language_content_id")
  private AudioGuideLanguageContent audioGuideLanguageContent;

  @Builder
  private SearchAudioGuide(Language language, AudioGuideLanguageContent guideLanguageContent){
    super(language);
    this.audioGuideLanguageContent = guideLanguageContent;
  }
}
