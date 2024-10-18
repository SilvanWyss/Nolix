package ch.nolix.systemtest.elementtest.multistateconfigurationtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.system.graphic.color.Color;

final class CascadingPropertyInMultiStateConfigurationTest extends StandardTest {

  //static enum
  private enum CustomState {
    A,
    B,
    C,
    D
  }

  private static final class CustomMultiStateConfiguration
  extends MultiStateConfiguration<CustomMultiStateConfiguration, CustomState> {

    static final CustomState BASE_STATE = CustomState.A;

    final CascadingProperty<CustomState, Color> testUnit = new CascadingProperty<>(
      "Color",
      CustomState.class,
      Color::fromSpecification,
      Color::getSpecification,
      Color.WHITE);

    CustomMultiStateConfiguration() {

      super(BASE_STATE);

      reset();
    }

    void addChild(final CustomMultiStateConfiguration child) {
      internalAddChild(child);
    }
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenState() {

    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(Color.WHITE);
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndDefinesValueForBaseState() {

    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.A, Color.RED);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(Color.RED);
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndGetsValueFromParent() {

    //setup
    final var parentMultiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.testUnit.setValueForState(CustomState.C, Color.RED);
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.addChild(multiStateConfiguration);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(Color.RED);
  }

  @Test
  void testCase_getValueOfState_whenDefinesValueForGivenState() {

    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.C, Color.RED);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(Color.RED);
  }
}
