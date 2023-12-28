//package declaration
package ch.nolix.coretest.webtest.htmltest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;

//class
public final class HtmlElementTest extends Test {

  //method
  @TestCase
  public void testCase_toString() {

    //setup
    final var testUnit = HtmlElement.withType("div");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<div />");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsAttributes() {

    //setup
    final var testUnit = //
    HtmlElement.withTypeAndAttribute(
      "div",
      HtmlAttribute.withNameAndValue("class", "my_class"),
      HtmlAttribute.withNameAndValue("title", "my_title"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\" />");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsAttributesAndChildElements() {

    //setup
    final var testUnit = //
    HtmlElement.withTypeAndAttributesAndChildElement(
      "div",
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\"><h1 /><p /></div>");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsAttributesAndInnerText() {

    //setup
    final var testUnit = //
    HtmlElement.withTypeAndAttributesAndInnerText(
      "div",
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      "my_content");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\">my_content</div>");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsChildElements() {

    //setup
    final var testUnit = //
    HtmlElement.withTypeAndChildElement(
      "div",
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<div><h1 /><p /></div>");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsInnerText() {

    //setup
    final var testUnit = HtmlElement.withTypeAndInnerText("div", "my_content");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<div>my_content</div>");
  }

  //method
  @TestCase
  public void testCase_withAttribute_whenContainsAttributesAndHasNonEmptyInnerText() {

    //setup
    final var attribute1 = HtmlAttribute.withNameAndValue("n1", "v1");
    final var attribute2 = HtmlAttribute.withNameAndValue("n2", "v2");
    final var attribute3 = HtmlAttribute.withNameAndValue("n3", "v3");
    final var attribute4 = HtmlAttribute.withNameAndValue("n4", "v4");
    final var testUnit = HtmlElement.withTypeAndAttributesAndInnerText(
      HtmlElementTypeCatalogue.DIV,
      ImmutableList.withElement(attribute1, attribute2),
      "my inner text");

    //execution
    final var result = testUnit.withAttribute(attribute3, attribute4);

    //verification
    expect(testUnit)
      .isEqualTo(
        HtmlElement.withTypeAndAttributesAndInnerText(
          HtmlElementTypeCatalogue.DIV,
          ImmutableList.withElement(attribute1, attribute2),
          "my inner text"));
    expect(result).isEqualTo(
      HtmlElement.withTypeAndAttributesAndInnerText(
        HtmlElementTypeCatalogue.DIV,
        ImmutableList.withElement(attribute1, attribute2, attribute3, attribute4),
        "my inner text"));
  }
}
