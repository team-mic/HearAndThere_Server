package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.Language;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_guide_language_contents")
@Entity
public class AudioGuideLanguageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String title;

    private String category;

    @Lob
    private String overviewDescription;

    @ManyToOne
    @JoinColumn(name = "audio_guide_id")
    private AudioGuide audioGuide;

    private String viewCount;

    private String playingCount;

    @Builder
    private AudioGuideLanguageContent(Language language, String title, String category,
                                      String overviewDescription, AudioGuide audioGuide,
                                      String viewCount, String playingCount){
        this.language = language;
        this.title = title;
        this.category = category;
        this.overviewDescription = overviewDescription;
        this.audioGuide = audioGuide;
        this.viewCount = viewCount;
        this.playingCount = playingCount;
    }
}
