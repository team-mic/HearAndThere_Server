package team_mic.here_and_there.backend.audio_guide.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResDirectionDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.TmapApiBaseResModelDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.TmapApiBaseResModelDto.Feature.Geometry;

@Service
public class DirectionApiService {

  private final RestTemplate restTemplate;

  private final static String APP_KEY = "appKey";
  private final static Integer MAX_STOPOVER_COUNT = 5;
  private final static Integer MAX_STOPOVER_CONTAINS_START_AND_END_POINT = MAX_STOPOVER_COUNT + 2;
  private final static String PEDESTRIAN_DIRECTIONS_URI_PATH = "/routes/pedestrian";
  @Value("${tmap.api.url}")
  private String tmapApiUrl;

  @Value("${tmap.api.appKey}")
  private String appKey;

  public DirectionApiService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public List<ResDirectionDto> getTracksPedestrianDirections(Set<AudioGuideTrackContainer> tracks)
      throws InterruptedException {

    Integer trackSize = tracks.size();
    Integer maxStopoverRequestCount = trackSize / MAX_STOPOVER_CONTAINS_START_AND_END_POINT;
    Integer remainingStopoverFinalRequestTrackCount =
        trackSize - (1 + (MAX_STOPOVER_CONTAINS_START_AND_END_POINT - 1) * maxStopoverRequestCount)
            + 1;

    Iterator<AudioGuideTrackContainer> tracksIterator = tracks.iterator();
    AudioTrack startTrack = tracksIterator.next().getAudioTrack();
    List<ResDirectionDto> directionList = new ArrayList<>();

    //max stopover request
    for (int requestCount = 0; requestCount < maxStopoverRequestCount; requestCount++) {
      UriComponentsBuilder builder = createBaseUriBuilder(PEDESTRIAN_DIRECTIONS_URI_PATH);

      builder.queryParam("startX", startTrack.getLocationLongitude())
          .queryParam("startY", startTrack.getLocationLatitude());

      builder.queryParam("passList", getPassListOfStops(tracksIterator, MAX_STOPOVER_COUNT));

      AudioTrack endTrack = tracksIterator.next().getAudioTrack();

      builder.queryParam("endX", endTrack.getLocationLongitude())
          .queryParam("endY", endTrack.getLocationLatitude());

      startTrack = endTrack;

      TmapApiBaseResModelDto modelDto = callPedestrianDirectionsApi(builder.build());
      getCoordinatesDirectionList(modelDto, directionList);
    }

    //final remaining request
    if (remainingStopoverFinalRequestTrackCount != 1) {
      Thread.sleep(500);
      UriComponentsBuilder finalBuilder = createBaseUriBuilder(PEDESTRIAN_DIRECTIONS_URI_PATH);

      finalBuilder.queryParam("startX", startTrack.getLocationLongitude())
          .queryParam("startY", startTrack.getLocationLatitude());

      finalBuilder.queryParam("passList",
            getPassListOfStops(tracksIterator, remainingStopoverFinalRequestTrackCount - 2));

      AudioTrack endTrack = tracksIterator.next().getAudioTrack();
      finalBuilder.queryParam("endX", endTrack.getLocationLongitude())
          .queryParam("endY", endTrack.getLocationLatitude());

      TmapApiBaseResModelDto modelDto = callPedestrianDirectionsApi(finalBuilder.build());
      getCoordinatesDirectionList(modelDto, directionList);
      System.out.println("final list size:"+ directionList.size());
    }

    return directionList;
  }

  private void getCoordinatesDirectionList(TmapApiBaseResModelDto modelDto,
      List<ResDirectionDto> directionList) {
    modelDto.getFeatureList().forEach(feature -> {
      Geometry geometry = feature.getGeometry();
      if(geometry.getType().equals("LineString")){
        geometry.getCoordinates().forEach(coordinate -> {
          Double longitude = ((List<Double>)coordinate).get(0);
          Double latitude = ((List<Double>)coordinate).get(1);
          System.out.println("Lon:"+longitude+"lat:"+latitude);
          directionList.add(ResDirectionDto.builder()
              .latitude(latitude)
              .longitude(longitude)
              .build());
        });
      }
    });
  }

  private TmapApiBaseResModelDto callPedestrianDirectionsApi(UriComponents components) {
    HttpEntity<?> httpEntity = createHttpEntityHeader();
    System.out.println("uri: "+components.toUriString());
    return restTemplate.exchange(components.toUriString(), HttpMethod.POST, httpEntity,
        new ParameterizedTypeReference<TmapApiBaseResModelDto>() {
        }).getBody();
  }

  private String getPassListOfStops(Iterator<AudioGuideTrackContainer> tracksIterator,
      Integer stopsCount) {

    if(stopsCount == 0) return null;

    StringBuilder builder = new StringBuilder();

    for (int stopCount = 0; stopCount < stopsCount; stopCount++) {
      AudioTrack track = tracksIterator.next().getAudioTrack();
      builder.append(track.getLocationLongitude().toString())
          .append(",")
          .append(track.getLocationLatitude().toString())
          .append("_");
    }
    String passList = builder.toString();

    return passList.substring(0, passList.length() - 1);
  }

  public HttpEntity<?> createHttpEntityHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.set(HttpHeaders.HOST, "apis.openapi.sk.com");
    headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    headers.set(APP_KEY, appKey);
    return new HttpEntity<>(headers);
  }

  public UriComponentsBuilder createBaseUriBuilder(String appendUrl) {

    return UriComponentsBuilder.fromHttpUrl(tmapApiUrl + appendUrl)
        .queryParam("version", "1")
        .queryParam("reqCoordType", "WGS84GEO")
        .queryParam("resCoordType", "WGS84GEO")
        .queryParam("startName", "출발지")
        .queryParam("endName", "목적지");
  }
}
