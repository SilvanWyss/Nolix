/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.json.immutablejsonobject;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.json.ImmutableJsonArray;
import ch.nolix.base.document.json.ImmutableJsonNameValuePair;
import ch.nolix.base.document.json.ImmutableJsonNumber;
import ch.nolix.base.document.json.ImmutableJsonObject;
import ch.nolix.base.document.json.ImmutableJsonString;
import ch.nolix.base.environment.filesystem.FileSystemAccessor;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ToFormattedStringTest extends StandardTest {
  @Test
  void testCase_toFormattedString_1() {
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

  @Test
  void testCase_toFormattedString_2() {
    // setup
    final var testUnit = ImmutableJsonObject.withNameValuePairs(
      ImmutableJsonNameValuePair.withNameAndValue("date", ImmutableJsonString.withString("2030-01-01")),
      ImmutableJsonNameValuePair.withNameAndValue(
        "customer",
        ImmutableJsonObject.withNameValuePairs(
          ImmutableJsonNameValuePair.withNameAndValue("firstName", ImmutableJsonString.withString("Donald")),
          ImmutableJsonNameValuePair.withNameAndValue("lastName", ImmutableJsonString.withString("Duck")),
          ImmutableJsonNameValuePair.withNameAndValue(
            "deliveryAddress",
            ImmutableJsonObject.withNameValuePairs(
              ImmutableJsonNameValuePair.withNameAndValue("housenumber", ImmutableJsonString.withString("13")),
              ImmutableJsonNameValuePair.withNameAndValue("street", ImmutableJsonString.withString("Quack Street")),
              ImmutableJsonNameValuePair.withNameAndValue("city", ImmutableJsonString.withString("Duckburg")))))),
      ImmutableJsonNameValuePair.withNameAndValue(
        "positions",
        ImmutableJsonArray.withObjects(
          ImmutableJsonObject.withNameValuePairs(
            ImmutableJsonNameValuePair.withNameAndValue("product", ImmutableJsonString.withString("desk")),
            ImmutableJsonNameValuePair.withNameAndValue("amount", ImmutableJsonNumber.withNumber(1)),
            ImmutableJsonNameValuePair.withNameAndValue("pricePerPiece", ImmutableJsonNumber.withNumber(513))),
          ImmutableJsonObject.withNameValuePairs(
            ImmutableJsonNameValuePair.withNameAndValue("product", ImmutableJsonString.withString("chair")),
            ImmutableJsonNameValuePair.withNameAndValue("amount", ImmutableJsonNumber.withNumber(4)),
            ImmutableJsonNameValuePair.withNameAndValue("pricePerPiece", ImmutableJsonNumber.withNumber(113))),
          ImmutableJsonObject.withNameValuePairs(
            ImmutableJsonNameValuePair.withNameAndValue("product", ImmutableJsonString.withString("lamp")),
            ImmutableJsonNameValuePair.withNameAndValue("amount", ImmutableJsonNumber.withNumber(1)),
            ImmutableJsonNameValuePair.withNameAndValue("pricePerPiece", ImmutableJsonNumber.withNumber(213))))));

    // execute
    final var result = testUnit.toFormattedString();

    // verify
    final var expectedResult = FileSystemAccessor.readFile("./././././TestResource/sample_json/order.json");
    expect(result).isEqualTo(expectedResult);
  }
}
