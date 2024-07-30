//package declaration
package ch.nolix.coretest.commontypetooltest.iteratortooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class Get1BasedIndexOfFirstEqualElementMethodTest extends StandardTest {

  //method
  @ParameterizedTest
  @ValueSource(strings = { "antelope", "elephant", "lion", "monkey", "rhino", "zebra" })
  void testCase_get1BasedIndexOfFirstEqualElement_whenGivenIterableIsNull(final Object object) {

    //setup
    final var testUnit = new IterableTool();

    //execution & verification
    expectRunning(() -> testUnit.get1BasedIndexOfFirstEqualElement(null, object))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "antelope, 1", //
  "elephant, 2", //
  "lion, 3", //
  "monkey, 4", //
  "rhino, 5", //
  "zebra, 6", //
  })
  void testCase_get1BasedIndexOfFirstEqualElement_whenGivenIterableContains1EqualElement(
    final Object object,
    final int expectedResult) {

    //setup
    final Iterable<Object> iterable = //
    ImmutableList.withElement("antelope", "elephant", "lion", "monkey", "rhino", "zebra");
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.get1BasedIndexOfFirstEqualElement(iterable, object);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @ParameterizedTest
  @ValueSource(strings = { "antelope", "zebra", })
  void testCase_get1BasedIndexOfFirstEqualElement_whenGivenIterableDoesNotContains1EqualElement(final Object object) {

    //setup 
    final Iterable<Object> iterable = ImmutableList.withElement("elephant", "lion", "monkey", "rhino");
    final var testUnit = new IterableTool();

    //execution & verification
    expectRunning(() -> testUnit.get1BasedIndexOfFirstEqualElement(iterable, object))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }
}
