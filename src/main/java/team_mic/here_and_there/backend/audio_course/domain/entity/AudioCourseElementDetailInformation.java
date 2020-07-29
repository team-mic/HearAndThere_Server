package team_mic.here_and_there.backend.audio_course.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_course_element_detail_informations")
@Entity
public class AudioCourseElementDetailInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String phoneNumber;

  private String travelHotLinePhoneNumber;

  private String dayOffDays;

  private String parkingInfo;

  private String availableTime;

  private String salesItems;

  private String strollerRentalAvailable;

  private String petsAllowedAvailable;

  private String creditCardAvailable;

  private String admissionFees;

  @OneToOne(mappedBy = "detailInformation", fetch = FetchType.EAGER)
  private AudioCourseElement audioCourseElement;

  @Builder
  private AudioCourseElementDetailInformation(String phoneNumber, String travelHotLinePhoneNumber,
      String dayOffDays, String parkingInfo, String availableTime, String salesItems,
      String strollerRentalAvailable, String petsAllowedAvailable, String creditCardAvailable,
      String admissionFees) {
    this.phoneNumber = phoneNumber;
    this.travelHotLinePhoneNumber = travelHotLinePhoneNumber;
    this.dayOffDays = dayOffDays;
    this.parkingInfo = parkingInfo;
    this.availableTime = availableTime;
    this.salesItems = salesItems;
    this.strollerRentalAvailable = strollerRentalAvailable;
    this.petsAllowedAvailable = petsAllowedAvailable;
    this.creditCardAvailable = creditCardAvailable;
    this.admissionFees = admissionFees;
  }
}
