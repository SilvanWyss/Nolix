package ch.nolix.coretest.webtest.htmltest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;

final class HtmlElementTest extends StandardTest {

  @Test
  void testCase_withAttribute_whenContainsAttributesAndHasNonEmptyInnerText() {

    //setup
    final var attribute1 = HtmlAttribute.withNameAndValue("n1", "v1");
    final var attribute2 = HtmlAttribute.withNameAndValue("n2", "v2");
    final var attribute3 = HtmlAttribute.withNameAndValue("n3", "v3");
    final var attribute4 = HtmlAttribute.withNameAndValue("n4", "v4");
    final var testUnit = HtmlElement.withTypeAndAttributesAndInnerText(
      HtmlElementTypeCatalogue.DIV,
      ImmutableList.withElement(attribute1, attribute2),
      "my inner text");

    //setup verification
    expect(testUnit)
      .isEqualTo(
        HtmlElement.withTypeAndAttributesAndInnerText(
          HtmlElementTypeCatalogue.DIV,
          ImmutableList.withElement(attribute1, attribute2),
          "my inner text"));

    //execution
    final var result = testUnit.withAttribute(attribute3, attribute4);

    //verification
    expect(result).isEqualTo(
      HtmlElement.withTypeAndAttributesAndInnerText(
        HtmlElementTypeCatalogue.DIV,
        ImmutableList.withElement(attribute1, attribute2, attribute3, attribute4),
        "my inner text"));
  }
}
