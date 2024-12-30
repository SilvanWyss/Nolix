package ch.nolix.systemtest.elementtest.multistateconfigurationtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalogue;

final class CascadingPropertyInMultiStateConfigurationTest extends StandardTest {

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
      X11ColorCatalogue.WHITE);

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
    expect(result).is(X11ColorCatalogue.WHITE);
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndDefinesValueForBaseState() {

    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.A, X11ColorCatalogue.RED);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(X11ColorCatalogue.RED);
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndGetsValueFromParent() {

    //setup
    final var parentMultiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.testUnit.setValueForState(CustomState.C, X11ColorCatalogue.RED);
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.addChild(multiStateConfiguration);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(X11ColorCatalogue.RED);
  }

  @Test
  void testCase_getValueOfState_whenDefinesValueForGivenState() {

    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.C, X11ColorCatalogue.RED);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(X11ColorCatalogue.RED);
  }
}
