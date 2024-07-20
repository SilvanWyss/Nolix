//package declaration
package ch.nolix.coretest.commontypetooltest.stringtooltest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.commontypetool.stringtool.GlobalStringTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
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
  "0, ''", //
  "1, '\t'", //
  "2, '\t\t'", //
  "3, '\t\t\t'", //
  "4, '\t\t\t\t'", //
  "5, '\t\t\t\t\t'", //
  "6, '\t\t\t\t\t\t'", //
  "7, '\t\t\t\t\t\t\t'", //
  "8, '\t\t\t\t\t\t\t\t'", //
  "9, '\t\t\t\t\t\t\t\t\t'", //
  "10, '\t\t\t\t\t\t\t\t\t\t'" //
  })
  void testCase_createTabs(final int tabCount, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.createTabs(tabCount);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_createTabs_whenTheGivenTabCountIsNegative() {

    //execution & verification
    expectRunning(() -> GlobalStringTool.createTabs(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given tab count '-1' is negative.");
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', '{}'", //
  "zebra, {zebra}" //
  })
  void testCase_getIngetInBraces(final String string, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.getInBraces(string);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_getIngetInBraces_whenTheGivenObjectIsNull() {

    //execution & verification
    expectRunning(() -> GlobalStringTool.getInBraces(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Object is null.");
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "'', '()'", //
  "zebra, (zebra)" //
  })
  void testCase_getInParantheses(final String string, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.getInParantheses(string);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_getInParantheses_whenTheGivenObjectIsNull() {

    //execution & verification
    expectRunning(() -> GlobalStringTool.getInParantheses(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Object is null.");
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
  @Test
  void testCase_getInSingleQuotes_whenTheGivenObjectIsNull() {

    //execution & verification
    expectRunning(() -> GlobalStringTool.getInSingleQuotes(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Object is null.");
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "cheeseburger, ''", //
  "cheeseburger, c", //
  "cheeseburger, ch", //
  "cheeseburger, che", //
  "cheeseburger, chee", //
  "cheeseburger, chees", //
  "cheeseburger, cheese", //
  "cheeseburger, cheeseb", //
  "cheeseburger, cheesebu", //
  "cheeseburger, cheesebur", //
  "cheeseburger, cheeseburg", //
  "cheeseburger, cheeseburge", //
  "cheeseburger, cheeseburger", //
  "cheeseburger, C", //
  "cheeseburger, cH", //
  "cheeseburger, chE", //
  "cheeseburger, cheE", //
  "cheeseburger, cheeS", //
  "cheeseburger, cheesE", //
  "cheeseburger, cheeseB", //
  "cheeseburger, cheesebU", //
  "cheeseburger, cheesebuR", //
  "cheeseburger, cheeseburG", //
  "cheeseburger, cheeseburgE", //
  "cheeseburger, cheeseburgeR" //
  })
  void testCase_startsWithIgnoringCase_whenTheGivenStringStartsWithTheGivenPrefix(
    final String string,
    final String prefix) {

    //execution
    final var result = GlobalStringTool.startsWithIgnoringCase(string, prefix);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  ", ''", //
  ", c", //
  ", ch", //
  ", che", //
  ", chee", //
  ", chees", //
  ", cheese", //
  ", cheeseb", //
  ", cheesebu", //
  ", cheesebur", //
  ", cheeseburg", //
  ", cheeseburge", //
  ", cheeseburger", //
  ",", //
  "c,", //
  "ch,", //
  "che,", //
  "chee,", //
  "chees,", //
  "cheese,", //
  "cheeseb,", //
  "cheesebu,", //
  "cheesebur,", //
  "cheeseburg,", //
  "cheeseburge,", //
  "cheeseburger,", //
  "cheeseburger, h", //
  "cheeseburger, he", //
  "cheeseburger, hee", //
  "cheeseburger, hees", //
  "cheeseburger, heese", //
  "cheeseburger, heeseb", //
  "cheeseburger, heesebu", //
  "cheeseburger, heesebur", //
  "cheeseburger, heeseburg", //
  "cheeseburger, heeseburge", //
  "cheeseburger, heeseburger", //
  "'', c", //
  "'', ch", //
  "'', che", //
  "'', chee", //
  "'', chees", //
  "'', cheese", //
  "'', cheeseb", //
  "'', cheesebu", //
  "'', cheesebur", //
  "'', cheeseburg", //
  "'', cheeseburge", //
  "'', cheeseburger" //
  })
  void testCase_startsWithIgnoringCase_whenTheGivenStringDoesNotStartWithTheGivenPrefix(
    final String string,
    final String prefix) {

    //execution
    final var result = GlobalStringTool.startsWithIgnoringCase(string, prefix);

    //verification
    expectNot(result);
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
  "x", //
  "y", //
  "z", //
  "-1", //
  "+1", //
  "2", //
  "3", //
  "10" //
  })
  void testCase_toBoolean_whenTheGivenStringDoesNotRepresentABoolean(final String string) {

    //execution & verification
    expectRunning(() -> GlobalStringTool.toBoolean(string))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given String '" + string + "' does not represent a boolean.");
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "-1.5", //
  "-1.0", //
  "-1.5", //
  "0.0", //
  "0.5", //
  "1.0", //
  "1.5" //
  })
  void testCase_toDouble_whenTheGivenStringRepresentsADouble(final String string) {

    //execution
    final var result = GlobalStringTool.toDouble(string);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "-2", //
  "-1", //
  "-0", //
  "0", //
  "1", //
  "2", //
  })
  void testCase_toDouble_whenTheGivenStringDoesNotRepresentADouble(final String string) {

    //execution & verification
    expectRunning(() -> GlobalStringTool.toDouble(string))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given String '" + string + "' does not represent a double.");
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
  "§cursor, §CURSOR", //
  "§CURSOR, §CURSOR", //
  "§Cursor, §CURSOR", //
  "cursor500, CURSOR500", //
  "CURSOR500, CURSOR500", //
  "Cursor500, CURSOR500", //
  "cursor§, CURSOR§", //
  "CURSOR§, CURSOR§", //
  "Cursor§, CURSOR§", //
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
  "§cursor, §Cursor", //
  "§CURSOR, §Cursor", //
  "§Cursor, §Cursor", //
  "cursor500, Cursor500", //
  "CURSOR500, Cursor500", //
  "Cursor500, Cursor500", //
  "cursor§, Cursor§", //
  "CURSOR§, Cursor§", //
  "Cursor§, Cursor§", //
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
