/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.web.htmlmodel;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.web.htmlmodel.HtmlAttribute;
import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.base.web.htmlmodel.HtmlElementStringRepresentator;

/**
 * @author Silvan Wyss
 */
final class HtmlElementStringRepresentatorTest extends StandardTest {
  @Test
  void testCase_toString() {
    // setup
    final var htmlElement = HtmlElement.withType("div");

   // execute
    final var result = HtmlElementStringRepresentator.toString(htmlElement);

   // verify
    expect(result).isEqualTo("<div />");
  }

  @Test
  void testCase_toString_whenContainsAttributes() {
    // setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributes(
      "div",
      HtmlAttribute.withNameAndValue("class", "my_class"),
      HtmlAttribute.withNameAndValue("title", "my_title"));

   // execute
    final var result = HtmlElementStringRepresentator.toString(htmlElement);

   // verify
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\" />");
  }

  @Test
  void testCase_toString_whenContainsAttributesAndChildElements() {
    // setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributesAndChildElements(
      "div",
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));

   // execute
    final var result = HtmlElementStringRepresentator.toString(htmlElement);

   // verify
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\"><h1 /><p /></div>");
  }

  @Test
  void testCase_toString_whenContainsAttributesAndInnerText() {
    // setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributesAndInnerText(
      "div",
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      "my_content");

   // execute
    final var result = HtmlElementStringRepresentator.toString(htmlElement);

   // verify
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\">my_content</div>");
  }

  @Test
  void testCase_toString_whenContainsChildElements() {
    // setup
    final var htmlElement = //
    HtmlElement.withTypeAndChildElements(
      "div",
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));

   // execute
    final var result = HtmlElementStringRepresentator.toString(htmlElement);

   // verify
    expect(result).isEqualTo("<div><h1 /><p /></div>");
  }

  @Test
  void testCase_toString_whenContainsInnerText() {
    // setup
    final var htmlElement = HtmlElement.withTypeAndInnerText("div", "my_content");

   // execute
    final var result = HtmlElementStringRepresentator.toString(htmlElement);

   // verify
    expect(result).isEqualTo("<div>my_content</div>");
  }
}
