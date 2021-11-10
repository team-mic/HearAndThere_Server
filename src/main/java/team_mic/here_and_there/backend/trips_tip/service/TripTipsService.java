package team_mic.here_and_there.backend.trips_tip.service;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_mic.here_and_there.backend.common.domain.ImageSizeType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.repository.SearchTripTipRepository;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResPatchedSingleTripTipDto;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipItemDto;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;

import java.util.List;
import team_mic.here_and_there.backend.trips_tip.exception.NoTripTipsException;

@RequiredArgsConstructor
@Service
public class TripTipsService {

  private final TripTipRepository tripTipRepository;

  public ResTripTipsListDto getMainFixedTripTipsList(String language) {
    List<ResTripTipItemDto> itemList = new ArrayList<>();
    Long[] fixedTipIds = new Long[2];

    if(language.equals(Language.KOREAN.getVersion())){
      fixedTipIds[0] = 9L;
      fixedTipIds[1] = 11L;
    }

    if(language.equals(Language.ENGLISH.getVersion())){
      fixedTipIds[0] = 2L;
      fixedTipIds[1] = 5L;
    }

    for(Long tipId : fixedTipIds){
      TripTip tip = tripTipRepository.findById(tipId).orElseThrow(NoTripTipsException::new);
      itemList.add(toTripTipItemDto(tip));
    }

    return ResTripTipsListDto.builder()
        .language(language)
        .tripTipsList(itemList)
        .build();
  }

  public ResTripTipItemDto toTripTipItemDto(TripTip tip) {
    return ResTripTipItemDto.builder()
        .tripTipId(tip.getId())
        .title(tip.getTitle())
        .thumbnailImageUrl(tip.getThumbnailImage() + ImageSizeType.MIDDLE.getSuffix())
        .contentsUrl(tip.getContentsUrl())
        .thumbnailDescription(tip.getThumbnailDescription())
        .viewCount(Long.valueOf(tip.getViewCount()))
        .build();
  }

  public ResTripTipsListDto getAllTripTipsListOrderByViewCount(String language) {
    List<ResTripTipItemDto> itemList = new ArrayList<>();
    List<TripTip> tips = new ArrayList<>();

    if(language.equals(Language.KOREAN.getVersion())){
      tips = tripTipRepository.findAllByLanguageOrderByViewCountDesc(Language.KOREAN);
    }

    if(language.equals(Language.ENGLISH.getVersion())){
      tips = tripTipRepository.findAllByLanguageOrderByViewCountDesc(Language.ENGLISH);
    }

    if(tips.isEmpty()){
      throw new NoTripTipsException();
    }
    tips.forEach(tip -> itemList.add(toTripTipItemDto(tip)));

    return ResTripTipsListDto.builder()
        .language(language)
        .tripTipsList(itemList)
        .build();
  }

  @Transactional
  public ResPatchedSingleTripTipDto updateTripTipViewCount(Long tripTipsId) {
    TripTip tip = tripTipRepository.findById(tripTipsId).orElseThrow(NoTripTipsException::new);
    tip.updateViewCount();
    return ResPatchedSingleTripTipDto.builder()
        .tripTipsId(tip.getId())
        .updatedViewCount(String.valueOf(tip.getViewCount()))
        .build();
  }
/*
  public ResTripTipsListDto getTripTipsList() {

    List<TripTip> tipsList = tripTipRepository.findTop4ByOrderByCreatedTimeDesc();
    if (tipsList.isEmpty()) {
      throw new NoTripTipsException();
    }
    List<ResTripTipItemDto> itemList = tipsList.parallelStream()
        .map(tripTip -> ResTripTipItemDto.builder()
            .tripTipId(tripTip.getId())
            .imageUrl(tripTip.getThumbnailImage().get(0))
            .title(tripTip.getTitle())
            .description(tripTip.getDescription())
            .build())
        .collect(Collectors.toList());

    return ResTripTipsListDto.builder().tripTipsList(itemList).build();
  }
  */
}
