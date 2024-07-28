//package declaration
package ch.nolix.coretest.commontypetooltest.iteratortooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ContainsAllObjectsMethodTest extends StandardTest {

  //method
  @ParameterizedTest
  @ValueSource(strings = {
  "",
  "antelope", //
  "antelope,elephant", //
  "antelope,elephant,lion", //
  "antelope,elephant,lion,monkey", //
  "antelope,elephant,lion,monkey,rhino", //
  "antelope,elephant,lion,monkey,rhino,zebra"
  })
  void testCase_containsAllObjects_whenIsNull(final String arrayAsString) {

    //setup
    final var elements = arrayAsString.split(",");
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(null, elements);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenContainsAllObject_1A() {

    //setup
    final var list = ImmutableList.createEmpty();
    final var elements = new String[] {};
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenContainsAllObject_1B() {

    //setup
    final var antelope = "antelope";
    final var list = ImmutableList.withElement(antelope);
    final var elements = new String[] { antelope };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenContainsAllObject_1C() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var list = ImmutableList.withElement(antelope, elephant);
    final var elements = new String[] { antelope, elephant };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenContainsAllObject_1D() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var list = ImmutableList.withElement(antelope, elephant, lion);
    final var elements = new String[] { antelope, elephant, lion, };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenContainsAllObject_1E() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var list = ImmutableList.withElement(antelope, elephant, lion, monkey);
    final var elements = new String[] { antelope, elephant, lion, monkey, };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenDoesNotContainsAllObject_1A() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var list = ImmutableList.createEmpty();
    final var elements = new String[] { antelope, elephant, lion, monkey, rhino };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenDoesNotContainsAllObject_1B() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var list = ImmutableList.withElement(antelope);
    final var elements = new String[] { antelope, elephant, lion, monkey, rhino };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenDoesNotContainsAllObject_1C() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var list = ImmutableList.withElement(antelope, elephant);
    final var elements = new String[] { antelope, elephant, lion, monkey, rhino };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenDoesNotContainsAllObject_1D() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var list = ImmutableList.withElement(antelope, elephant, lion);
    final var elements = new String[] { antelope, elephant, lion, monkey, rhino };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAllObjects_whenDoesNotContainsAllObject_1E() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var list = ImmutableList.withElement(antelope, elephant, lion, monkey);
    final var elements = new String[] { antelope, elephant, lion, monkey, rhino };
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAllObjects(list, elements);

    //verification
    expectNot(result);
  }
}
