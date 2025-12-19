package ch.nolix.coreapitest.attributeapi.mandatoryattributeapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class NameHolderTest extends StandardTest {
  @Test
  void testCase_getNameInQuotes() {
    //setup
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.getNameInQuotes();

    //verification
    expect(result).isEqualTo("'my_name'");
  }

  @Test
  void testCase_hasName_whenDoesNotHaveTheGivenName() {
    //setup
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasName("My_name");

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasName_whenHasTheGivenName() {
    //setup
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasName("my_name");

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasSameNameAs_whenDoesNotHaveTheSameName() {
    //setup
    final var nameHolder = MockNameHolder.withName("My_name");
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasSameNameAs(nameHolder);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasSameNameAs_whenHasTheSameName() {
    //setup
    final var nameHolder = MockNameHolder.withName("my_name");
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasSameNameAs(nameHolder);

    //verification
    expect(result).isTrue();
  }
}
