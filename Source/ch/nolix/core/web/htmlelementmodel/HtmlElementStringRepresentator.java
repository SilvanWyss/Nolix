package ch.nolix.core.web.htmlelementmodel;

import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;

/**
 * @author Silvan Wyss
 */
public final class HtmlElementStringRepresentator {
  private HtmlElementStringRepresentator() {
  }

  public static String toString(final IHtmlElement htmlElement) {
    if (!htmlElement.containsChildElements()) {
      return toStringWhenDoesNotContainChildElements(htmlElement);
    }

    return toStringWhenContainsChildElements(htmlElement);
  }

  private static String toStringWhenDoesNotContainChildElements(final IHtmlElement htmlElement) {
    if (htmlElement.getInnerText().isEmpty()) {
      return toStringWhenDoesNotContainChildElementsAndHasEmptyInnerText(htmlElement);
    }

    return toStringWhenDoesNotContainChildElementsAndHasNonEmptyInnerText(htmlElement);
  }

  private static String toStringWhenDoesNotContainChildElementsAndHasEmptyInnerText(final IHtmlElement htmlElement) {
    if (!htmlElement.containsAttributes()) {
      return ("<" + htmlElement.getType() + " />");
    }

    return ("<" + htmlElement.getType() + " " + getAttributesAsString(htmlElement) + " />");
  }

  private static String toStringWhenDoesNotContainChildElementsAndHasNonEmptyInnerText(final IHtmlElement htmlElement) {
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

  private static String toStringWhenContainsChildElements(final IHtmlElement htmlElement) {
    if (htmlElement.getInnerText().isEmpty()) {
      return toStringWhenContainsChildElementsAndHasEmptyInnerText(htmlElement);
    }

    return toStringWhenContainsChildElementsAndHasNonEmptyInnerText(htmlElement);
  }

  private static String toStringWhenContainsChildElementsAndHasEmptyInnerText(final IHtmlElement htmlElement) {
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

  private static String toStringWhenContainsChildElementsAndHasNonEmptyInnerText(final IHtmlElement htmlElement) {
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

  private static String getChildElementsAsString(final IHtmlElement htmlElement) {
    return htmlElement.getChildElements().toStringWithSeparator("");
  }

  private static String getAttributesAsString(final IHtmlElement htmlElement) {
    return htmlElement.getAttributes().toStringWithSeparator(" ");
  }
}
