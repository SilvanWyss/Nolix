//package declaration
package ch.nolix.systemtest.elementtest.multistateconfigurationtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.system.graphic.color.Color;

//class
public final class CascadingPropertyInMultiStateConfigurationTest extends Test {

  // static enum
  private enum CustomState {
    A,
    B,
    C,
    D
  }

  // static class
  private static final class CustomMultiStateConfiguration
      extends MultiStateConfiguration<CustomMultiStateConfiguration, CustomState> {

    public static final CustomState BASE_STATE = CustomState.A;

    // attribute
    public final CascadingProperty<CustomState, Color> testUnit = new CascadingProperty<>(
        "Color",
        CustomState.class,
        Color::fromSpecification,
        Color::getSpecification,
        Color.WHITE);

    // constructor
    public CustomMultiStateConfiguration() {

      super(BASE_STATE);

      reset();
    }

    // method
    public void addChild(final CustomMultiStateConfiguration child) {
      internalAddChild(child);
    }
  }

  // method
  @TestCase
  public void testCase_getValueOfState_whenDoesNotDefineValueForGivenState() {

    // setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    // execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    // verification
    expect(result).is(Color.WHITE);
  }

  // method
  @TestCase
  public void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndDefinesValueForBaseState() {

    // setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.A, Color.RED);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    // execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    // verification
    expect(result).is(Color.RED);
  }

  // method
  @TestCase
  public void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndGetsValueFromParent() {

    // setup
    final var parentMultiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.testUnit.setValueForState(CustomState.C, Color.RED);
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.addChild(multiStateConfiguration);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    // execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    // verification
    expect(result).is(Color.RED);
  }

  // method
  @TestCase
  public void testCase_getValueOfState_whenDefinesValueForGivenState() {

    // setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.C, Color.RED);

    // execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    // verification
    expect(result).is(Color.RED);
  }
}
