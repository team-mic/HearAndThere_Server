package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_tracks")
@Entity
public class AudioTrack extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String audioFileUrl;

    private String runningTime;

    private String title;

    private String location;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "audio_tracks_images", joinColumns = @JoinColumn(name = "audio_track_id"))
    @Column(name = "image_url")
    private Set<String> images = new HashSet<>();

    @OneToMany(mappedBy = "audioTrack", fetch = FetchType.EAGER)
    private Set<AudioGuideTrackContainer> guides = new HashSet<>();

    private Long attractionId; //tour api의 content_id

    @Builder
    private AudioTrack(String audioFileUrl, String runningTime, String title, String location, Set<String> images, Long attractionId){
        this.audioFileUrl=audioFileUrl;
        this.runningTime=runningTime;
        this.title=title;
        this.location=location;
        this.images=images;
        this.attractionId=attractionId;
    }

}
