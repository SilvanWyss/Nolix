//package declaration
package ch.nolix.coretest.commontypetooltest.stringtooltest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GetInParenthesesMethodTest extends StandardTest {

  //method
  @Test
  void testCase_getInParantheses_whenGivenObjectIsNull() {

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.getInParentheses(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given 1th object is null.");
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', '()'", //
  "zebra, (zebra)", //
  "lorem ipsum, (lorem ipsum)"
  })
  void testCase_getInParantheses_when1StringIsGiven(final String string, final String expectedResult) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.getInParentheses(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getInParantheses_when3StringsAreGiven() {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.getInParentheses("antelope", "baboon", "elephant");

    //verification
    expect(result).isEqualTo("(antelope,baboon,elephant)");
  }

  //method
  @Test
  void testCase_getInParantheses_whenOneOfGivenStringsIsNull() {

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.getInParentheses("antelope", null, "baboon"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given 2th object is null.");
  }

  //method
  @Test
  void testCase_getInParantheses_whenGivenVarargsIsNull() {

    //setup
    final Object[] varargs = null;
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.getInParentheses("antelope", varargs))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given objects is null.");
  }
}
