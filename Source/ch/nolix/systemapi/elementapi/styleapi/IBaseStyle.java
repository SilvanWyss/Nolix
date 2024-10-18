package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

/**
 * A {@link IBaseStyle} can style {@link IStylableElement}s. A
 * {@link IBaseStyle} can distinguish if it would style also the child elements
 * of a given {@link IStylableElement}.
 * 
 * @author Silvan Wyss
 * @version 2023-07-09
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
  IContainer<? extends INode<?>> getAttachingAttributes();

  /**
   * @return the sub styles of the current {@link IBaseStyle}.
   */
  IContainer<? extends ISelectingStyleWithSelectors> getSubStyles();

  /**
   * @param attachingAttribute
   * @param attachingAttributes
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttribute and attachingAttributes added.
   * @throws RuntimeException if the given attachingAttribute is not valid.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   */
  S withAttachingAttribute(String attachingAttribute, String... attachingAttributes);

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
