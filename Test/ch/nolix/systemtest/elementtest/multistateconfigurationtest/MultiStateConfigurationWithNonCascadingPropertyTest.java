//package declaration
package ch.nolix.systemtest.elementtest.multistateconfigurationtest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;

//class
final class MultiStateConfigurationWithNonCascadingPropertyTest extends StandardTest {

  //static enum
  private enum CustomState {
    A,
    B,
    C,
    D
  }

  //constant
  private static final class CustomFormatElement extends MultiStateConfiguration<CustomFormatElement, CustomState> {

    //attribute
    final NonCascadingProperty<CustomState, Color> color = new NonCascadingProperty<>(
      "Color",
      CustomState.class,
      Color::fromSpecification,
      Color::getSpecification);

    //constructor
    CustomFormatElement() {

      super(CustomState.A);

      reset();
    }
  }

  //method
  @Test
  void testCase_addOrChangeAttribute_1A() {

    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("AColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.A)).isEqualTo(Color.RED);
  }

  //method
  @Test
  void testCase_addOrChangeAttribute_1B() {

    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("BColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.B)).isEqualTo(Color.RED);
  }

  //method
  @Test
  void testCase_addOrChangeAttribute_1C() {

    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("CColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.C)).isEqualTo(Color.RED);
  }

  //method
  @Test
  void testCase_addOrChangeAttribute_1D() {

    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("DColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.D)).isEqualTo(Color.RED);
  }

  //method
  @Test
  void testCase_constructor() {

    //execution
    final var testUnit = new CustomFormatElement();

    //verification
    expect(testUnit.color.getName()).isEqualTo("Color");
  }

  //method
  @Test
  void testCase_getSpecification() {

    //setup
    final var testUnit = new CustomFormatElement();
    testUnit.color.setValueForState(CustomState.A, Color.BLACK);
    testUnit.color.setValueForState(CustomState.B, Color.BLUE);
    testUnit.color.setValueForState(CustomState.C, Color.RED);
    testUnit.color.setValueForState(CustomState.D, Color.GREEN);

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation(
      "CustomFormatElement(AColor(0x000000),BColor(0x0000FF),CColor(0xFF0000),DColor(0x008000))");
  }
}