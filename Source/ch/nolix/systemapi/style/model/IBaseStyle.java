/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.style.stylable.StylableElement;

/**
 * A {@link IBaseStyle} can style {@link StylableElement}s. A {@link IBaseStyle}
 * can distinguish if it would style also the child elements of a given
 * {@link StylableElement}.
 * 
 * @author Silvan Wyss
 * @param <S> the type of a {@link IBaseStyle}.
 */
public interface IBaseStyle<S extends IBaseStyle<S>> extends Element {
  /**
   * Applies the current {@link IBaseStyle} to the given element.
   * 
   * @param element
   */
  void applyToElement(StylableElement<?> element);

  /**
   * @return the attaching attributes of the current {@link IBaseStyle}.
   */
  ExtendedIterable<String> getAttachingAttributes();

  /**
   * @return the sub styles of the current {@link IBaseStyle}.
   */
  ExtendedIterable<? extends ISelectingStyleWithSelectors> getSubStyles();

  /**
   * @return true if the current {@link IBaseStyle} contains attaching attributes,
   *         false otherwise
   */
  boolean containsAttachingAttributes();

  /**
   * @param additionalSubStyle
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         additionalSubStyle
   * @throws RuntimeException if the given additionalSubStyle is not valid
   * @throws RuntimeException if one of the given additionalSubStyles is null
   */
  S withAdditionalSubStyle(ISelectingStyleWithSelectors additionalSubStyle);

  /**
   * @param additionalSubStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         additionalSubStyles
   * @throws RuntimeException if the given additionalSubStyles is null
   * @throws RuntimeException if one of the given additionalSubStyles is null
   */
  S withAdditionalSubStyles(ISelectingStyleWithSelectors... additionalSubStyles);

  /**
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttributes added
   * @throws RuntimeException if the given attachingAttributes is null
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttributes(ExtendedIterable<String> attachingAttributes);

  /**
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttributes added
   * @throws RuntimeException if the given attachingAttributes is null
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttributes(String... attachingAttributes);

  /**
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyles added
   * @throws RuntimeException if the given subStyles is null
   * @throws RuntimeException if one of the given subStyles is not valid
   */
  S withSubStyles(ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles);
}
