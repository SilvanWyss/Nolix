/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.multistateconfiguration;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.multistateconfiguration.AbstractMultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;

/**
 * @author Silvan Wyss
 */
final class MultiStateConfigurationWithNonCascadingPropertyTest extends StandardTest {
  private enum CustomState {
    A,
    B,
    C,
    D
  }

  private static final class CustomFormatElement
  extends AbstractMultiStateConfiguration<CustomFormatElement, CustomState> {
    final NonCascadingProperty<CustomState, Color> color = new NonCascadingProperty<>(
      "Color",
      CustomState.class,
      Color::fromSpecification,
      Color::getSpecification,
      X11ColorCatalog.WHITE);

    CustomFormatElement() {
      super(CustomState.A);

      reset();
    }
  }

  @Test
  void testCase_addOrChangeAttribute_1A() {
    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("AColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.A)).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_addOrChangeAttribute_1B() {
    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("BColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.B)).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_addOrChangeAttribute_1C() {
    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("CColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.C)).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_addOrChangeAttribute_1D() {
    //setup
    final var testUnit = new CustomFormatElement();

    //execution
    testUnit.addOrChangeAttribute(Node.fromString("DColor(0xFF0000)"));

    //verification
    expect(testUnit.color.getValueOfState(CustomState.D)).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_constructor() {
    //execution
    final var testUnit = new CustomFormatElement();

    //verification
    expect(testUnit.color.getName()).isEqualTo("Color");
  }

  @Test
  void testCase_getSpecification() {
    //setup
    final var testUnit = new CustomFormatElement();
    testUnit.color.setValueForState(CustomState.A, X11ColorCatalog.BLACK);
    testUnit.color.setValueForState(CustomState.B, X11ColorCatalog.BLUE);
    testUnit.color.setValueForState(CustomState.C, X11ColorCatalog.RED);
    testUnit.color.setValueForState(CustomState.D, X11ColorCatalog.GREEN);

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation(
      "CustomFormatElement(AColor(0x000000),BColor(0x0000FF),CColor(0xFF0000),DColor(0x008000))");
  }
}
