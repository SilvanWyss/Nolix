/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.web.htmlelementmodel;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;

/**
 * @author Silvan Wyss
 */
public final class HtmlElement implements IHtmlElement {
  private final String type;

  private final String innerText;

  private final IContainer<HtmlAttribute> memmberAttributes;

  private final IContainer<HtmlElement> childElements;

  private HtmlElement(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final String innerText,
    final IContainer<? extends IHtmlElement> childElements) {
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
    final IContainer<? extends IHtmlAttribute> attributes) {
    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, ImmutableList.createEmpty());
  }

  public static HtmlElement withTypeAndAttributesAndChildElements(
    final String type,
    final IContainer<? extends IHtmlAttribute> attributes,
    final IHtmlElement... childElements) {
    final var childElementsContainerView = ContainerView.forArray(childElements);

    return new HtmlElement(type, attributes, StringCatalog.EMPTY_STRING, childElementsContainerView);
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
    final IContainer<? extends IHtmlElement> childElements) {
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
  public IContainer<? extends IHtmlAttribute> getAttributes() {
    return memmberAttributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IHtmlElement> getChildElements() {
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
  public IHtmlElement withAttribute(final IHtmlAttribute attribute, final IHtmlAttribute... attributes) {
    final var allAttributes = //
    ContainerView.forIterables(getAttributes(), ContainerView.forElementAndArray(attribute, attributes));

    if (containsChildElements()) {
      return withTypeAndAttributesAndChildElements(getType(), allAttributes, getChildElements());
    }

    return withTypeAndAttributesAndInnerText(getType(), allAttributes, getInnerText());
  }
}
