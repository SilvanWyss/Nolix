//package declaration
package ch.nolix.coretest.webtest.htmltest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.core.web.html.HtmlElementStringRepresentator;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class HtmlElementStringRepresentatorTest extends StandardTest {

  //method
  @TestCase
  public void testCase_toString() {

    //setup
    final var htmlElement = HtmlElement.withType("div");
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div />");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsAttributes() {

    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttribute(
      "div",
      HtmlAttribute.withNameAndValue("class", "my_class"),
      HtmlAttribute.withNameAndValue("title", "my_title"));
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\" />");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsAttributesAndChildElements() {

    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributesAndChildElement(
      "div",
      ImmutableList.withElement(
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

  //method
  @TestCase
  public void testCase_toString_whenContainsAttributesAndInnerText() {

    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndAttributesAndInnerText(
      "div",
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue("class", "my_class"),
        HtmlAttribute.withNameAndValue("title", "my_title")),
      "my_content");
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div class=\"my_class\" title=\"my_title\">my_content</div>");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsChildElements() {

    //setup
    final var htmlElement = //
    HtmlElement.withTypeAndChildElement(
      "div",
      HtmlElement.withType("h1"),
      HtmlElement.withType("p"));
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div><h1 /><p /></div>");
  }

  //method
  @TestCase
  public void testCase_toString_whenContainsInnerText() {

    //setup
    final var htmlElement = HtmlElement.withTypeAndInnerText("div", "my_content");
    final var testUnit = new HtmlElementStringRepresentator();

    //execution
    final var result = testUnit.toString(htmlElement);

    //verification
    expect(result).isEqualTo("<div>my_content</div>");
  }
}
