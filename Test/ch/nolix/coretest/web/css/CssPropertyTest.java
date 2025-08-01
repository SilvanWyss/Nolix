package ch.nolix.coretest.web.css;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.core.web.css.CssProperty;

final class CssPropertyTest extends StandardTest {

  @Test
  void testCase_toString() {

    //setup
    final var testUnit = CssProperty.withNameAndValue("width", "200px");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("width: 200px;");
  }

  @Test
  void testCase_withNameAndValue() {

    //execution
    final var result = CssProperty.withNameAndValue("width", "200px");

    //verification
    expect(result.getName()).isEqualTo("width");
    expect(result.getValue()).isEqualTo("200px");
  }

  @Test
  void testCase_withNameAndValue_whenTheGivenNameIsNull() {

    //execution & verification
    expectRunning(() -> CssProperty.withNameAndValue(null, "200px"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given name is null.");
  }

  @Test
  void testCase_withNameAndValue_whenTheGivenValueIsNull() {

    //setup
    final String value = null;

    //execution & verification
    expectRunning(() -> CssProperty.withNameAndValue("width", value))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");
  }
}
