//package declaration
package ch.nolix.coretest.commontypetooltest.chartooltest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.commontypetool.chartool.CharTool;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class CharToolTest extends StandardTest {

  //method
  @ParameterizedTest
  @ValueSource(chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' })
  void testCase_isDigit_whenTheGivenCharacterIsADigit(final char character) {

    //setup   
    final var testUnit = new CharTool();

    //execution
    final var result = testUnit.isDigit(character);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @ValueSource(chars = { 'A', 'a', '%', '&', '.', ',', '(', ')' })
  void testCase_isDigit_whenTheGivenCharacterIsNotADigit(final char character) {

    //setup   
    final var testUnit = new CharTool();

    //execution
    final var result = testUnit.isDigit(character);

    //verification
    expectNot(result);
  }

  //method
  @ParameterizedTest
  @ValueSource(chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' })
  void testCase_isHexadecimalDigit_whenGivenCharacterIsAHexadecimalDigit(final char character) {

    //setup   
    final var testUnit = new CharTool();

    //execution
    final var result = testUnit.isHexadecimalDigit(character);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @ValueSource(chars = { 'G', 'g', '%', '&', '.', ',', '(', ')' })
  void testCase_isHexadecimalDigit_whenTheGivenCharacterIsNotAHexadecimalDigit(final char character) {

    //setup   
    final var testUnit = new CharTool();

    //execution
    final var result = testUnit.isHexadecimalDigit(character);

    //verification
    expectNot(result);
  }
}
