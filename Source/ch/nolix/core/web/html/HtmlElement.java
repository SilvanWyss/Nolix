package ch.nolix.core.web.html;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;

public final class HtmlElement implements IHtmlElement {

  private static final HtmlElementStringRepresentator HTML_ELEMENT_STRING_REPRESENTATOR = //
  new HtmlElementStringRepresentator();

  private final String type;

  private final String innerText;

  private final IContainer<HtmlAttribute> attributes;

  private final IContainer<HtmlElement> childElements;

  private HtmlElement(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final String innerText,
    final IContainer<? extends IHtmlElement> childElements) {

    Validator.assertThat(type).thatIsNamed(LowerCaseVariableCatalog.TYPE).isNotBlank();
    Validator.assertThat(innerText).thatIsNamed("inner text").isNotNull();

    this.type = type;
    this.attributes = attributes.to(HtmlAttribute::fromHtmlAttribute);
    this.innerText = innerText;
    this.childElements = childElements.to(HtmlElement::fromHtmlElement);
  }

  public static HtmlElement fromHtmlElement(final IHtmlElement htmlElement) {

    if (htmlElement instanceof final HtmlElement htmlAttribute) {
      return htmlAttribute;
    }

    return withTypeAndAttributesAndChildElements(
      htmlElement.getType(),
      htmlElement.getAttributes(),
      htmlElement.getChildElements());
  }

  public static HtmlElement withType(final String type) {
    return //
    new HtmlElement(type, ImmutableList.createEmpty(), StringCatalog.EMPTY_STRING, ImmutableList.createEmpty());
  }

  public static HtmlElement withTypeAndAttribute(
    final String type,
    final IHtmlAttribute attribute,
    final IHtmlAttribute... attributes) {
    return //
    new HtmlElement(
      type,
      ImmutableList.withElement(attribute, attributes),
      StringCatalog.EMPTY_STRING,
      ImmutableList.createEmpty());
  }

  public static HtmlElement withTypeAndAttributeAndChildElement(
    final String type,
    final IHtmlAttribute attribute,
    final IHtmlElement childElement) {
    return new HtmlElement(
      type,
      ImmutableList.withElement(attribute),
      StringCatalog.EMPTY_STRING,
      ImmutableList.withElement(childElement));
  }

  public static HtmlElement withTypeAndAttributes(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, ImmutableList.createEmpty());
  }

  public static HtmlElement withTypeAndAttributesAndChildElement(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final IHtmlElement childElement,
    final IHtmlElement... childElements) {

    final var allChildElements = ImmutableList.withElement(childElement, childElements);

    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, allChildElements);
  }

  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final IContainer<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, childElements);
  }

  public static HtmlElement withTypeAndAttributesAndInnerText(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final String innerText) {
    return new HtmlElement(type, attributes, innerText, ImmutableList.createEmpty());
  }

  public static HtmlElement withTypeAndChildElement(
    final String type,
    final IHtmlElement childElement,
    final IHtmlElement... childElements) {
    return //
    new HtmlElement(
      type,
      ImmutableList.createEmpty(),
      StringCatalog.EMPTY_STRING,
      ImmutableList.withElement(childElement, childElements));
  }

  public static HtmlElement withTypeAndChildElements(
    final String type,
    final IContainer<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, ImmutableList.createEmpty(), StringCatalog.EMPTY_STRING, childElements);
  }

  public static HtmlElement withTypeAndInnerText(final String type, final String innerText) {
    return new HtmlElement(type, ImmutableList.createEmpty(), innerText, ImmutableList.createEmpty());
  }

  @Override
  public boolean containsAttributes() {
    return getAttributes().containsAny();
  }

  @Override
  public boolean containsChildElements() {
    return getChildElements().containsAny();
  }

  @Override
  public IContainer<? extends IHtmlAttribute> getAttributes() {
    return attributes;
  }

  @Override
  public IContainer<? extends IHtmlElement> getChildElements() {
    return childElements;
  }

  @Override
  public String getInnerText() {
    return innerText;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public boolean equals(final Object object) {

    if (object instanceof final HtmlElement htmlElement) {
      return getType().equals(htmlElement.getType())
      && getAttributes().containsExactlyEqualingInSameOrder(htmlElement.getAttributes())
      && getChildElements().containsExactlyEqualingInSameOrder(htmlElement.getChildElements())
      && getInnerText().equals(htmlElement.getInnerText());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    return HTML_ELEMENT_STRING_REPRESENTATOR.toString(this);
  }

  @Override
  public IHtmlElement withAttribute(final IHtmlAttribute attribute, final IHtmlAttribute... attributes) {

    final var allAttributes = //
    ContainerView.forIterable(getAttributes(), ContainerView.forElementAndArray(attribute, attributes));

    if (containsChildElements()) {
      return withTypeAndAttributesAndChildElements(getType(), allAttributes, getChildElements());
    }

    return withTypeAndAttributesAndInnerText(getType(), allAttributes, getInnerText());
  }
}
