package team_mic.here_and_there.backend.audio_course.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElementLanguageContent;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioCourseElementLanguageContentRepository;
import team_mic.here_and_there.backend.audio_course.dto.response.ResAudioCourseInfoItemDto;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.common.domain.ImageSizeType;

@RequiredArgsConstructor
@Service
public class AudioCourseService {

  private final AudioCourseElementLanguageContentRepository audioCourseElementLanguageContentRepository;

  public Set<AudioCourseElement> getRelatedCourse(Long contentId, Integer contentTypeId, String language){
    List<AudioCourseElementLanguageContent> languageContentList = audioCourseElementLanguageContentRepository
        .findAllByTourApiAttractionContentIdAndTourApiAttractionContentTypeId(contentId, contentTypeId);
    Set<AudioCourseElement> courseElements = new HashSet<>();

    languageContentList.stream().forEach(languageContent
        -> {
      if(languageContent.getLanguage().getVersion().equals(language)){
        courseElements.add(languageContent.getAudioCourseElement());
      }
    });

    return courseElements;
  }
  public List<ResAudioCourseInfoItemDto> getAudioGuidesCourseList(AudioGuide guide, String language) {

    List<ResAudioCourseInfoItemDto> courseList = guide.getCourse().parallelStream()
        .map(audioGuideCourse -> toAudioCourseInfoItem(audioGuideCourse, language))
        .collect(Collectors.toList());

    return courseList;
  }

  private ResAudioCourseInfoItemDto toAudioCourseInfoItem(
      AudioGuideCourse audioGuideCourse, String language) {

    AudioCourseElement courseElement = audioGuideCourse.getAudioCourseElement();
    Optional<AudioCourseElementLanguageContent> languageContent = Optional.empty();

    for(AudioCourseElementLanguageContent content : courseElement.getLanguageContents()){
      if(content.getLanguage().getVersion().equals(language)){
        languageContent = Optional.of(content);
      }
    }

    AudioCourseElementLanguageContent correspondingContent = languageContent.orElseThrow(
        NoSuchElementException::new); //TODO : custom exception

    return ResAudioCourseInfoItemDto.builder()
        .audioCourseId(courseElement.getId())
        .courseImages(courseElement.getImages().stream()
            .map(image-> image + ImageSizeType.THUMBNAIL.getSuffix())
            .collect(Collectors.toList()))
        .courseOrderNumber(audioGuideCourse.getOrderNumber())
        .estimatedTravelTime(courseElement.getEstimatedTravelTime())
        .title(correspondingContent.getTitle())
        .hasRelatedAttraction(correspondingContent.getTourApiAttractionContentId()==null ? false : true)
        .relatedTourApiAttractionContentId(correspondingContent.getTourApiAttractionContentId())
        .relatedTourApiAttractionContentTypeId(correspondingContent.getTourApiAttractionContentTypeId())
        .build();
  }
}
