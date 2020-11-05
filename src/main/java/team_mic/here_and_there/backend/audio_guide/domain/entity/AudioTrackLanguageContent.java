package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.Language;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_track_language_contents")
@Entity
public class AudioTrackLanguageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String title;

    private String audioFileUrl;

    private String runningTime;

    @ManyToOne
    @JoinColumn(name = "audio_track_id")
    private AudioTrack audioTrack;

    @Builder
    private AudioTrackLanguageContent(Language language, String title, String audioFileUrl,
                                      AudioTrack audioTrack, String runningTime){
        this.language = language;
        this.title = title;
        this.runningTime=runningTime;
        this.audioFileUrl = audioFileUrl;
        this.audioTrack = audioTrack;
    }
}
