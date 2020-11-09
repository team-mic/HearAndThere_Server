package team_mic.here_and_there.backend.trips_tip.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;

import javax.persistence.*;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_guide_trips_tip_containers")
@Entity
public class AudioGuideTripsTipContainer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "audio_guide_id")
    private AudioGuide audioGuide;

    @ManyToOne
    @JoinColumn(name = "trip_tip_id")
    private TripTip tripTip;

    @Builder
    private AudioGuideTripsTipContainer(AudioGuide audioGuide, TripTip tripTip){
        this.audioGuide = audioGuide;
        this.tripTip = tripTip;
    }
}
