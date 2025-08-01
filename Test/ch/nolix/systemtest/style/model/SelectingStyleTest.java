package ch.nolix.systemtest.style.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.mutableelement.AbstractMutableElement;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.style.model.SelectingStyle;

final class SelectingStyleTest extends StandardTest {

  @Test
  void testCase_selects_whenHasSelectorIdAndTheGivenElementDoesNotHaveId() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyle().withSelectorId("x");

    //setup verification
    expect(mockStylableElement.hasId()).isFalse();

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_selects_whenHasSelectorIdAndTheGivenElementHasThatId() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();
    mockStylableElement.setId("x");

    //setup
    final var testUnit = new SelectingStyle().withSelectorId("x");

    //setup verification
    expect(mockStylableElement.hasId()).isTrue();

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_selects_whenHasSelectorIdAndTheGivenElementHasOtherId() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();
    mockStylableElement.setId("x");

    //setup
    final var testUnit = new SelectingStyle().withSelectorId("y");

    //setup verification
    expect(mockStylableElement.hasId());

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfAntoherType() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyle().withSelectorType(Image.class);

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfTheSameType() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyle().withSelectorType(MockStylableElement.class);

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfASubType() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyle().withSelectorType(AbstractMutableElement.class);

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_selectsChildElements() {

    //setup
    final var testUnit = new SelectingStyle();

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_skipsChildElements() {

    //setup
    final var testUnit = new SelectingStyle();

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expect(result).isTrue();
  }
}
