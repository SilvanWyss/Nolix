/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.style.model;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.style.model.SelectingStyle;
import ch.nolix.system.style.model.Style;
import ch.nolix.system.webgui.main.WebGui;

/**
 * @author Silvan Wyss
 */
final class StyleTest extends StandardTest {
  @Test
  void testCase_fromSpecification_whenIsEmpty() {
    // setup
    final var specification = ImmutableNode.withHeader("Style");

    // execute
    final var result = Style.fromSpecification(specification);

    // verify
    expect(result.getAttachingAttributes()).isEmpty();
    expect(result.getSubStyles()).isEmpty();
  }

  @Test
  void testCase_fromSpecification_whenContainsAttachingAttributesAndSubStyles() {
    // setup
    final var specification = ImmutableNode
      .fromString(
        "Style("
        + "AttachingAttribute(test_attaching_attribute_1),"
        + "AttachingAttribute(test_attaching_attribute_2),"
        + "SelectingStyle,"
        + "DeepSelectingStyle"
        + ")");

    // execute
    final var result = Style.fromSpecification(specification);

    // verify
    expect(result.getAttachingAttributes().toStrings()).containsExactlyEqualing(
      "test_attaching_attribute_1",
      "test_attaching_attribute_2");
    expect(result.getSubStyles()).containsExactlyEqualing(
      SelectingStyle.EMPTY,
      DeepSelectingStyle.EMPTY);
  }

  @Test
  void testCase_styleElement() {
    // setup part 1: create WebGui
    final var webGui = new WebGui();

    // setup part 2: create testUnit
    final var testUnit = //
    Style.withAttachingAttributesAndSubStyles(
      ImmutableList.withElements("Title(my_title)", "Background(Color(Blue))"),
      ImmutableList.createEmpty());

    // execute
    testUnit.applyToElement(webGui);

    // verify
    expect(webGui.getTitle()).isEqualTo("my_title");
    expect(webGui.getBackgroundColor()).isEqualTo(X11ColorCatalog.BLUE);
  }

  @Test
  void testCase_withAttachingAttribute_whenIsEmpty() {
    // setup
    final var testUnit = Style.EMPTY;

    // execute
    final var result = testUnit.withAttachingAttributes("p1(v1)", "p2(v2)");

    // verify
    expect(result.getAttachingAttributes()
      .toStrings())
      .containsExactlyEqualing("p1(v1)", "p2(v2)");
    expect(result.getSubStyles()).isEmpty();
  }

  @Test
  void testCase_withSubStyle_whenIsEmpty() {
    // setup
    final var subStyle1 = SelectingStyle.EMPTY;
    final var subStyle2 = SelectingStyle.EMPTY;
    final var testUnit = Style.EMPTY;

    // execute
    final var result = testUnit.withAdditionalSubStyles(subStyle1, subStyle2);

    // verify
    expect(result.getAttachingAttributes()).isEmpty();
    final var subStyles = result.getSubStyles();
    expect(subStyles).hasElementCount(2);
    expect(subStyles.getStoredAtOneBasedIndex(1)).is(subStyle1);
    expect(subStyles.getStoredAtOneBasedIndex(2)).is(subStyle2);
  }

  @Test
  void testCase_withAttachingAttribute_whenContainsAny() {
    // setup
    final var testUnit = Style.EMPTY.withAttachingAttributes("p1(v1)", "p2(v2)");

    // execute
    final var result = testUnit.withAttachingAttributes("p3(v3)", "p4(v4)");

    // verify
    expect(result.getAttachingAttributes().toStrings())
      .containsExactlyEqualing(
        "p1(v1)",
        "p2(v2)",
        "p3(v3)",
        "p4(v4)");
    expect(result.getSubStyles()).isEmpty();
  }

  @Test
  void testCase_withSubStyle_whenContainsAny() {
    // setup
    final var subStyle1 = SelectingStyle.EMPTY;
    final var subStyle2 = SelectingStyle.EMPTY;
    final var subStyle3 = SelectingStyle.EMPTY;
    final var subStyle4 = SelectingStyle.EMPTY;
    final var testUnit = Style.EMPTY.withAdditionalSubStyles(subStyle1, subStyle2);

    // execute
    final var result = testUnit.withAdditionalSubStyles(subStyle3, subStyle4);

    // verify
    expect(result.getAttachingAttributes()).isEmpty();
    final var subStyles = result.getSubStyles();
    expect(subStyles).hasElementCount(4);
    expect(subStyles.getStoredAtOneBasedIndex(1)).is(subStyle1);
    expect(subStyles.getStoredAtOneBasedIndex(2)).is(subStyle2);
    expect(subStyles.getStoredAtOneBasedIndex(3)).is(subStyle3);
    expect(subStyles.getStoredAtOneBasedIndex(4)).is(subStyle4);
  }
}
