package ch.nolix.core.web.html;

import ch.nolix.coreapi.web.html.IHtmlElement;

public final class HtmlElementStringRepresentator {

  public String toString(final IHtmlElement htmlElement) {

    if (!htmlElement.containsChildElements()) {
      return toStringWhenDoesNotContainChildElements(htmlElement);
    }

    return toStringWhenContainsChildElements(htmlElement);
  }

  private String toStringWhenDoesNotContainChildElements(final IHtmlElement htmlElement) {

    if (htmlElement.getInnerText().isEmpty()) {
      return toStringWhenDoesNotContainChildElementsAndHasEmptyInnerText(htmlElement);
    }

    return toStringWhenDoesNotContainChildElementsAndHasNonEmptyInnerText(htmlElement);
  }

  private String toStringWhenDoesNotContainChildElementsAndHasEmptyInnerText(final IHtmlElement htmlElement) {

    if (!htmlElement.containsAttributes()) {
      return ("<" + htmlElement.getType() + " />");
    }

    return ("<" + htmlElement.getType() + " " + getAttributesAsString(htmlElement) + " />");
  }

  private String toStringWhenDoesNotContainChildElementsAndHasNonEmptyInnerText(final IHtmlElement htmlElement) {

    if (!htmlElement.containsAttributes()) {
      return ("<" + htmlElement.getType() + ">" + htmlElement.getInnerText() + "</" + htmlElement.getType() + ">");
    }

    return "<"
    + htmlElement.getType()
    + " "
    + getAttributesAsString(htmlElement)
    + ">"
    + htmlElement.getInnerText()
    + "</"
    + htmlElement.getType()
    + ">";
  }

  private String toStringWhenContainsChildElements(final IHtmlElement htmlElement) {

    if (htmlElement.getInnerText().isEmpty()) {
      return toStringWhenContainsChildElementsAndHasEmptyInnerText(htmlElement);
    }

    return toStringWhenContainsChildElementsAndHasNonEmptyInnerText(htmlElement);
  }

  private String toStringWhenContainsChildElementsAndHasEmptyInnerText(final IHtmlElement htmlElement) {

    if (!htmlElement.containsAttributes()) {
      return "<"
      + htmlElement.getType()
      + ">"
      + getChildElementsAsString(htmlElement)
      + "</"
      + htmlElement.getType()
      + ">";
    }

    return "<"
    + htmlElement.getType()
    + " "
    + getAttributesAsString(htmlElement)
    + ">"
    + getChildElementsAsString(htmlElement)
    + "</"
    + htmlElement.getType()
    + ">";
  }

  private String toStringWhenContainsChildElementsAndHasNonEmptyInnerText(final IHtmlElement htmlElement) {

    if (!htmlElement.containsAttributes()) {
      return "<"
      + htmlElement.getType()
      + ">" + htmlElement.getInnerText()
      + getChildElementsAsString(htmlElement)
      + "</"
      + htmlElement.getType()
      + ">";
    }

    return "<"
    + htmlElement.getType()
    + " "
    + getAttributesAsString(htmlElement)
    + ">"
    + htmlElement.getInnerText()
    + getChildElementsAsString(htmlElement)
    + "</"
    + htmlElement.getType()
    + ">";
  }

  private String getChildElementsAsString(final IHtmlElement htmlElement) {
    return htmlElement.getChildElements().toStringWithSeparator("");
  }

  private String getAttributesAsString(final IHtmlElement htmlElement) {
    return htmlElement.getAttributes().toStringWithSeparator(" ");
  }
}
