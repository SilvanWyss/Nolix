package ch.nolix.coretest.commontypetool.stringtool;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.commontypetool.stringtool.StringExaminer;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 * @version 2025-03-01
 */
final class StringExaminerTest extends StandardTest {
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
    final var testUnit = new StringExaminer();

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
    final var testUnit = new StringExaminer();

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
    final var testUnit = new StringExaminer();

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
    final var testUnit = new StringExaminer();

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
    final var testUnit = new StringExaminer();

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
    final var testUnit = new StringExaminer();

    //execution
    final var result = testUnit.startsWithIgnoringCase(string, prefix);

    //verification
    expect(result).isFalse();
  }
}
