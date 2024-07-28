//package declaration
package ch.nolix.coretest.commontypetooltest.iteratortooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ContainsObjectOnceMethodTest extends StandardTest {

  //method
  @Test
  void testCase_containsObjectOnce_whenGivenIterableIsNull() {

    //setup
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsObjectOnce(null, "antelope");

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsObjectOnce_whenGivenIterableDoesNotContainGivenObject() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var list = ImmutableList.withElement(antelope, elephant, lion, monkey);
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsObjectOnce(list, rhino);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsObjectOnce_whenGivenIterableContainsGivenObjectOnce() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var list = ImmutableList.withElement(antelope, elephant, lion, monkey);
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsObjectOnce(list, antelope);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsObjectOnce_whenGivenIterableContainsGivenObjectTwice() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var list = ImmutableList.withElement(antelope, antelope, elephant, lion, monkey);
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsObjectOnce(list, antelope);

    //verification
    expectNot(result);
  }
}
