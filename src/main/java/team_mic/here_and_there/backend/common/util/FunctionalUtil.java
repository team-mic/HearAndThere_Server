package team_mic.here_and_there.backend.common.util;

import java.util.function.Consumer;

public class FunctionalUtil {

  public static <T> Consumer<T> throwingConsumerWrapper(
      ThrowingConsumer<T, Exception> throwingConsumer) {

    return input -> {
      try {
        throwingConsumer.accept(input);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
    };
  }
}
