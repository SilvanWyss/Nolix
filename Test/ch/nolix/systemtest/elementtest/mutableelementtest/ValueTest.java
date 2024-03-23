//package declaration
package ch.nolix.systemtest.elementtest.mutableelementtest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.element.property.Value;

//class
final class ValueTest extends StandardTest {

  //method
  @Test
  void testCase_forInt() {

    //execution
    final var result = Value.forInt("amount", FunctionCatalogue::takeObjectAndDoNothing);

    //verification
    expect(result.getName()).isEqualTo("amount");
    expect(result.isEmpty());
  }

  //method
  @Test
  void testCase_getSpecification() {

    //setup
    final var testUnit = Value.forInt("amount", FunctionCatalogue::takeObjectAndDoNothing);
    testUnit.setValue(500);

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation("amount(500)");
  }

  //method
  @Test
  void testCase_setValue_whenTheGivenValueIsNull() {

    //setup
    final var testUnit = Value.forString("name", FunctionCatalogue::takeObjectAndDoNothing);

    //setup verification
    expect(testUnit.isEmpty());

    //execution & verification
    expectRunning(() -> testUnit.setValue(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @Test
  void testCase_setValue_whenTheGivenValueIsValid() {

    //setup
    final var testUnit = Value.forString("name", FunctionCatalogue::takeObjectAndDoNothing);

    //execution
    testUnit.setValue("Garfield");

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getValue()).isEqualTo("Garfield");
  }
}
