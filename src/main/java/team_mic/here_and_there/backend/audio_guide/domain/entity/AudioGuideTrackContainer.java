package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class AudioGuideTrackContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "audio_guides_id")
    private AudioGuide audioGuide;

    @ManyToOne
    @JoinColumn(name = "audio_tracks_id")
    private AudioTrack audioTrack;

    private Integer orderNumber;

    @Builder
    private AudioGuideTrackContainer(AudioGuide audioGuide, AudioTrack audioTrack, Integer orderNumber){
        this.audioGuide=audioGuide;
        this.audioTrack=audioTrack;
        this.orderNumber=orderNumber;
    }
}
