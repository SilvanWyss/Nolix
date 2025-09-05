package ch.nolix.systemtest.element.multistateconfiguration;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.multistateconfiguration.AbstractMultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;

final class CascadingPropertyInMultiStateConfigurationTest extends StandardTest {
  private enum CustomState {
    A,
    B,
    C,
    D
  }

  private static final class CustomMultiStateConfiguration
  extends AbstractMultiStateConfiguration<CustomMultiStateConfiguration, CustomState> {
    static final CustomState BASE_STATE = CustomState.A;

    final CascadingProperty<CustomState, Color> testUnit = new CascadingProperty<>(
      "Color",
      CustomState.class,
      Color::fromSpecification,
      Color::getSpecification,
      X11ColorCatalog.WHITE);

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
    expect(result).is(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndDefinesValueForBaseState() {
    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.A, X11ColorCatalog.RED);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(X11ColorCatalog.RED);
  }

  @Test
  void testCase_getValueOfState_whenDoesNotDefineValueForGivenStateAndGetsValueFromParent() {
    //setup
    final var parentMultiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.testUnit.setValueForState(CustomState.C, X11ColorCatalog.RED);
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    parentMultiStateConfiguration.addChild(multiStateConfiguration);
    multiStateConfiguration.testUnit.setUndefinedForState(CustomState.C);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(X11ColorCatalog.RED);
  }

  @Test
  void testCase_getValueOfState_whenDefinesValueForGivenState() {
    //setup
    final var multiStateConfiguration = new CustomMultiStateConfiguration();
    multiStateConfiguration.testUnit.setValueForState(CustomState.C, X11ColorCatalog.RED);

    //execution
    final var result = multiStateConfiguration.testUnit.getValueWhenHasState(CustomState.C);

    //verification
    expect(result).is(X11ColorCatalog.RED);
  }
}
