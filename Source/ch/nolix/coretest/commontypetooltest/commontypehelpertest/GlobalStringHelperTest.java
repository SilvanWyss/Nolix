//package declaration
package ch.nolix.coretest.commontypetooltest.commontypehelpertest;

import ch.nolix.core.commontypetool.commontypehelper.GlobalStringHelper;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class GlobalStringHelperTest extends Test {

  //method
  @TestCase
  public void testCase_createStringWithoutLastCharacters_1A() {

    //execution
    final var result = GlobalStringHelper.createStringWithoutLastCharacters("cheeseburger", 0);

    //verification
    expect(result).isEqualTo("cheeseburger");
  }

  //method
  @TestCase
  public void testCase_createStringWithoutLastCharacters_1B() {

    //execution
    final var result = GlobalStringHelper.createStringWithoutLastCharacters("cheeseburger", 6);

    //verification
    expect(result).isEqualTo("cheese");
  }

  //method
  @TestCase
  public void testCase_createStringWithoutLastCharacters_1C() {

    //execution
    final var result = GlobalStringHelper.createStringWithoutLastCharacters("cheeseburger", 12);

    //verification
    expect(result).isEqualTo("");
  }

  //method
  @TestCase
  public void testCase_getInParantheses_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringHelper.getInParantheses("");

    //verification
    expect(result).isEqualTo("()");
  }

  //method
  @TestCase
  public void testCase_getInParantheses_whenGivenStringIsNotEmpty() {

    //execution
    final var result = GlobalStringHelper.getInParantheses("Zebra");

    //verification
    expect(result).isEqualTo("(Zebra)");
  }

  //method
  @TestCase
  public void testCase_getInQuotes_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringHelper.getInQuotes("");

    //verification
    expect(result).isEqualTo("''");
  }

  //method
  @TestCase
  public void testCase_getInQuotes_whenGivenStringIsNotEmpty() {

    //execution
    final var result = GlobalStringHelper.getInQuotes("Zebra");

    //verification
    expect(result).isEqualTo("'Zebra'");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsOneWordInLowerCase() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("zebra");

    //verification
    expect(result).isEqualTo("Zebra");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsOneWordInUpperCase() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("ZEBRA");

    //verification
    expect(result).isEqualTo("Zebra");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsOneWordInSentenceCase() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("Zebra");

    //verification
    expect(result).isEqualTo("Zebra");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsTwoWordsInLowerSnakeCase() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("cursor_icon");

    //verification
    expect(result).isEqualTo("CursorIcon");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsTwoWordsInUpperSnakeCase() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("CURSOR_ICON");

    //verification
    expect(result).isEqualTo("CursorIcon");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsTwoWordsInSentenceSnakeCase() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("Cursor_Icon");

    //verification
    expect(result).isEqualTo("CursorIcon");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringHelper.toPascalCase("");

    //verification
    expect(result).isEqualTo("");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInLowerCase() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("zebra");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInUpperCase() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("ZEBRA");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInSentenceCase() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("Zebra");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInLowerSnakeCase() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("cursor_icon");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInUpperSnakeCase() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("CURSOR_ICON");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInSentenceSnakeCase() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("Cursor_Icon");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringHelper.toUpperSnakeCase("");

    //verification
    expect(result).isEqualTo("");
  }
}
