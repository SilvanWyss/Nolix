//package declaration
package ch.nolix.core.web.html;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;

//class
public final class HtmlElementStringRepresentator {

  //method
  public String toString(final IHtmlElement htmlElement) {

    if (!htmlElement.containsChildElements()) {
      return toStringWhenDoesNotContainChildElements(htmlElement);
    }

    return toStringWhenContainsChildElements(htmlElement);
  }

  //method
  private String toStringWhenDoesNotContainChildElements(final IHtmlElement htmlElement) {

    if (htmlElement.getInnerText().isEmpty()) {
      return toStringWhenDoesNotContainChildElementsAndHasEmptyInnerText(htmlElement);
    }

    return toStringWhenDoesNotContainChildElementsAndHasNonEmptyInnerText(htmlElement);
  }

  //method
  private String toStringWhenDoesNotContainChildElementsAndHasEmptyInnerText(final IHtmlElement htmlElement) {

    if (!htmlElement.containsAttributes()) {
      return ("<" + htmlElement.getType() + " />");
    }

    return ("<" + htmlElement.getType() + " " + getAttributesAsString(htmlElement) + " />");
  }

  //method
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

  //method
  private String toStringWhenContainsChildElements(final IHtmlElement htmlElement) {

    if (htmlElement.getInnerText().isEmpty()) {
      return toStringWhenContainsChildElementsAndHasEmptyInnerText(htmlElement);
    }

    return toStringWhenContainsChildElementsAndHasNonEmptyInnerText(htmlElement);
  }

  //method
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

  //method
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

  //method
  private String getChildElementsAsString(final IHtmlElement htmlElement) {
    return htmlElement.getChildElements().toStringWithSeparator("");
  }

  //method
  private String getAttributesAsString(final IHtmlElement htmlElement) {
    return htmlElement.getAttributes().toStringWithSeparator(" ");
  }
}
