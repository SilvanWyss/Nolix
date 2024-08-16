//package declaration
package ch.nolix.coretest.commontypetooltest.iteratortooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.independent.container.List;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ContainsAnyMethodTest extends StandardTest {

  //method
  @Test
  void testCase_containsAny_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAny_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAny_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAny_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result);
  }
}
