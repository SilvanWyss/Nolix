package ch.nolix.systemtest.elementtest.styletest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.style.AttachingAttribute;
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.SelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.WebGui;

final class StyleTest extends StandardTest {

  @Test
  void testCase_fromSpecification_whenIsEmpty() {

    //setup
    final var specification = Node.withHeader("Style");

    //execution
    final var result = Style.fromSpecification(specification);

    //verification
    expect(result.getAttachingAttributes()).isEmpty();
    expect(result.getSubStyles()).isEmpty();
  }

  @Test
  void testCase_fromSpecification_whenContainsAttachingAttributesAndSubStyles() {

    //setup
    final var specification = Node
      .fromString(
        "Style("
        + "AttachingAttribute(test_attaching_attribute_1),"
        + "AttachingAttribute(test_attaching_attribute_2),"
        + "SelectingStyle,"
        + "DeepSelectingStyle"
        + ")");

    //execution
    final var result = Style.fromSpecification(specification);

    //verification
    expect(result.getAttachingAttributes().toStrings()).containsExactlyEqualing(
      "AttachingAttribute(test_attaching_attribute_1)",
      "AttachingAttribute(test_attaching_attribute_2)");
    expect(result.getSubStyles()).containsExactlyEqualing(
      new SelectingStyle(),
      new DeepSelectingStyle());
  }

  @Test
  void testCase_styleElement() {

    //setup part 1: create WebGui
    final var webGui = new WebGui();

    //setup part 2: create testUnit
    final var testUnit = new Style(
      ImmutableList.withElement(
        AttachingAttribute.forValue("Title(my_title)"),
        AttachingAttribute.forValue("Background(Color(Blue))")),
      ImmutableList.createEmpty());

    //execution
    testUnit.applyToElement(webGui);

    //verification
    expect(webGui.getTitle()).isEqualTo("my_title");
    expect(webGui.getBackgroundColor()).isEqualTo(Color.BLUE);
  }

  @Test
  void testCase_withAttachingAttribute_whenIsEmpty() {

    //setup
    final var testUnit = new Style();

    //execution
    final var result = testUnit.withAttachingAttribute("p1(v1)", "p2(v2)");

    //verification
    expect(result.getAttachingAttributes()
      .toStrings())
      .containsExactlyEqualing("AttachingAttribute(p1(v1))", "AttachingAttribute(p2(v2))");
    expect(result.getSubStyles()).isEmpty();
  }

  @Test
  void testCase_withSubStyle_whenIsEmpty() {

    //setup
    final var subStyle1 = new SelectingStyle();
    final var subStyle2 = new SelectingStyle();
    final var testUnit = new Style();

    //execution
    final var result = testUnit.withSubStyle(subStyle1, subStyle2);

    //verification
    expect(result.getAttachingAttributes()).isEmpty();
    final var subStyles = result.getSubStyles();
    expect(subStyles).hasElementCount(2);
    expect(subStyles.getStoredAt1BasedIndex(1)).is(subStyle1);
    expect(subStyles.getStoredAt1BasedIndex(2)).is(subStyle2);
  }

  @Test
  void testCase_withAttachingAttribute_whenContainsAny() {

    //setup
    final var testUnit = new Style().withAttachingAttribute("p1(v1)", "p2(v2)");

    //execution
    final var result = testUnit.withAttachingAttribute("p3(v3)", "p4(v4)");

    //verification
    expect(result.getAttachingAttributes().toStrings())
      .containsExactlyEqualing(
        "AttachingAttribute(p1(v1))",
        "AttachingAttribute(p2(v2))",
        "AttachingAttribute(p3(v3))",
        "AttachingAttribute(p4(v4))");
    expect(result.getSubStyles()).isEmpty();
  }

  @Test
  void testCase_withSubStyle_whenContainsAny() {

    //setup
    final var subStyle1 = new SelectingStyle();
    final var subStyle2 = new SelectingStyle();
    final var subStyle3 = new SelectingStyle();
    final var subStyle4 = new SelectingStyle();
    final var testUnit = new Style().withSubStyle(subStyle1, subStyle2);

    //execution
    final var result = testUnit.withSubStyle(subStyle3, subStyle4);

    //verification
    expect(result.getAttachingAttributes()).isEmpty();
    final var subStyles = result.getSubStyles();
    expect(subStyles).hasElementCount(4);
    expect(subStyles.getStoredAt1BasedIndex(1)).is(subStyle1);
    expect(subStyles.getStoredAt1BasedIndex(2)).is(subStyle2);
    expect(subStyles.getStoredAt1BasedIndex(3)).is(subStyle3);
    expect(subStyles.getStoredAt1BasedIndex(4)).is(subStyle4);
  }
}
