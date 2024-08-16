//package declaration
package ch.nolix.coretest.commontypetooltest.iteratortooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.independent.container.List;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GetCountMethodTest extends StandardTest {

  //method
  @Test
  void testCase_getCount_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.getCount(iterable);

    //verification
    expect(result).isEqualTo(0);
  }

  //method
  @Test
  void testCase_getCount_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.getCount(iterable);

    //verification
    expect(result).isEqualTo(0);
  }

  //method
  @Test
  void testCase_getCount_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.getCount(iterable);

    //verification
    expect(result).isEqualTo(1);
  }

  //method
  @Test
  void testCase_getCount_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.getCount(iterable);

    //verification
    expect(result).isEqualTo(2);
  }
}
