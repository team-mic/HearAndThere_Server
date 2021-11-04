package team_mic.here_and_there.backend.attraction;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import team_mic.here_and_there.backend.attraction.service.AttractionService;

@SpringBootTest
public class AttractionTest {
    @Autowired
    private AttractionService attractionService;

    private RestTemplate restTemplate = new RestTemplate();
    private MockRestServiceServer mockServer;

    @Before
    public void init() {
      mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void 관광지_개수_가져오기_테스트() throws UnsupportedEncodingException {

    }
}
