//package declaration
package ch.nolix.coretest.commontypetooltest.stringtooltest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.commontypetool.stringtool.GlobalStringTool;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GlobalStringToolTest extends StandardTest {

  //method
  @Test
  void testCase_createStringWithoutLastCharacters_1A() {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters("cheeseburger", 0);

    //verification
    expect(result).isEqualTo("cheeseburger");
  }

  //method
  @Test
  void testCase_createStringWithoutLastCharacters_1B() {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters("cheeseburger", 6);

    //verification
    expect(result).isEqualTo("cheese");
  }

  //method
  @Test
  void testCase_createStringWithoutLastCharacters_1C() {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters("cheeseburger", 12);

    //verification
    expect(result).isEqualTo("");
  }

  //method
  @Test
  void testCase_getInParantheses_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.getInParantheses("");

    //verification
    expect(result).isEqualTo("()");
  }

  //method
  @Test
  void testCase_getInParantheses_whenGivenStringIsNotEmpty() {

    //execution
    final var result = GlobalStringTool.getInParantheses("Zebra");

    //verification
    expect(result).isEqualTo("(Zebra)");
  }

  //method
  @Test
  void testCase_getInSingleQuotes_whenTheGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.getInSingleQuotes("");

    //verification
    expect(result).isEqualTo("''");
  }

  //method
  @Test
  void testCase_getInSingleQuotes_whenTheGivenStringIsNotEmpty() {

    //execution
    final var result = GlobalStringTool.getInSingleQuotes("Zebra");

    //verification
    expect(result).isEqualTo("'Zebra'");
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
  void testCase_toPascalCase(final String input, final String expectedResult) {

    //execution
    final var result = GlobalStringTool.toPascalCase(input);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_toPascalCase_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.toPascalCase("");

    //verification
    expect(result).isEqualTo("");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInLowerCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("zebra");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInUpperCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("ZEBRA");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInSentenceCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("Zebra");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInLowerSnakeCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("cursor_icon");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInUpperSnakeCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("CURSOR_ICON");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInSentenceSnakeCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("Cursor_Icon");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @Test
  void testCase_toUpperSnakeCase_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("");

    //verification
    expect(result).isEqualTo("");
  }
}
