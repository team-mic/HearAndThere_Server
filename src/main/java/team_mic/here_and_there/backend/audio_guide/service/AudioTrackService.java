package team_mic.here_and_there.backend.audio_guide.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoListDto;

@RequiredArgsConstructor
@Service
public class AudioTrackService {

  private final AudioGuideService audioGuideService;

  public ResAudioTrackInfoListDto getAudioGuidesTrackList(Long audioGuideId) {

    AudioGuide audioGuide = audioGuideService.findAudioGuideById(audioGuideId);

    List<ResAudioTrackInfoItemDto> trackList = audioGuide.getTracks().parallelStream()
        .map(audioGuideTrackContainer -> toAudioTrackInfoItem(audioGuideTrackContainer))
        .collect(Collectors.toList());

    return ResAudioTrackInfoListDto.builder()
        .audioGuideId(audioGuide.getId())
        .audioGuideTitle(audioGuide.getTitle())
        .audioTrackInfoList(trackList)
        .build();
  }

  private ResAudioTrackInfoItemDto toAudioTrackInfoItem(
      AudioGuideTrackContainer audioGuideTrackContainer) {

    AudioTrack track = audioGuideTrackContainer.getAudioTrack();

    return ResAudioTrackInfoItemDto.builder()
        .audioTrackId(track.getId())
        .orderNumber(audioGuideTrackContainer.getOrderNumber())
        .audioFileUrl(track.getAudioFileUrl())
        .images(track.getImages())
        .title(track.getTitle())
        .runningTime(track.getRunningTime())
        .placeName(track.getPlaceName())
        .placeAddress(track.getPlaceAddress())
        .trackLatitude(track.getLocationLatitude())
        .trackLongitude(track.getLocationLongitude())
        .build();
  }
}
