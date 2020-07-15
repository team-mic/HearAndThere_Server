package team_mic.here_and_there.backend.trips_tip.domain.entity;

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
@Table(name = "trip_tips")
@Entity
public class TripTip extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "trip_tips_images", joinColumns = {@JoinColumn(name = "trip_tip_id")})
    @Column(name = "image_url")
    private Set<String> images = new HashSet<>();

    @Builder
    private TripTip(String title, String description, Set<String> images){
        this.title=title;
        this.description=description;
        this.images=images;
    }
}
