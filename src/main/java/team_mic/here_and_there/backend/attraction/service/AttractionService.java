package team_mic.here_and_there.backend.attraction.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import team_mic.here_and_there.backend.attraction.domain.entity.TouristArea;
import team_mic.here_and_there.backend.attraction.domain.repository.TouristAreaRepository;
import team_mic.here_and_there.backend.attraction.dto.response.AreaCodeAndNameListDto;
import team_mic.here_and_there.backend.attraction.dto.response.AreaCodeAndNameListDto.AreaCodeAndNameItemDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionItemDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionDetailCommonDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionDetailImageListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionsDetailDto;
import team_mic.here_and_there.backend.attraction.dto.response.TourApiBaseResModelDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import team_mic.here_and_there.backend.attraction.exception.InvalidAttractionIdException;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.service.AudioCourseService;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
import team_mic.here_and_there.backend.common.domain.ImageSizeType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.dto.SearchResultAttractionListDto;

@Service
@Slf4j
public class AttractionService {

  private final RestTemplate restTemplate;

  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private AudioCourseService audioCourseService;
  @Autowired
  private AudioGuideService audioGuideService;
  @Autowired
  private TouristAreaRepository touristAreaRepository;

  @Value("${tour.api.url.kor}")
  private String korTourApiUrl;

  @Value("${tour.api.url.eng}")
  private String engTourApiUrl;

  @Value("${tour.api.serviceKey.kor}")
  private String korServiceKey;

  @Value("${tour.api.serviceKey.eng}")
  private String engServiceKey;

  private Map<Triple<Language, Integer, Integer>, String> areaCodeNameMap = new HashMap<>();

  public AttractionService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @PostConstruct
  public void initAreaCodeNameMap(){
    //for parent area( only areaCode )
    Arrays.stream(Language.values()).forEach(language -> {
      try {
        List<AreaCodeAndNameItemDto> areaCodeNameList = getAreaCodeNameList(language, null);

        areaCodeNameList.stream().forEach(areaCodeAndNameItemDto -> {
          Integer areaCode = areaCodeAndNameItemDto.getCode();
          Triple<Language, Integer, Integer> lanAreaCodeKey = Triple.of(language, areaCode, null);
          String areaName = isCustomAreaNameCode(language, areaCode) ? getCustomAreaName(language, areaCode) : areaCodeAndNameItemDto.getAreaName();

          areaCodeNameMap.put(lanAreaCodeKey, areaName);
        });

      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    });
    log.info("areaCodeNameMap size : " + areaCodeNameMap.size());

    Map<Triple<Language, Integer, Integer>, String> sigunguCodeNameMap = new HashMap<>();

    //for child area( with sigunguCode )
    areaCodeNameMap.keySet().stream().forEach(key->{
      try {
        Language language = key.getLeft();
        Integer parentAreaCode = key.getMiddle();

        List<AreaCodeAndNameItemDto> areaCodeNameList = getAreaCodeNameList(language, parentAreaCode);
        areaCodeNameList.stream().forEach(areaCodeAndNameItemDto -> {
          Integer sigunguCode = areaCodeAndNameItemDto.getCode();
          Triple<Language, Integer, Integer> lanAreaCodeKey = Triple.of(language, parentAreaCode, sigunguCode);
          String areaName = areaCodeAndNameItemDto.getAreaName();

          sigunguCodeNameMap.put(lanAreaCodeKey, areaName);
        });

      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    });

    areaCodeNameMap.putAll(sigunguCodeNameMap);
    log.info("areaCodeNameMap size : " + areaCodeNameMap.size());
  }

  private String getCustomAreaName(Language language, Integer areaCode) {
    Map<Pair<Language, Integer>, String> customAreaNameMap = new HashMap<>(){{
      put(Pair.of(Language.KOREAN, 8), "세종");
    }};

    return customAreaNameMap.get(Pair.of(language, areaCode));
  }

  private boolean isCustomAreaNameCode(Language language, Integer areaCode) {
    Set<Pair<Language, Integer>> customAreaCodeTargets = new HashSet<>(){{
      add(Pair.of(Language.KOREAN, 8));
    }};

    if(customAreaCodeTargets.contains(Pair.of(language, areaCode))){
      return true;
    }
    return false;
  }

  private HttpEntity<?> createHttpEntityHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return new HttpEntity<>(headers);
  }

  private UriComponentsBuilder createBaseUriBuilder(String language, String appendUrl)
      throws UnsupportedEncodingException {
    String baseUrl = null;
    String serviceKey = null;

    if (language.equals(Language.KOREAN.getVersion())) {
      baseUrl = korTourApiUrl;
      serviceKey = korServiceKey;
    }
    if (language.equals(Language.ENGLISH.getVersion())) {
      baseUrl = engTourApiUrl;
      serviceKey = engServiceKey;
    }

    return UriComponentsBuilder.fromHttpUrl(baseUrl + appendUrl)
        .queryParam("ServiceKey", URLDecoder.decode(serviceKey, "UTF-8"))
        .queryParam("MobileOS", "ETC")
        .queryParam("MobileApp", "HearAndThere")
        .queryParam("_type", "json");
  }

  public ResAreaAttractionsListDto getAreaAttractionsList(Integer areaCode, Integer sigunguAreaCode,
      Integer pageNumber, Integer pageSize, String language)
      throws IOException {

    if(!isValidAreaCode(areaCode, sigunguAreaCode)){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); // TODO : custom exception
    }

    Language lan = null;
    if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
    if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;

    UriComponentsBuilder builder = createBaseUriBuilder(language, "/areaBasedList");

    UriComponents components = builder
        .queryParam("areaCode", areaCode)
        .queryParam("sigunguCode", sigunguAreaCode)
        .queryParam("listYN", "Y")
        .queryParam("arrange", "P")
        .queryParam("numOfRows", pageSize)
        .queryParam("pageNo", pageNumber)
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();
    TourApiBaseResModelDto<?> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<?>>() {
            }).getBody();

