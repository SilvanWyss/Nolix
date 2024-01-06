//package declaration
package ch.nolix.core.web.html;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringutilapi.StringCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;

//class
public final class HtmlElement implements IHtmlElement {

  //constant
  private static final HtmlElementStringRepresentator HTML_ELEMENT_STRING_REPRESENTATOR = //
  new HtmlElementStringRepresentator();

  //attribute
  private final String type;

  //attribute
  private final String innerText;

  //multi-attribute
  private final IContainer<HtmlAttribute> attributes;

  //multi-attribute
  private final IContainer<HtmlElement> childElements;

  //constructor
  private HtmlElement(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final String innerText,
    final IContainer<? extends IHtmlElement> childElements) {

    GlobalValidator.assertThat(type).thatIsNamed(LowerCaseCatalogue.TYPE).isNotBlank();
    GlobalValidator.assertThat(innerText).thatIsNamed("inner text").isNotNull();

    this.type = type;
    this.attributes = attributes.to(HtmlAttribute::fromHtmlAttribute);
    this.innerText = innerText;
    this.childElements = childElements.to(HtmlElement::fromHtmlElement);
  }

  //static method
  public static HtmlElement fromHtmlElement(final IHtmlElement htmlElement) {

    if (htmlElement instanceof HtmlElement htmlAttribute) {
      return htmlAttribute;
    }

    return withTypeAndAttributesAndChildElements(
      htmlElement.getType(),
      htmlElement.getAttributes(),
      htmlElement.getChildElements());
  }

  //static method
  public static HtmlElement withType(final String type) {
    return new HtmlElement(type, new ImmutableList<>(), StringCatalogue.EMPTY_STRING, new ImmutableList<>());
  }

  //static method
  public static HtmlElement withTypeAndAttribute(
    final String type,
    final IHtmlAttribute attribute,
    final IHtmlAttribute... attributes) {
    return //
    new HtmlElement(
      type,
      ImmutableList.withElement(attribute, attributes),
      StringCatalogue.EMPTY_STRING,
      new ImmutableList<>());
  }

  //static method
  public static HtmlElement withTypeAndAttributeAndChildElement(
    final String type,
    final IHtmlAttribute attribute,
    final IHtmlElement childElement) {
    return new HtmlElement(
      type,
      ImmutableList.withElement(attribute),
      StringCatalogue.EMPTY_STRING,
      ImmutableList.withElement(childElement));
  }

  //static method
  public static HtmlElement withTypeAndAttributes(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes) {
    return new HtmlElement(type, attributes, StringCatalogue.EMPTY_STRING, new ImmutableList<>());
  }

  //static method
  public static HtmlElement withTypeAndAttributesAndChildElement(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final IHtmlElement childElement,
    final IHtmlElement... childElements) {

    final var allChildElements = ImmutableList.withElement(childElement, childElements);

    return new HtmlElement(type, attributes, StringCatalogue.EMPTY_STRING, allChildElements);
  }

  //static method
  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final IContainer<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, attributes, StringCatalogue.EMPTY_STRING, childElements);
  }

  //static method
  public static HtmlElement withTypeAndAttributesAndInnerText(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final String innerText) {
    return new HtmlElement(type, attributes, innerText, new ImmutableList<>());
  }

  //static method
  public static HtmlElement withTypeAndChildElement(
    final String type,
    final IHtmlElement childElement,
    final IHtmlElement... childElements) {
    return //
    new HtmlElement(
      type,
      new ImmutableList<>(),
      StringCatalogue.EMPTY_STRING,
      ImmutableList.withElement(childElement, childElements));
  }

  //static method
  public static HtmlElement withTypeAndChildElements(
    final String type,
    final IContainer<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, new ImmutableList<>(), StringCatalogue.EMPTY_STRING, childElements);
  }

  //static method
  public static HtmlElement withTypeAndInnerText(final String type, final String innerText) {
    return new HtmlElement(type, new ImmutableList<>(), innerText, new ImmutableList<>());
  }

  //method
  @Override
  public boolean containsAttributes() {
    return getAttributes().containsAny();
  }

  //method
  @Override
  public boolean containsChildElements() {
    return getChildElements().containsAny();
  }

  //method
  @Override
  public IContainer<? extends IHtmlAttribute> getAttributes() {
    return attributes;
  }

  //method
  @Override
  public IContainer<? extends IHtmlElement> getChildElements() {
    return childElements;
  }

  //method
  @Override
  public String getInnerText() {
    return innerText;
  }

  //method
  @Override
  public String getType() {
    return type;
  }

  //method
  @Override
  public boolean equals(final Object object) {

    if (object instanceof HtmlElement htmlElement) {
      return getType().equals(htmlElement.getType())
      && getAttributes().containsOnlyEqualingAndViceVersa(htmlElement.getAttributes())
      && getChildElements().containsOnlyEqualingAndViceVersa(htmlElement.getChildElements())
      && getInnerText().equals(htmlElement.getInnerText());
    }

    return false;
  }

  //method
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  @Override
  public String toString() {
    return HTML_ELEMENT_STRING_REPRESENTATOR.toString(this);
  }

  //method
  @Override
  public IHtmlElement withAttribute(final IHtmlAttribute attribute, final IHtmlAttribute... attributes) {

    final var allAttributes = ReadContainer.forIterable(ReadContainer.forElement(attribute, attributes),
      getAttributes());

    if (containsChildElements()) {
      return withTypeAndAttributesAndChildElements(getType(), allAttributes, getChildElements());
    }

    return withTypeAndAttributesAndInnerText(getType(), allAttributes, getInnerText());
  }
}
