package team_mic.here_and_there.backend.location_tag.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.common.domain.Language;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tags")
@Entity
public class Tag extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "tag")
  private Set<AudioGuideTag> guides = new HashSet<>();

  @Enumerated(EnumType.STRING)
  private Language language;

  @Builder
  private Tag(String name, Language language) {
    this.name = name;
    this.language = language;
  }
}
