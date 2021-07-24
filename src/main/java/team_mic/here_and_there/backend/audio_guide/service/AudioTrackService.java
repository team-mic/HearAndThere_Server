package team_mic.here_and_there.backend.audio_guide.service;

import autovalue.shaded.com.google$.common.primitives.$Primitives;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrackLanguageContent;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResNearestAudioTrackDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResNearestAudioTrackInfoDto;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.common.util.DistanceCalculate;
import team_mic.here_and_there.backend.util.ImageSizeType;

@RequiredArgsConstructor
@Service
public class AudioTrackService {

  private static final Long RADIUS_METER_BOUNDARY = 50L;


  public List<ResAudioTrackInfoItemDto> getAudioGuidesTrackList(AudioGuide guide, String language) {

    List<ResAudioTrackInfoItemDto> trackList = guide.getTracks().stream()
        .map(audioGuideTrackContainer -> toAudioTrackInfoItem(audioGuideTrackContainer, language))
        .collect(Collectors.toList());

    return trackList;
  }

  private ResAudioTrackInfoItemDto toAudioTrackInfoItem(AudioGuideTrackContainer audioGuideTrackContainer,
      String language) {

    AudioTrack track = audioGuideTrackContainer.getAudioTrack();
    Optional<AudioTrackLanguageContent> languageContent = Optional.empty();
    for(AudioTrackLanguageContent content : track.getLanguageContents()){
      if(content.getLanguage().getVersion().equals(language)){
        languageContent = Optional.of(content);
      }
    }

    AudioTrackLanguageContent correspondingContent = languageContent.orElseThrow(
        NoSuchElementException::new);

    return ResAudioTrackInfoItemDto.builder()
        .audioTrackId(track.getId())
        .orderNumber(audioGuideTrackContainer.getOrderNumber())
        .thumbnailImage(track.getImages().get(0) + ImageSizeType.THUMBNAIL.getSuffix())
        .images(track.getImages().stream()
            .map(image-> image + ImageSizeType.LARGE.getSuffix())
            .collect(Collectors.toList())
        )
        .title(correspondingContent.getTitle())
        .audioFileUrl(correspondingContent.getAudioFileUrl())
        .runningTime(correspondingContent.getRunningTime())
        .build();
  }
/*
  public ResNearestAudioTrackDto getNearestAudioTracks(Long audioGuideId, Double userLatitude,
      Double userLongitude) {

    Set<AudioGuideTrackContainer> trackContainers = audioGuideService
        .findAudioGuideById(audioGuideId).getTracks();

    AudioTrack nearestTrack = findNearestTrackBelongingToRadius(trackContainers, userLatitude,
        userLongitude);

    Boolean isTrackNearBy = isAudioTrackNearBy(nearestTrack);

    ResNearestAudioTrackInfoDto trackInfo = toNearestAudioTrackInfo(isTrackNearBy, nearestTrack);

    return ResNearestAudioTrackDto.builder()
        .audioGuideId(audioGuideId)
        .userPresentLatitude(userLatitude)
        .userPresentLongitude(userLongitude)
        .isAudioTrackNearBy(isTrackNearBy)
        .nearestTrackInfo(trackInfo)
        .build();
  }

  private ResNearestAudioTrackInfoDto toNearestAudioTrackInfo(Boolean isTrackNearBy,
      AudioTrack nearestTrack) {
    ResNearestAudioTrackInfoDto trackInfo = null;

    if (isTrackNearBy) {
      trackInfo = ResNearestAudioTrackInfoDto.builder()
          .audioTrackId(nearestTrack.getId())
          .audioFileUrl(nearestTrack.getAudioFileUrl())
          .title(nearestTrack.getTitle())
          .images(nearestTrack.getImages())
          .runningTime(nearestTrack.getRunningTime())
          .placeName(nearestTrack.getPlaceName())
          .placeAddress(nearestTrack.getPlaceAddress())
          .build();
    }

    return trackInfo;
  }*/

  private AudioTrack findNearestTrackBelongingToRadius(
      Set<AudioGuideTrackContainer> trackContainers, Double userLatitude, Double userLongitude) {
    Double minDistance = Double.MAX_VALUE;
    AudioTrack nearestTrack = null;

    for (AudioGuideTrackContainer trackContainer : trackContainers) {
      AudioTrack track = trackContainer.getAudioTrack();
      Double distance = DistanceCalculate
          .calculateDistanceMeter(userLatitude, userLongitude, track.getLocationLatitude(),
              track.getLocationLongitude());

      if (distance <= RADIUS_METER_BOUNDARY) {
        if (minDistance > distance) {
          minDistance = distance;
          nearestTrack = track;
        }
      }
    }
    return nearestTrack;
  }

  private Boolean isAudioTrackNearBy(AudioTrack track) {
    return track == null ? false : true;
  }
}
