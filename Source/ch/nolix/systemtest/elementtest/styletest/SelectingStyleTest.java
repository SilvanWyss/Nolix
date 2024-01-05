//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.stylebuilder.SelectingStyleBuilder;
import ch.nolix.system.graphic.image.Image;

//class
public final class SelectingStyleTest extends Test {

  //method
  @TestCase
  public void testCase_selects_whenHasSelectorIdAndTheGivenElementDoesNotHaveAnId() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyleBuilder().setSelectorId("x").build();

    //setup verification
    expectNot(mockStylableElement.hasId());

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_selects_whenHasSelectorIdAndTheGivenElementHasThatId() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();
    mockStylableElement.setId("x");

    //setup
    final var testUnit = new SelectingStyleBuilder().setSelectorId("x").build();

    //setup verification
    expect(mockStylableElement.hasId());

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_selects_whenHasSelectorIdAndTheGivenElementHasOtherId() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();
    mockStylableElement.setId("x");

    //setup
    final var testUnit = new SelectingStyleBuilder().setSelectorId("y").build();

    //setup verification
    expect(mockStylableElement.hasId());

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfAntoherType() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyleBuilder().setSelectorType(Image.class).build();

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfTheSameType() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyleBuilder().setSelectorType(MockStylableElement.class).build();

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_selects_whenHasSelectorTypeAndTheGivenElementIsOfASubType() {

    //setup mockStylableElement
    final var mockStylableElement = new MockStylableElement();

    //setup testUnit
    final var testUnit = new SelectingStyleBuilder().setSelectorType(MutableElement.class).build();

    //execution
    final var result = testUnit.selectsElement(mockStylableElement);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_selectsChildElements() {

    //setup
    final var testUnit = new SelectingStyleBuilder().build();

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_skipsChildElements() {

    //setup
    final var testUnit = new SelectingStyleBuilder().build();

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expect(result);
  }
}
