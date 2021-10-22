package team_mic.here_and_there.backend.audio_guide.domain.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "main_categories")
@Entity
public class MainCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String engName;

  private String korName;

  @OneToMany(mappedBy = "parentMainCategory")
  private Set<SubCategory> subCategories = new HashSet<>();

  @Builder
  private MainCategory(String engName, String korName){
    this.engName = engName;
    this.korName = korName;
  }
}
