/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.web.url;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.web.url.UrlTool;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class UrlToolTest extends StandardTest {
  @Test
  void testCase_getDisplayTextForUrl_whenTheGivenUrlIsNull() {
    // setup
    final var testUnit = new UrlTool();

   // execute & verification
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
    // setup
    final var testUnit = new UrlTool();

   // execute
    final var result = testUnit.getDisplayTextForUrl(url);

   // verify
    expect(result).isEqualTo(expectedResult);
  }
}
