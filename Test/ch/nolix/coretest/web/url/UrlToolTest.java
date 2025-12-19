package ch.nolix.coretest.web.url;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.core.web.url.UrlTool;

/**
 * @author Silvan Wyss
 */
final class UrlToolTest extends StandardTest {
  @Test
  void testCase_getDisplayTextForUrl_whenTheGivenUrlIsNull() {
    //setup
    final var testUnit = new UrlTool();

    //execution & verification
    expectRunning(() -> testUnit.getDisplayTextForUrl(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given url is null.");
  }

  @ParameterizedTest
  @CsvSource({
  "http://nolix.ch, nolix.ch",
  "https://nolix.ch, nolix.ch",
  "http://www.nolix.ch, nolix.ch",
  "https://www.nolix.ch, nolix.ch",
  "www.nolix.ch, nolix.ch",
  "nolix.ch, nolix.ch"
  })
  void testCase_getDisplayTextForUrl_whenTheGivenUrlIsNotNull(final String url, final String expectedResult) {
    //setup
    final var testUnit = new UrlTool();

    //execution
    final var result = testUnit.getDisplayTextForUrl(url);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
