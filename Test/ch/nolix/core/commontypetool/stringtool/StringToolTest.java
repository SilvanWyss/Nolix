package ch.nolix.core.commontypetool.stringtool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class StringToolTest extends StandardTest {

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

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.createStringWithoutLastCharacters(string, lastCharacterCount);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.createTabs(tabCount);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createTabs_whenTheGivenTabCountIsNegative() {

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.createTabs(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given tab count '-1' is negative.");
  }

  @ParameterizedTest
  @CsvSource({
  "'', '{}'", //
  "zebra, {zebra}" //
  })
  void testCase_getIngetInBraces(final String string, final String expectedResult) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.getInBraces(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_getInInBraces_whenTheGivenObjectIsNull() {

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.getInBraces(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Object is null.");
  }

  @Test
  void testCase_getInSingleQuotes_whenTheGivenObjectIsNull() {

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.getInSingleQuotes(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Object is null.");
  }

  @ParameterizedTest
  @CsvSource({
  "''", //
  "cheeseburger", //
  "cheeseburger_", //
  "cheesburger5", //
  "cheesburger§" //
  })
  void testCase_isLowerCase_whenTheGivenStringIsLowerCase(final String string) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.isLowerCase(string);

    //verification
    expect(result).isTrue();
  }

  @ParameterizedTest
  @CsvSource({
  ",", //
  "Cheeseburger", //
  "CheeseBurger", //
  "Cheesburger5", //
  "Cheesburger§" //
  })
  void testCase_isLowerCase_whenTheGivenStringIsNotLowerCase(final String string) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.isLowerCase(string);

    //verification
    expect(result).isFalse();
  }

  @ParameterizedTest
  @CsvSource({
  "''", //
  "Cursor", //
  "CursorIcon" //
  })
  void testCase_iisPascalCase_whenTheGivenStringIsPascalCase(final String string) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.isPascalCase(string);

    //verification
    expect(result).isTrue();
  }

  @ParameterizedTest
  @CsvSource({
  ",", //
  "cursor", //
  "cursorIcon" //
  })
  void testCase_iisPascalCase_whenTheGivenStringIsNotPascalCase(final String string) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.isPascalCase(string);

    //verification
    expect(result).isFalse();
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.startsWithIgnoringCase(string, prefix);

    //verification
    expect(result).isTrue();
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.startsWithIgnoringCase(string, prefix);

    //verification
    expect(result).isFalse();
  }

  @ParameterizedTest
  @CsvSource({
  "0", //
  "F", //
  "FALSE", //
  "False", //
  "false" //
  })
  void testCase_toBoolean_whenTheGivenStringRepresentsFalse(final String string) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.toBoolean(string);

    //verification
    expect(result).isFalse();
  }

  @ParameterizedTest
  @CsvSource({
  "1", //
  "T", //
  "TRUE", //
  "True", //
  "true" //
  })
  void testCase_toBoolean_whenTheGivenStringRepresentsTrue(final String string) {

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.toBoolean(string);

    //verification
    expect(result).isTrue();
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.toBoolean(string))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given String '" + string + "' does not represent a boolean.");
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution & verification
    expectRunning(() -> testUnit.toDouble(string))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given String '" + string + "' does not represent a double.");
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.toCapitalSnakeCase(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

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

    //setup
    final var testUnit = new StringTool();

    //execution
    final var result = testUnit.toPascalCase(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