    ResAreaAttractionsListDto listDto = new ResAreaAttractionsListDto();

    if(modelDto.getResponse().getBody().getItems().getClass() == String.class){ //for last page
      listDto.setAttractionList(new ArrayList<>());
    }else{
      listDto = mapper.convertValue(modelDto.getResponse().getBody().getItems(), ResAreaAttractionsListDto.class);

      Triple<Language, Integer, Integer> areaCodeNameKey = Triple.of(lan, areaCode, null);
      String areaName = areaCodeNameMap.get(areaCodeNameKey);

      listDto.getAttractionList().stream()
          .forEach(resAreaAttractionItemDto -> resAreaAttractionItemDto.setAreaName(areaName));
    }

    TouristArea area = getTouristArea(language, areaCode, sigunguAreaCode);
    listDto.setAreaName(area.getAreaName());
    listDto.setAreaMainImageUrl(area.getThumbnailImage() + ImageSizeType.MIDDLE.getSuffix());
    listDto.setLanguage(language);
    listDto.setAreaCode(areaCode);
    listDto.setListOrder("popular");
    listDto.setSigunguAreaCode(sigunguAreaCode);
    listDto.setPageNumber(pageNumber);

    return listDto;
  }

  public Integer getAreaAttractionsCount(Integer areaCode, Integer sigunguAreaCode, String language)
      throws UnsupportedEncodingException {

    if(!isValidAreaCode(areaCode, sigunguAreaCode)){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); // TODO : custom exception
    }

    UriComponentsBuilder builder = createBaseUriBuilder(language, "/areaBasedList");

    UriComponents components = builder
        .queryParam("areaCode", areaCode)
        .queryParam("sigunguCode", sigunguAreaCode)
        .queryParam("listYN", "N")
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();
    TourApiBaseResModelDto<?> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<?>>() {
            }).getBody();

    return modelDto.getResponse().getBody().getTotalAttractionsCount();
  }

  public ResAttractionsDetailDto getAttractionDetail(Long contentId, Integer contentTypeId, String language)
      throws UnsupportedEncodingException {

    Language lan = null;
    if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
    if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;

    ResAttractionDetailCommonDto detailCommonDto= getDetailCommon(contentId, contentTypeId, language);

    LinkedHashMap<String, Object> detailIntroMap = getDetailIntroMap(contentId, contentTypeId, language);

    List<String> images = getAttractionDetailImages(contentId, contentTypeId, language);
    if(detailCommonDto.getImage() != null){
      images.add(detailCommonDto.getImage());
    }

    Set<AudioGuide> guides = new HashSet<>();
    Set<AudioCourseElement> relatedCourses = audioCourseService.getRelatedCourse(contentId, contentTypeId, language);
    relatedCourses.forEach(audioCourseElement -> {
      audioCourseElement.getGuides()
          .stream()
          .forEach(audioGuideCourse -> guides.add(audioGuideCourse.getAudioGuide()));

      images.addAll(audioCourseElement.getImages().stream()
        .map(image->image + ImageSizeType.MIDDLE.getSuffix())
        .collect(Collectors.toList())
      );
    });

    Language finalLan = lan;
    List<ResAudioGuideItemDto> relatedGuidesList = guides.stream()
        .map(guide -> audioGuideService.toAudioGuideItem(guide, finalLan))
        .filter(resAudioGuideItemDto -> resAudioGuideItemDto != null)
        .collect(Collectors.toList());

    String areaName = areaCodeNameMap.get(Triple.of(lan, detailCommonDto.getAreaCode(), null));

    return ResAttractionsDetailDto.builder()
        .contentId(contentId)
        .contentTypeId(contentTypeId)
        .language(language)
        .areaName(areaName)
        .detailCommonInfo(detailCommonDto)
        .detailIntroductionInfo(detailIntroMap)
        .hasRelatedAudioGuides(guides.size()!=0 ? true : false)
        .relatedAudioGuidesCount(guides.size())
        .relatedAudioGuideLists(relatedGuidesList)
        .imagesList(images)
        .build();
  }

  private List<String> getAttractionDetailImages(Long contentId, Integer contentTypeId,
      String language) throws UnsupportedEncodingException {
    List<String> detailImages = new ArrayList<>();

    UriComponents components = createBaseUriBuilder(language, "/detailImage")
        .queryParam("contentId", contentId)
        .queryParam("contentTypeId", contentTypeId)
        .queryParam("imageYN", "Y")
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();
    TourApiBaseResModelDto<?> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<?>>() {
            }).getBody();

    if(modelDto.getResponse().getBody().getItems().getClass()!=String.class){
      ResAttractionDetailImageListDto imageListDto = mapper.convertValue(modelDto.getResponse().getBody().getItems(), ResAttractionDetailImageListDto.class);
      List<String> images = imageListDto.getImageList().stream()
          .map(imageItemDto -> imageItemDto.getImage())
          .collect(Collectors.toList());

      detailImages.addAll(images);
    }
    return detailImages;
  }

  private LinkedHashMap<String, Object> getDetailIntroMap(Long contentId, Integer contentTypeId,
      String language) throws UnsupportedEncodingException {
    UriComponents components = createBaseUriBuilder(language, "/detailIntro")
        .queryParam("contentId", contentId)
        .queryParam("contentTypeId", contentTypeId)
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();
    TourApiBaseResModelDto<LinkedHashMap> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<LinkedHashMap>>() {
            }).getBody();

    LinkedHashMap<String, Object> detailIntroMap =
        mapper.convertValue(modelDto.getResponse().getBody()
            .getItems().get("item"), LinkedHashMap.class);
    detailIntroMap.remove("contentid");
    detailIntroMap.remove("contenttypeid");

    for(Entry<String, Object> entry : detailIntroMap.entrySet()){
      if(entry.getValue().getClass() == String.class){
        detailIntroMap.put(entry.getKey(), parseHtmlToPlainText((String) entry.getValue()));
      }
    }

    return detailIntroMap;
  }

  public ResAttractionDetailCommonDto getDetailCommon(Long contentId, Integer contentTypeId,
      String language) throws UnsupportedEncodingException {

    UriComponents components = createBaseUriBuilder(language, "/detailCommon")
        .queryParam("contentId", contentId)
        .queryParam("contentTypeId", contentTypeId)
        .queryParam("defaultYN", "Y")
        .queryParam("addrinfoYN", "Y")
        .queryParam("mapinfoYN", "Y")
        .queryParam("overviewYN", "Y")
        .queryParam("firstImageYN", "Y")
        .queryParam("areacodeYN", "Y")
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();
    TourApiBaseResModelDto<?> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<?>>() { //ResAttractionsDetailDto
            }).getBody();

    if(modelDto.getResponse().getBody().getItems().equals("")){
      throw new InvalidAttractionIdException();
    }

    TourApiBaseResModelDto<ResAttractionsDetailDto> parsedModelDto = mapper.convertValue(modelDto, new TypeReference<>() {});

    ResAttractionDetailCommonDto detailCommonDto = parsedModelDto.getResponse().getBody().getItems().getDetailCommonInfo();

    if(detailCommonDto.getHomepage() != null){
      detailCommonDto.setHomepage(parseHtmlToPlainText(detailCommonDto.getHomepage()));
    }
    if (detailCommonDto.getOverview() != null) {
      detailCommonDto.setOverview(parseHtmlToPlainText(detailCommonDto.getOverview()));
    }

    return detailCommonDto;
  }

  private String parseHtmlToPlainText(String htmlString){
    return Jsoup.parse(htmlString).wholeText();
  }

  private boolean isValidAreaCode(Integer areaCode, Integer sigunguAreaCode) {
    if(sigunguAreaCode == null){
      return touristAreaRepository.existsByAreaCode(areaCode);
    }
    return touristAreaRepository.existsByAreaCodeAndSigunguCode(areaCode, sigunguAreaCode);
  }

  private TouristArea getTouristArea(String languageVersion, Integer areaCode, Integer sigunguAreaCode) {
    TouristArea touristArea;
    Language language= null;

    if(languageVersion.equals(Language.KOREAN.getVersion())){
      language = Language.KOREAN;
    }
    if(languageVersion.equals(Language.ENGLISH.getVersion())){
      language = Language.ENGLISH;
    }

    if(sigunguAreaCode==null){
      touristArea = touristAreaRepository.findByAreaCodeAndLanguage(areaCode, language)
          .orElseThrow(NoSuchElementException::new);
    }else{
      touristArea = touristAreaRepository.findByAreaCodeAndSigunguCodeAndLanguage(areaCode, sigunguAreaCode, language)
          .orElseThrow(NoSuchElementException::new);
    }
    return touristArea;
  }

  public SearchResultAttractionListDto searchAttractionKeyword(String keyword, Language language, Integer pageNumber, Integer pageSize) throws UnsupportedEncodingException {
    UriComponents components = createBaseUriBuilder(language.getVersion(), "/searchKeyword")
        .queryParam("pageNo", pageNumber)
        .queryParam("numOfRows", pageSize)
        .queryParam("arrange", "B") //인기순
        .queryParam("keyword", keyword)
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();

    TourApiBaseResModelDto<?> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<?>>() {}).getBody();

    if(modelDto.getResponse().getBody().getItems().equals("")){ //for no result
      return SearchResultAttractionListDto.builder()
          .attractionsList(Collections.emptyList())
          .totalAttractionCount(modelDto.getResponse().getBody().getTotalAttractionsCount())
          .build();
    }else{
      TourApiBaseResModelDto<LinkedHashMap> parsedModelDto = mapper.convertValue(modelDto, new TypeReference<>() {});
      SearchResultAttractionListDto resultAttractionListDto = null;

      if(parsedModelDto.getResponse().getBody().getItems().get("item") instanceof List){ //for array response
        SearchResultAttractionListDto attractionListDto = mapper.convertValue(modelDto.getResponse().getBody().getItems(), SearchResultAttractionListDto.class);
        attractionListDto.setTotalAttractionCount(modelDto.getResponse().getBody().getTotalAttractionsCount());

        resultAttractionListDto = attractionListDto;

      }else{ //for single one object response
        ResAreaAttractionItemDto singleAttractionDto = mapper.convertValue(parsedModelDto.getResponse().getBody().getItems().get("item"), ResAreaAttractionItemDto.class);
        SearchResultAttractionListDto attractionListDto = SearchResultAttractionListDto.builder()
            .totalAttractionCount(modelDto.getResponse().getBody().getTotalAttractionsCount())
            .attractionsList(new ArrayList<>() {{
              add(singleAttractionDto);
            }})
            .build();

        resultAttractionListDto = attractionListDto;
      }

      resultAttractionListDto.getAttractionsList().stream()
          .forEach(resAreaAttractionItemDto -> {
            Triple<Language, Integer, Integer> areaCodeNameKey = Triple.of(language, resAreaAttractionItemDto.getAreaCode(), null);
            String areaName = areaCodeNameMap.get(areaCodeNameKey);
            resAreaAttractionItemDto.setAreaName(areaName);
          });

      return resultAttractionListDto;
    }
  }

  private List<AreaCodeAndNameItemDto> getAreaCodeNameList(Language language, Integer areaCode) throws UnsupportedEncodingException {

    UriComponents components = createBaseUriBuilder(language.getVersion(), "/areaCode")
        .queryParam("pageNo", 1)
        .queryParam("numOfRows", 50)
        .queryParam("areaCode", areaCode == null ? "" : areaCode)
        .build(false);
    HttpEntity<?> httpEntity = createHttpEntityHeader();

    TourApiBaseResModelDto<?> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<?>>() {}).getBody();

    if(modelDto.getResponse().getBody().getItems().equals("")){ //for no result
      return Collections.emptyList();

    }else{
      TourApiBaseResModelDto<LinkedHashMap> parsedModelDto = mapper.convertValue(modelDto, new TypeReference<>() {});

      if(parsedModelDto.getResponse().getBody().getItems().get("item") instanceof List) { //for array response
        AreaCodeAndNameListDto areaCodeAndNameListDto = mapper.convertValue(modelDto.getResponse().getBody().getItems(), AreaCodeAndNameListDto.class);
        List<AreaCodeAndNameItemDto> areaCodeNameList = areaCodeAndNameListDto.getAreaCodeAndNameItemList();
        return areaCodeNameList;
      }else{ //for single one object response
        AreaCodeAndNameItemDto areaCodeAndNameItemDto = mapper.convertValue(parsedModelDto.getResponse().getBody().getItems().get("item"), AreaCodeAndNameItemDto.class);
        return new ArrayList<>(){{add(areaCodeAndNameItemDto);}};
      }
    }
  }
}
