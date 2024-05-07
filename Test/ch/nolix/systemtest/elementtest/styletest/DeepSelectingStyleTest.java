//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.element.style.DeepSelectingStyle;

//class
final class DeepSelectingStyleTest extends StandardTest {

  //method
  @Test
  void testCase_selectsChildElements() {

    //setup
    final var testUnit = new DeepSelectingStyle();

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_skipsChildElements() {

    //setup
    final var testUnit = new DeepSelectingStyle();

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expectNot(result);
  }
}
