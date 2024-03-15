//package declaration
package ch.nolix.coretest.commontypetooltest;

//own imports
import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class GlobalStringToolTest extends Test {

  //method
  @TestCase
  public void testCase_createStringWithoutLastCharacters_1A() {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters("cheeseburger", 0);

    //verification
    expect(result).isEqualTo("cheeseburger");
  }

  //method
  @TestCase
  public void testCase_createStringWithoutLastCharacters_1B() {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters("cheeseburger", 6);

    //verification
    expect(result).isEqualTo("cheese");
  }

  //method
  @TestCase
  public void testCase_createStringWithoutLastCharacters_1C() {

    //execution
    final var result = GlobalStringTool.createStringWithoutLastCharacters("cheeseburger", 12);

    //verification
    expect(result).isEqualTo("");
  }

  //method
  @TestCase
  public void testCase_getInParantheses_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.getInParantheses("");

    //verification
    expect(result).isEqualTo("()");
  }

  //method
  @TestCase
  public void testCase_getInParantheses_whenGivenStringIsNotEmpty() {

    //execution
    final var result = GlobalStringTool.getInParantheses("Zebra");

    //verification
    expect(result).isEqualTo("(Zebra)");
  }

  //method
  @TestCase
  public void testCase_getInSingleQuotes_whenTheGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.getInSingleQuotes("");

    //verification
    expect(result).isEqualTo("''");
  }

  //method
  @TestCase
  public void testCase_getInSingleQuotes_whenTheGivenStringIsNotEmpty() {

    //execution
    final var result = GlobalStringTool.getInSingleQuotes("Zebra");

    //verification
    expect(result).isEqualTo("'Zebra'");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsOneWordInLowerCase() {

    //execution
    final var result = GlobalStringTool.toPascalCase("zebra");

    //verification
    expect(result).isEqualTo("Zebra");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsOneWordInUpperCase() {

    //execution
    final var result = GlobalStringTool.toPascalCase("ZEBRA");

    //verification
    expect(result).isEqualTo("Zebra");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsOneWordInSentenceCase() {

    //execution
    final var result = GlobalStringTool.toPascalCase("Zebra");

    //verification
    expect(result).isEqualTo("Zebra");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsTwoWordsInLowerSnakeCase() {

    //execution
    final var result = GlobalStringTool.toPascalCase("cursor_icon");

    //verification
    expect(result).isEqualTo("CursorIcon");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsTwoWordsInUpperSnakeCase() {

    //execution
    final var result = GlobalStringTool.toPascalCase("CURSOR_ICON");

    //verification
    expect(result).isEqualTo("CursorIcon");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringContainsTwoWordsInSentenceSnakeCase() {

    //execution
    final var result = GlobalStringTool.toPascalCase("Cursor_Icon");

    //verification
    expect(result).isEqualTo("CursorIcon");
  }

  //method
  @TestCase
  public void testCase_toPascalCase_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.toPascalCase("");

    //verification
    expect(result).isEqualTo("");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInLowerCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("zebra");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInUpperCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("ZEBRA");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsOneWordInSentenceCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("Zebra");

    //verification
    expect(result).isEqualTo("ZEBRA");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInLowerSnakeCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("cursor_icon");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInUpperSnakeCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("CURSOR_ICON");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringContainsTwoWordsInSentenceSnakeCase() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("Cursor_Icon");

    //verification
    expect(result).isEqualTo("CURSOR_ICON");
  }

  //method
  @TestCase
  public void testCase_toUpperSnakeCase_whenGivenStringIsEmpty() {

    //execution
    final var result = GlobalStringTool.toUpperSnakeCase("");

    //verification
    expect(result).isEqualTo("");
  }
}
