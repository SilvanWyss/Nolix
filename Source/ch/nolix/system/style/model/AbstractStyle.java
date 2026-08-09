/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.model;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.style.model.IBaseStyle;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.StylableElement;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link AbstractStyle}.
 */
abstract class AbstractStyle<S extends IBaseStyle<S>> extends AbstractElement implements IBaseStyle<S> {
  protected static final String ATTACHING_ATTRIBUTE_HEADER = "AttachingAttribute";

  private final ImmutableList<String> memberAttachingAttributes;

  private final ImmutableList<? extends ISelectingStyleWithSelectors> memberSubStyles;

  /**
   * Creates a new {@link AbstractStyle}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  protected AbstractStyle(
    final ExtendedIterable<String> attachingAttributes,
    final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    memberAttachingAttributes = ImmutableList.fromIterable(attachingAttributes);

    memberSubStyles = ImmutableList.fromIterable(subStyles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<String> getAttachingAttributes() {
    return memberAttachingAttributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<? extends ISelectingStyleWithSelectors> getSubStyles() {
    return memberSubStyles;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAttachingAttributes() {
    return memberAttachingAttributes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAdditionalSubStyle(final ISelectingStyleWithSelectors additionalSubStyle) {
    final var additionalSubStyles = ImmutableList.withElement(additionalSubStyle);

    return withSubStyles(additionalSubStyles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAdditionalSubStyles(final ISelectingStyleWithSelectors... additionalSubStyles) {
    final var additionalSubStylesContainer = ExtendedIterableView.forArray(additionalSubStyles);

    return withSubStyles(additionalSubStylesContainer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAttachingAttributes(final String... attachingAttributes) {
    final var attachingAttributesView = ExtendedIterableView.forArray(attachingAttributes);

    return withAttachingAttributes(attachingAttributesView);
  }

  /**
   * Sets the attaching attributes of the current {@link AbstractStyle} to the
   * given element.
   * 
   * @param element
   * @throws RuntimeException if an attaching attribute of the current
   *                          {@link AbstractStyle} is not valid for the given
   *                          element.
   */
  protected final void setAttachingAttributesToElement(StylableElement<?> element) {
    for (final var a : getAttachingAttributes()) {
      try {
        element.addOrChangeAttribute(a);
      } catch (final Throwable error) { // NOSONAR: All Throwable must be caught.

        final var invalidArgumentException = InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          a,
          "attaching attribute",
          "could not be added to the given " + element.getType() + " '" + element.getSpecification() + "'");

        invalidArgumentException.initCause(error);

        throw invalidArgumentException;
      }
    }
  }

  /**
   * Lets the sub styles of the current {@link AbstractStyle} style the child
   * element of the given element.
   * 
   * @param element
   */
  protected final void letSubStylesStyleChildElementsOfElement(final StylableElement<?> element) {
    final var childElements = element.getStoredChildStylableElements();

    getSubStyles().forEach(ss -> childElements.forEach(ss::applyToElement));
  }
}
