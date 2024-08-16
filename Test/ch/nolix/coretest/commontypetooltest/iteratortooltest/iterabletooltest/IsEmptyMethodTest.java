//package declaration
package ch.nolix.coretest.commontypetooltest.iteratortooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.independent.container.List;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class IsEmptyMethodTest extends StandardTest {

  //method
  @Test
  void testCase_isEmpty_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isEmpty_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isEmpty_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isEmpty_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expectNot(result);
  }
}
