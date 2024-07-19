//package declaration
package ch.nolix.coretest.commontypetooltest.stringtooltest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.commontypetool.stringtool.GlobalStringTool;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GlobalStringToolTest extends StandardTest {

  //method
  @ParameterizedTest
  @CsvSource({
  "'', 0, ''", //
  "cheeseburger, 0, cheeseburger", //
  "cheeseburger, 1, cheeseburge", //
  "cheeseburger, 2, cheeseburg", //
  "cheeseburger, 3, cheesebur", //
  "cheeseburger, 4, cheesebu", //
  "cheeseburger, 5, cheeseb", //
  "cheeseburger, 6, cheese", //
  "cheeseburger, 7, chees", //
  "cheeseburger, 8, chee", //
  "cheeseburger, 9, che", //
  "cheeseburger, 10, ch", //
  "cheeseburger, 11, c", //
  "cheeseburger, 12, ''", //
  })
  void testCase_createStringWithoutLastCharacters(
    final String string,
    final int lastCharacterCount,
    final String expectedResult) {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters(string, lastCharacterCount);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', '()'", //
  "zebra, (zebra)" //
  })
  void testCase_getInParantheses(final String string, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.getInSingleQuotes(string);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', ''", //
  "zebra, '\'zebra\''" //
  })
  void testCase_getInSingleQuotes(final String string, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.getInSingleQuotes(string);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "0", //
  "F", //
  "FALSE", //
  "False", //
  "false" //
  })
  void testCase_toBoolean_whenTheGivenStringRepresentsFalse(final String string) {

    //execution
    final var result = GlobalStringTool.toBoolean(string);

    //verification
    expectNot(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "1", //
  "T", //
  "TRUE", //
  "True", //
  "true" //
  })
  void testCase_toBoolean_whenTheGivenStringRepresentsTrue(final String string) {

    //execution
    final var result = GlobalStringTool.toBoolean(string);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', ''", //
  "cursor, CURSOR", //
  "CURSOR, CURSOR", //
  "Cursor, CURSOR", //
  "cursor_icon, CURSOR_ICON", //
  "CURSOR_ICON, CURSOR_ICON", //
  "Cursor_Icon, CURSOR_ICON", //
  "cursorIcon, CURSOR_ICON", //
  "CursorIcon, CURSOR_ICON", //
  "500cursor, 500CURSOR", //
  "500CURSOR, 500CURSOR", //
  "500Cursor, 500CURSOR", //
  "cursor500, CURSOR500", //
  "CURSOR500, CURSOR500", //
  "Cursor500, CURSOR500", //
  "cursor500icon, CURSOR500ICON", //
  "CURSOR500ICON, CURSOR500ICON", //
  "Cursor500Icon, CURSOR500ICON" //
  })
  void testCase_toCapitalSnakeCase(final String string, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.toCapitalSnakeCase(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', ''", //
  "cursor, Cursor", //
  "CURSOR, Cursor", //
  "Cursor, Cursor", //
  "cursor_icon, CursorIcon", //
  "CURSOR_ICON, CursorIcon", //
  "Cursor_Icon, CursorIcon", //
  "cursorIcon, CursorIcon", //
  "CursorIcon, CursorIcon", //
  "500cursor, 500Cursor", //
  "500CURSOR, 500Cursor", //
  "500Cursor, 500Cursor", //
  "cursor500, Cursor500", //
  "CURSOR500, Cursor500", //
  "Cursor500, Cursor500", //
  "cursor500icon, Cursor500Icon", //
  "CURSOR500ICON, Cursor500Icon", //
  "Cursor500Icon, Cursor500Icon" //
  })
  void testCase_toPascalCase(final String string, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.toPascalCase(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
