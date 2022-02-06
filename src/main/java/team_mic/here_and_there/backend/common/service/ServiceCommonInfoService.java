package team_mic.here_and_there.backend.common.service;

import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.common.domain.entity.ServiceCommonInfo;
import team_mic.here_and_there.backend.common.domain.repository.ServiceCommonInfoRepository;

@RequiredArgsConstructor
@Service
public class ServiceCommonInfoService {

  private final ServiceCommonInfoRepository serviceCommonInfoRepository;
  private final static String AOS_APP_VERSION_KEY = "aos-app-version:latest";

  public String getLatestAppVersionOfService() {
    ServiceCommonInfo serviceCommonInfo = serviceCommonInfoRepository.findByInfoNameEquals(AOS_APP_VERSION_KEY)
        .orElseThrow(NoSuchElementException::new); //TODO custom exception

    return serviceCommonInfo.getValue();
  }

  @Transactional
  public void updateLatestAppVersionOfService(String latestVersion) {
    ServiceCommonInfo serviceCommonInfo = serviceCommonInfoRepository.findByInfoNameEquals(AOS_APP_VERSION_KEY)
        .orElse(serviceCommonInfoRepository.save(ServiceCommonInfo
            .builder()
            .infoName(AOS_APP_VERSION_KEY)
            .value(latestVersion)
            .build()));

    serviceCommonInfo.updateValue(latestVersion);
  }
}
