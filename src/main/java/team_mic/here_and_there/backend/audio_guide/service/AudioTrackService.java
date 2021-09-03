package team_mic.here_and_there.backend.audio_guide.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrackLanguageContent;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoItemDto;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.common.util.DistanceCalculate;
import team_mic.here_and_there.backend.common.domain.ImageSizeType;

@RequiredArgsConstructor
@Service
public class AudioTrackService {

  private static final Long RADIUS_METER_BOUNDARY = 50L;
  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

  public List<ResAudioTrackInfoItemDto> getAudioGuidesTrackList(AudioGuide guide, String language,
      Boolean isHlsSupport) {

    if(isHlsSupport!=null && isHlsSupport && guide.getId() == 6L){
      return guide.getTracks().stream()
          .map(audioGuideTrackContainer -> toHlsSupportAudioTrackInfoItem(audioGuideTrackContainer, language))
          .collect(Collectors.toList());
    }

    List<ResAudioTrackInfoItemDto> trackList = guide.getTracks().stream()
        .map(audioGuideTrackContainer -> toAudioTrackInfoItem(audioGuideTrackContainer, language))
        .collect(Collectors.toList());

    return trackList;
  }

  //for hls support test
  private ResAudioTrackInfoItemDto toHlsSupportAudioTrackInfoItem(AudioGuideTrackContainer audioGuideTrackContainer,
      String language) {

    String[] engHlsFileUrl = {
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/hls/01+Mangwon.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/hls/02+Mangwonjeong+Pavilion+Site.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/hls/03+Mangnidan-gil.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/hls/04+Mangwon+Market.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/hls/05+Mangwon+Hangang+Park.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/hls/06+Seoul+Battleship+park.m3u8",
    };

    String[] korHlsFileUrl = {
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/hls/1+%EB%A7%9D%EC%9B%90%EB%8F%99+%EC%86%8C%EA%B0%9C.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/hls/2+%EB%A7%9D%EC%9B%90%EC%A0%95.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/hls/3+%EB%A7%9D%EB%A6%AC%EB%8B%A8%EA%B8%B8%EC%97%90+%EB%8C%80%ED%95%98%EC%97%AC.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/hls/4+%EB%A7%9D%EC%9B%90%EC%8B%9C%EC%9E%A5%EC%97%90+%EB%8C%80%ED%95%9C+%EC%86%8C%EA%B0%9C.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/hls/5+%EB%A7%9D%EC%9B%90%ED%95%9C%EA%B0%95%EA%B3%B5%EC%9B%90.m3u8",
        AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/hls/6+%EC%84%9C%EC%9A%B8%ED%95%A8+%EA%B3%B5%EC%9B%90.m3u8"
    };

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
        .audioFileUrl(language.equals(Language.KOREAN.getVersion()) ?
            korHlsFileUrl[audioGuideTrackContainer.getOrderNumber()-1] : engHlsFileUrl[audioGuideTrackContainer.getOrderNumber()-1])
        .runningTime(correspondingContent.getRunningTime())
        .build();
  }

  private ResAudioTrackInfoItemDto toAudioTrackInfoItem(
      AudioGuideTrackContainer audioGuideTrackContainer,
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
