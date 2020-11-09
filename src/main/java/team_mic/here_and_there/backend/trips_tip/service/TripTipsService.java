package team_mic.here_and_there.backend.trips_tip.service;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipItemDto;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;

import java.util.List;
import java.util.stream.Collectors;
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

  private ResTripTipItemDto toTripTipItemDto(TripTip tip) {
    return ResTripTipItemDto.builder()
        .tripTipId(tip.getId())
        .title(tip.getTitle())
        .thumbnailImageUrl(tip.getThumbnailImage())
        .contentsUrl(tip.getContentsUrl())
        .thumbnailDescription(tip.getThumbnailDescription())
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
