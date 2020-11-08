package team_mic.here_and_there.backend.attraction.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.Language;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tourist_areas")
@Entity
public class TouristArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thumbnailImage;

    private String areaName;

    @Enumerated(EnumType.STRING)
    private Language language;

    private Integer areaCode;

    private Integer sigunguCode;

    @Builder
    private TouristArea(String thumbnailImage, String areaName, Language language, Integer areaCode, Integer sigunguCode){
        this.thumbnailImage = thumbnailImage;
        this.areaName = areaName;
        this.language = language;
        this.areaCode = areaCode;
        this.sigunguCode = sigunguCode;
    }
}
