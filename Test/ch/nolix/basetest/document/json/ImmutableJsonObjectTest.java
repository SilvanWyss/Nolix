/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.json;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.json.ImmutableJsonNameValuePair;
import ch.nolix.base.document.json.ImmutableJsonNumber;
import ch.nolix.base.document.json.ImmutableJsonObject;
import ch.nolix.base.document.json.ImmutableJsonString;
import ch.nolix.base.environment.filesystem.FileSystemAccessor;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ImmutableJsonObjectTest extends StandardTest {
  @Test
  void testCase_toFormattedString() {
    // setup
    final var testUnit = ImmutableJsonObject.withNameValuePairs(
      ImmutableJsonNameValuePair.withNameAndValue("name", ImmutableJsonString.withString("Garfield")),
      ImmutableJsonNameValuePair.withNameAndValue("sex", ImmutableJsonString.withString("Male")),
      ImmutableJsonNameValuePair.withNameAndValue("ageInYears", ImmutableJsonNumber.withNumber(5)),
      ImmutableJsonNameValuePair.withNameAndValue("weightInGrams", ImmutableJsonNumber.withNumber(6500)));

    // execute
    final var result = testUnit.toFormattedString();

    // verify
    final var expectedResult = FileSystemAccessor.readFile("./././././TestResource/sample_json/garfield.json");
    expect(result).isEqualTo(expectedResult);
  }
}
