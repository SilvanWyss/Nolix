/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.style.model;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.element.mutableelement.AbstractMutableElementWithProperties;
import ch.nolix.system.graphic.image.ImmutableImage;
import ch.nolix.system.style.model.SelectingStyle;

/**
 * @author Silvan Wyss
 */
final class SelectingStyleTest extends StandardTest {
  @Test
  void testCase_selects_whenHasSelectorIdAndTheGivenElementDoesNotHaveId() {
    // setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    // setup testUnit
    final var testUnit = SelectingStyle.EMPTY.withSelectorId("x");

    // setup verification
    expect(mockStylableElement.hasId()).isFalse();

    // execute
    final var result = testUnit.selectsElement(mockStylableElement);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_selects_whenHasSelectorIdAndTheGivenElementHasThatId() {
    // setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();
    mockStylableElement.setId("x");

    // setup
    final var testUnit = SelectingStyle.EMPTY.withSelectorId("x");

    // setup verification
    expect(mockStylableElement.hasId()).isTrue();

    // execute
    final var result = testUnit.selectsElement(mockStylableElement);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_selects_whenHasSelectorIdAndTheGivenElementHasOtherId() {
    // setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();
    mockStylableElement.setId("x");

    // setup
    final var testUnit = SelectingStyle.EMPTY.withSelectorId("y");

    // setup verification
    expect(mockStylableElement.hasId());

    // execute
    final var result = testUnit.selectsElement(mockStylableElement);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfAntoherType() {
    // setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    // setup testUnit
    final var testUnit = SelectingStyle.EMPTY.withSelectorType(ImmutableImage.class);

    // execute
    final var result = testUnit.selectsElement(mockStylableElement);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfTheSameType() {
    // setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    // setup testUnit
    final var testUnit = SelectingStyle.EMPTY.withSelectorType(MockStylableElement.class);

    // execute
    final var result = testUnit.selectsElement(mockStylableElement);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfASubType() {
    // setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    // setup testUnit
    final var testUnit = SelectingStyle.EMPTY.withSelectorType(AbstractMutableElementWithProperties.class);

    // execute
    final var result = testUnit.selectsElement(mockStylableElement);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_selectsChildElements() {
    // setup
    final var testUnit = SelectingStyle.EMPTY;

    // execute
    final var result = testUnit.selectsChildElements();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_skipsChildElements() {
    // setup
    final var testUnit = SelectingStyle.EMPTY;

    // execute
    final var result = testUnit.skipsChildElements();

    // verify
    expect(result).isTrue();
  }
}
