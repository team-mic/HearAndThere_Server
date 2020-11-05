package team_mic.here_and_there.backend.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {

    KOREAN("kor"),
    ENGLISH("eng");

    private final String language;
}
