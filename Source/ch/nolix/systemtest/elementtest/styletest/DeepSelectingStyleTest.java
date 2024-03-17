//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.element.stylebuilder.DeepSelectingStyleBuilder;

//class
public final class DeepSelectingStyleTest extends StandardTest {

  //method
  @TestCase
  public void testCase_selectsChildElements() {

    //setup
    final var testUnit = new DeepSelectingStyleBuilder().build();

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_skipsChildElements() {

    //setup
    final var testUnit = new DeepSelectingStyleBuilder().build();

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expectNot(result);
  }
}
