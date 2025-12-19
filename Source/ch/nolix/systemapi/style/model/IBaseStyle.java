package ch.nolix.systemapi.style.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * A {@link IBaseStyle} can style {@link IStylableElement}s. A
 * {@link IBaseStyle} can distinguish if it would style also the child elements
 * of a given {@link IStylableElement}.
 * 
 * @author Silvan Wyss
 * @param <S> is the type of a {@link IBaseStyle}.
 */
public interface IBaseStyle<S extends IBaseStyle<S>> extends IElement {
  /**
   * Applies the current {@link IBaseStyle} to the given element.
   * 
   * @param element
   */
  void applyToElement(IStylableElement<?> element);

  /**
   * @return the attaching attributes of the current {@link IBaseStyle}.
   */
  IContainer<String> getAttachingAttributes();

  /**
   * @return the sub styles of the current {@link IBaseStyle}.
   */
  IContainer<? extends ISelectingStyleWithSelectors> getSubStyles();

  /**
   * @return true if the current {@link IBaseStyle} contains attaching attributes,
   *         false otherwise.
   */
  boolean containsAttachingAttributes();

  /**
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttributes added.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttributes(IContainer<String> attachingAttributes);

  /**
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttributes added.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttributes(String... attachingAttributes);

  /**
   * @param subStyle
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyle and subStyles added.
   * @throws RuntimeException if the given subStyle is not valid.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  S withSubStyle(ISelectingStyleWithSelectors subStyle, ISelectingStyleWithSelectors... subStyles);

  /**
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyles added.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  S withSubStyles(IContainer<? extends ISelectingStyleWithSelectors> subStyles);
}
