/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.htmlmodel;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlAttribute;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;

/**
 * @author Silvan Wyss
 */
public final class HtmlElement implements IHtmlElement {
  private final String type;

  private final String innerText;

  private final ExtendedIterable<HtmlAttribute> memmberAttributes;

  private final ExtendedIterable<HtmlElement> childElements;

  private HtmlElement(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final String innerText,
    final ExtendedIterable<? extends IHtmlElement> childElements) {
    Validator.assertThat(type).thatIsNamed(LowerCaseVariableNameCatalog.TYPE).isNotBlank();
    Validator.assertThat(innerText).thatIsNamed("inner text").isNotNull();

    this.type = type;
    this.memmberAttributes = attributes.to(HtmlAttribute::fromHtmlAttribute);
    this.innerText = innerText;
    this.childElements = childElements.to(HtmlElement::fromHtmlElement);
  }

  /**
   * @param htmlElement
   * @return a new {@link HtmlElement} from the given htmlElement
   */
  public static HtmlElement fromHtmlElement(final IHtmlElement htmlElement) {
    if (htmlElement instanceof final HtmlElement htmlAttribute) {
      return htmlAttribute;
    }

    return withTypeAndAttributesAndChildElements(
      htmlElement.getType(),
      htmlElement.getAttributes(),
      htmlElement.getChildElements());
  }

  /**
   * @param type
   * @return a new {@link HtmlElement} with the given type
   */
  public static HtmlElement withType(final String type) {
    return //
    new HtmlElement(type, ImmutableList.createEmpty(), StringCatalog.EMPTY_STRING, ImmutableList.createEmpty());
  }

  /**
   * @param type
   * @param attributes
   * @return a new {@link HtmlElement} with the given type and attributes
   */
  public static HtmlElement withTypeAndAttributes(
    final String type,
    final IHtmlAttribute... attributes) {
    return //
    new HtmlElement(
      type,
      ImmutableList.withElements(attributes),
      StringCatalog.EMPTY_STRING,
      ImmutableList.createEmpty());
  }

  /**
   * @param type
   * @param attribute
   * @param childElement
   * @return a new {@link HtmlElement} with the given type, attribute and
   *         childElement
   */
  public static HtmlElement withTypeAndAttributeAndChildElement(
    final String type,
    final IHtmlAttribute attribute,
    final IHtmlElement childElement) {
    return new HtmlElement(
      type,
      ImmutableList.withElements(attribute),
      StringCatalog.EMPTY_STRING,
      ImmutableList.withElements(childElement));
  }

  /**
   * @param type
   * @param attributes
   * @return a new {@link HtmlElement} with the given type and attributes
   */
  public static HtmlElement withTypeAndAttributes(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, ImmutableList.createEmpty());
  }

  /**
   * @param type
   * @param attributes
   * @param childElements
   * @return a new {@link HtmlElement} with the given type, attributes and
   *         childElements
   */
  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final IHtmlElement... childElements) {
    final var childElementsContainerView = ExtendedIterableView.forArray(childElements);

    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, childElementsContainerView);
  }

  /**
   * @param type
   * @param attributes
   * @param childElements
   * @return a new {@link HtmlElement} with the given type, attributes and
   *         childElements
   */
  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final ExtendedIterable<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, childElements);
  }

  /**
   * @param type
   * @param attributes
   * @param innerText
   * @return a new {@link HtmlElement} with the given type, attributes and
   *         innerText
   */
  public static HtmlElement withTypeAndAttributesAndInnerText(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final String innerText) {
    return new HtmlElement(type, attributes, innerText, ImmutableList.createEmpty());
  }

  /**
   * @param type
   * @param childElement
   * @return a new {@link HtmlElement} with the given type and childElement
   */
  public static HtmlElement withTypeAndChildElement(
    final String type,
    final IHtmlElement childElement) {
    return //
    new HtmlElement(
      type,
      ImmutableList.createEmpty(),
      StringCatalog.EMPTY_STRING,
      ImmutableList.withElement(childElement));
  }

  /**
   * @param type
   * @param childElements
   * @return a new {@link HtmlElement} with the given type and childElements
   */
  public static HtmlElement withTypeAndChildElements(
    final String type,
    final IHtmlElement... childElements) {
    return //
    new HtmlElement(
      type,
      ImmutableList.createEmpty(),
      StringCatalog.EMPTY_STRING,
      ExtendedIterableView.forArray(childElements));
  }

  /**
   * @param type
   * @param childElements
   * @return a new {@link HtmlElement} with the given type and childElements
   */
  public static HtmlElement withTypeAndChildElements(
    final String type,
    final ExtendedIterable<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, ImmutableList.createEmpty(), StringCatalog.EMPTY_STRING, childElements);
  }

  /**
   * @param type
   * @param innerText
   * @return a new {@link HtmlElement} with the given type and innerText
   */
  public static HtmlElement withTypeAndInnerText(final String type, final String innerText) {
    return new HtmlElement(type, ImmutableList.createEmpty(), innerText, ImmutableList.createEmpty());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAttributes() {
    return getAttributes().containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsChildElements() {
    return getChildElements().containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    if (object instanceof final HtmlElement htmlElement) {
      return getType().equals(htmlElement.getType())
      && getAttributes().containsExactlyAllEqualInSameOrder(htmlElement.getAttributes())
      && getChildElements().containsExactlyAllEqualInSameOrder(htmlElement.getChildElements())
      && getInnerText().equals(htmlElement.getInnerText());
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IHtmlAttribute> getAttributes() {
    return memmberAttributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IHtmlElement> getChildElements() {
    return childElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getInnerText() {
    return innerText;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getType() {
    return type;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return HtmlElementStringRepresentator.toString(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement withAdditionalAttributes(final ExtendedIterable<IHtmlAttribute> additionalAttributes) {
    final var attributes = ExtendedIterableView.forIterables(getAttributes(), additionalAttributes);

    if (containsChildElements()) {
      return withTypeAndAttributesAndChildElements(getType(), attributes, getChildElements());
    }

    return withTypeAndAttributesAndInnerText(getType(), attributes, getInnerText());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement withAdditionalAttributes(final IHtmlAttribute... additionalAttributes) {
    final var additionalAttributesContainer = ExtendedIterableView.forArray(additionalAttributes);

    return withAdditionalAttributes(additionalAttributesContainer);
  }
}
