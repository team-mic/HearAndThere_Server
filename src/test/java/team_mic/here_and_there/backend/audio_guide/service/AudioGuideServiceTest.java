package team_mic.here_and_there.backend.audio_guide.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;

@ExtendWith(MockitoExtension.class)
public class AudioGuideServiceTest {

  @Mock
  private AudioGuideRepository audioGuideRepository;

  @InjectMocks
  private AudioGuideService audioGuideService;

  @Test
  public void 제외_소카테고리_테스트(){
    //given
    int excludedId = 2;
    int notExcludedId = 1;

    //when
    boolean expectedTrue = ReflectionTestUtils.invokeMethod(audioGuideService, "isExcludedSubCategory", excludedId);
    boolean expectedFalse = ReflectionTestUtils.invokeMethod(audioGuideService, "isExcludedSubCategory", notExcludedId);

    //then
    assertEquals(expectedTrue, true);
    assertEquals(expectedFalse, false);
  }

  @Test
  public void 랜덤Set_반환함수_테스트()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    //given
    int totalSize = 5;
    int requiredSize = 2;

    Method excludeCondition = audioGuideService.getClass().getDeclaredMethod("isExcludedSubCategory", new Class[]{Integer.class});
    excludeCondition.setAccessible(true);

    //when
    Set<Integer> result = ReflectionTestUtils.invokeMethod(audioGuideService, "getRandomIndexes", totalSize, requiredSize, excludeCondition);

    //then
    assertEquals(result.size(), requiredSize);
    assertEquals(result.contains(3), false);
  }
}
