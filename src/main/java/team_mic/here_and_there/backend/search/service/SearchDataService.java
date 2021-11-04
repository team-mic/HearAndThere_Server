package team_mic.here_and_there.backend.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.dto.response.ResSearchKeywordItemDto;
import team_mic.here_and_there.backend.search.dto.response.ResSearchKeywordRankListDto;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;

@RequiredArgsConstructor
@Service
public class SearchDataService {

  private final AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;
  private final TripTipRepository tripTipRepository;

  public ResSearchKeywordRankListDto getPopularSearchKeywordsRankingsWithDummyData(String language, Integer count) {
    Language lan = null;
    List<List<Object>> dummyAttractions = new ArrayList<>();

    if(language.equals(Language.ENGLISH.getVersion())){
      lan = Language.ENGLISH;
      dummyAttractions.add(new ArrayList<Object>(){{add("Gyeongbokgung Palace (경복궁)"); add(76); add(264337L);}});
      dummyAttractions.add(new ArrayList<Object>(){{add("Lotte World (롯데월드)"); add(76); add(264152L);}});
    }
    if(language.equals(Language.KOREAN.getVersion())){
      lan = Language.KOREAN;
      dummyAttractions.add(new ArrayList<Object>(){{add("홍릉수목원"); add(12); add(126500L);}});
      dummyAttractions.add(new ArrayList<Object>(){{add("청계산"); add(12); add(125452L);}});
    }

    List<AudioGuideLanguageContent> dummyGuideContents = audioGuideLanguageContentRepository.findAllByLanguage(lan).subList(0,4);
    List<TripTip> dummyTripTips = tripTipRepository.findAllByLanguage(lan).subList(0,4);

    Integer maxDummySearchKeywordsCount = 10;

    if(count > maxDummySearchKeywordsCount){
      count = maxDummySearchKeywordsCount;
    }

    List<ResSearchKeywordItemDto> searchKeywordItemList= new ArrayList<>();
    //dummy guide insert
    searchKeywordItemList.addAll(dummyGuideContents.stream()
        .map(guideContent -> ResSearchKeywordItemDto.builder()
            .keywordType("audio-guide")
            .keywordTargetIds(new ArrayList<Long>(){{add(guideContent.getAudioGuide().getId());}})
            .searchHitCounts(30L)
            .keywordTitle(guideContent.getTitle())
        .build())
        .collect(Collectors.toList()));

    //dummy trip tip insert
    searchKeywordItemList.addAll(dummyTripTips.stream()
      .map(tripTip -> ResSearchKeywordItemDto.builder()
          .keywordType("trip-tip")
          .keywordTitle(tripTip.getTitle())
          .keywordTargetIds(new ArrayList<Long>(){{add(tripTip.getId());}})
          .tripTipContentsUrl(tripTip.getContentsUrl())
          .searchHitCounts(20L)
        .build())
        .collect(Collectors.toList()));

    //dummy attraction insert
    searchKeywordItemList.addAll(dummyAttractions.stream()
        .map(attraction -> ResSearchKeywordItemDto.builder()
            .keywordType("attraction")
            .keywordTitle((String) attraction.get(0))
            .keywordTargetIds(new ArrayList<Long>(){{add(Long.valueOf((Integer)attraction.get(1))); add((Long) attraction.get(2));}})
            .searchHitCounts(10L)
            .build())
        .collect(Collectors.toList()));

    return ResSearchKeywordRankListDto.builder()
        .count(count)
        .language(lan.getVersion())
        .keywordRankList(searchKeywordItemList.subList(0, count))
        .build();
  }
}
