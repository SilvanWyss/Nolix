/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.model;

import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * A {@link ISelectingStyle} is a {@link IBaseStyle} that can select or skip a
 * {@link IStylableElement} that is given to be styled.
 * 
 * @author Silvan Wyss
 * @param <S> is the type of a {@link ISelectingStyle}.
 */
public interface ISelectingStyle<S extends ISelectingStyle<S>> extends IBaseStyle<S> {
  /**
   * @return true if the current {@link ISelectingStyle} would select the child
   *         elements of a given {@link IStylableElement} to style, false
   *         otherwise.
   */
  boolean selectsChildElements();

  /**
   * @param element
   * @return true if the current {@link ISelectingStyle} would select the given
   *         element to style, false otherwise.
   */
  boolean selectsElement(IStylableElement<?> element);

  /**
   * @return true if the current {@link ISelectingStyle} would not (!) select the
   *         child elements of a given {@link IStylableElement} to style, false
   *         otherwise.
   */
  default boolean skipsChildElements() {
    return !selectsChildElements();
  }

  /**
   * @param element
   * @return true if the current {@link ISelectingStyle} would not (!) select the
   *         given element to style, false otherwise.
   */
  default boolean skipsElement(final IStylableElement<?> element) {
    return !selectsElement(element);
  }
}
