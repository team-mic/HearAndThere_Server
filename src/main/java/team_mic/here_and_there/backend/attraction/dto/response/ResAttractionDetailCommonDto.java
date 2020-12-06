package team_mic.here_and_there.backend.attraction.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"title", "overview", "address", "zipcode", "homepage", "tel",
"mapLongitude", "mapLatitude"})
public class ResAttractionDetailCommonDto {

  @JsonProperty("homepage")
  private String homepage;

  @JsonProperty("tel")
  private String tel;

  @JsonProperty("title")
  private String title;

  private String address;

  @JsonProperty("zipcode")
  private Long zipcode;

  private Double mapLongitude;

  private Double mapLatitude;

  @JsonProperty("overview")
  private String overview;

  @JsonSetter("addr1")
  private void setAddress(String address){
    this.address = address;
  }

  @JsonGetter("address")
  private String getAddress(){ return this.address; }

  @JsonSetter("mapx")
  private void setMapLongitude(Double mapLongitude){
    this.mapLongitude = mapLongitude;
  }

  @JsonGetter("mapLongitude")
  private Double getMapLongitude(){ return this.mapLongitude; }

  @JsonSetter("mapy")
  private void setMapLatitude(Double mapLatitude){
    this.mapLatitude = mapLatitude;
  }

  @JsonGetter("mapLatitude")
  private Double getMapLatitude(){ return this.mapLatitude; }
}
