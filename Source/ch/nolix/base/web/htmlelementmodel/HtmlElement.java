/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.htmlelementmodel;

import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontype.stringtool.StringCatalog;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;

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
    Validator.assertThat(type).thatIsNamed(LowerCaseVariableCatalog.TYPE).isNotBlank();
    Validator.assertThat(innerText).thatIsNamed("inner text").isNotNull();

    this.type = type;
    this.memmberAttributes = attributes.to(HtmlAttribute::fromHtmlAttribute);
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

  public static HtmlElement withTypeAndAttributes(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, ImmutableList.createEmpty());
  }

  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final IHtmlElement... childElements) {
    final var childElementsContainerView = ContainerView.forArray(childElements);

    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, childElementsContainerView);
  }

  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final ExtendedIterable<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, childElements);
  }

  public static HtmlElement withTypeAndAttributesAndInnerText(
    final String type,
    final ExtendedIterable<? extends IHtmlAttribute> attributes,
    final String innerText) {
    return new HtmlElement(type, attributes, innerText, ImmutableList.createEmpty());
  }

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

  public static HtmlElement withTypeAndChildElements(
    final String type,
    final IHtmlElement... childElements) {
    return //
    new HtmlElement(
      type,
      ImmutableList.createEmpty(),
      StringCatalog.EMPTY_STRING,
      ContainerView.forArray(childElements));
  }

  public static HtmlElement withTypeAndChildElements(
    final String type,
    final ExtendedIterable<? extends IHtmlElement> childElements) {
    return new HtmlElement(type, ImmutableList.createEmpty(), StringCatalog.EMPTY_STRING, childElements);
  }

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
  public boolean equals(final Object object) {
    if (object instanceof final HtmlElement htmlElement) {
      return getType().equals(htmlElement.getType())
      && getAttributes().containsExactlyEqualingInSameOrder(htmlElement.getAttributes())
      && getChildElements().containsExactlyEqualingInSameOrder(htmlElement.getChildElements())
      && getInnerText().equals(htmlElement.getInnerText());
    }

    return false;
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
    final var attributes = ContainerView.forIterables(getAttributes(), additionalAttributes);

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
    final var additionalAttributesContainer = ContainerView.forArray(additionalAttributes);

    return withAdditionalAttributes(additionalAttributesContainer);
  }
}
