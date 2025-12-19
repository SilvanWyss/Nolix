package ch.nolix.coretest.web.htmlelementmodel;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.core.web.htmlelementmodel.HtmlElementStringRepresentator;

/**
 * @author Silvan Wyss
 */
final class HtmlElementStringRepresentatorTest extends StandardTest {
  @Test
  void testCase_toString() {
    //setup
    final var htmlElement = HtmlElement.withType("div");
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div />");
  }

  @Test
  void testCase_toString_whenContainsAttributes() {
    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributes(
      "div",
      HtmlAttribute.withNameAndValue("class", "my_class"),
      HtmlAttribute.withNameAndValue("title", "my_title"));
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\" />");
  }

  @Test
  void testCase_toString_whenContainsAttributesAndChildElements() {
    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributesAndChildElements(
      "div",
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\"><h1 /><p /></div>");
  }

  @Test
  void testCase_toString_whenContainsAttributesAndInnerText() {
    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributesAndInnerText(
      "div",
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      "my_content");
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\">my_content</div>");
  }

  @Test
  void testCase_toString_whenContainsChildElements() {
    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndChildElements(
      "div",
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div><h1 /><p /></div>");
  }

  @Test
  void testCase_toString_whenContainsInnerText() {
    //setup
    final var htmlElement = HtmlElement.withTypeAndInnerText("div", "my_content");
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div>my_content</div>");
  }
}
