package team_mic.here_and_there.backend.attraction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
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
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionDetailCommonDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionDetailImageListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionsDetailDto;
import team_mic.here_and_there.backend.attraction.dto.response.TourApiBaseResModelDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.service.AudioCourseService;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
import team_mic.here_and_there.backend.common.domain.Language;

@Service
public class AttractionService {

  private final RestTemplate restTemplate;

  @Autowired
  private TouristAreaService touristAreaService;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private AudioCourseService audioCourseService;
  @Autowired
  private AudioGuideService audioGuideService;

  @Value("${tour.api.url.kor}")
  private String korTourApiUrl;

  @Value("${tour.api.url.eng}")
  private String engTourApiUrl;

  @Value("${tour.api.serviceKey.kor}")
  private String korServiceKey;

  @Value("${tour.api.serviceKey.eng}")
  private String engServiceKey;

  public AttractionService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
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
/*
  private String getAreaNameFromAreaCode(Integer areaCode) throws UnsupportedEncodingException {

    UriComponentsBuilder builder = createBaseUriBuilder("/areaCode");
    UriComponents components = builder.queryParam("numOfRows", 17)
        .queryParam("pageNo", 1)
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();

    TourApiBaseResModelDto<AreaCodeAndNameListDto> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<AreaCodeAndNameListDto>>() {
            }).getBody();

    AreaCodeAndNameListDto listDto = modelDto.getResponse().getBody().getItems();

    String areaName = listDto.getAreaCodeAndNameItemList().stream()
        .filter(areaCodeAndNameItemDto -> areaCodeAndNameItemDto.getCode() == areaCode)
        .findFirst()
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST))
        .getAreaName();

    return areaName;
  }*/

  /*
  public ResMainFixedAttractionListDto getFixedMainAttractionList(String area, String language) {
    if (language.equals(Language.KOREAN.getVersion())) {
      if (area.equals("seoul")) {
        return getFixedSeoulAttractionList(Language.KOREAN);
      }
    }

    if (language.equals(Language.ENGLISH.getVersion())) {
      if (area.equals("seoul")) {
        return getFixedSeoulAttractionList(Language.ENGLISH);
      }
    }
    throw new NoSuchElementException();
  }

  private ResMainFixedAttractionListDto getFixedSeoulAttractionList(Language language) {
    List<ResMainFixedAttractionListItemDto> itemList = new ArrayList<>();
    String lanAreaName = null;

    if(language.equals(Language.KOREAN)){
      lanAreaName = "서울";

      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(126508L)
          .attractionContentTypeId(12L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg")
          .title("경복궁")
          .build());
      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(126498L)
          .attractionContentTypeId(12L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/77/2553577_image2_1.jpg")
          .title("롯데월드")
          .build());
      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(126535L)
          .attractionContentTypeId(12L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/95/2660695_image2_1.jpg")
          .title("N 서울타워")
          .build());
      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(126537L)
          .attractionContentTypeId(12L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/06/2512006_image2_1.jpg")
          .title("북촌 한옥마을")
          .build());
    }

    if(language.equals(Language.ENGLISH)){
      lanAreaName = "Seoul";

      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(264337L)
          .attractionContentTypeId(76L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/23/2678623_image2_1.jpg")
          .title("Gyeongbokgung Palace")
          .build());
      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(264152L)
          .attractionContentTypeId(76L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/77/2553577_image2_1.jpg")
          .title("Lotte World")
          .build());
      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(264550L)
          .attractionContentTypeId(76L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/95/2660695_image2_1.jpg")
          .title("Namsan Seoul Tower")
          .build());
      itemList.add(ResMainFixedAttractionListItemDto.builder()
          .attractionContentId(561382L)
          .attractionContentTypeId(76L)
          .thumbnailImageUrl("http://tong.visitkorea.or.kr/cms/resource/06/2512006_image2_1.jpg")
          .title("Bukchon Hanok Village")
          .build());
    }

    return ResMainFixedAttractionListDto.builder()
        .areaName(lanAreaName)
        .attractionItemList(itemList)
        .language(language.getVersion())
        .build();
  }
  */


  public ResAreaAttractionsListDto getAreaAttractionsList(Integer areaCode, Integer sigunguAreaCode,
      Integer pageNumber, Integer pageSize, String language)
      throws IOException {

    if(!touristAreaService.isValidAreaCode(areaCode, sigunguAreaCode)){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); // TODO : custom exception
    }

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
    }

    TouristArea area = touristAreaService.getTouristArea(language, areaCode, sigunguAreaCode);
    listDto.setAreaName(area.getAreaName());
    listDto.setAreaMainImageUrl(area.getThumbnailImage());
    listDto.setLanguage(language);
    listDto.setAreaCode(areaCode);
    listDto.setListOrder("popular");
    listDto.setSigunguAreaCode(sigunguAreaCode);
    listDto.setPageNumber(pageNumber);

    return listDto;
  }

  public ResAttractionsDetailDto getAttractionDetail(Long contentId, Integer contentTypeId, String language)
      throws UnsupportedEncodingException {

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
          .forEach(audioGuideCourse -> guides.add(audioGuideCourse.getAudioGuide()));

      images.addAll(audioCourseElement.getImages());
    });

    List<ResAudioGuideItemDto> relatedGuidesList = guides.stream()
        .map(guide -> audioGuideService.toAudioGuideItem(guide, language))
        .collect(Collectors.toList());

    return ResAttractionsDetailDto.builder()
        .contentId(contentId)
        .contentTypeId(contentTypeId)
        .language(language)
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

  private ResAttractionDetailCommonDto getDetailCommon(Long contentId, Integer contentTypeId,
      String language) throws UnsupportedEncodingException {

    UriComponents components = createBaseUriBuilder(language, "/detailCommon")
        .queryParam("contentId", contentId)
        .queryParam("contentTypeId", contentTypeId)
        .queryParam("defaultYN", "Y")
        .queryParam("addrinfoYN", "Y")
        .queryParam("mapinfoYN", "Y")
        .queryParam("overviewYN", "Y")
        .queryParam("firstImageYN", "Y")
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();
    TourApiBaseResModelDto<ResAttractionsDetailDto> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<ResAttractionsDetailDto>>() {
            }).getBody();

    ResAttractionDetailCommonDto detailCommonDto = modelDto.getResponse().getBody().getItems().getDetailCommonInfo();

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
}
