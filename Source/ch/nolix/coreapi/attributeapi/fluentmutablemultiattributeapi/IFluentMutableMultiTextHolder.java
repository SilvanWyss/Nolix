package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTextHolder;

/**
 * A {@link IFluentMutableMultiTextHolder} is a {@link IMultiTextHolder} whose
 * texts can be added and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 * @param <FMMTH> is the type of a {@link IFluentMutableMultiTextHolder}.
 */
public interface IFluentMutableMultiTextHolder<FMMTH extends IFluentMutableMultiTextHolder<FMMTH>>
extends IMultiTextHolder {

  /**
   * Adds the given text to the current {@link IFluentMutableMultiTextHolder}.
   * 
   * @param text
   * @return the current {@link IFluentMutableMultiTextHolder}.
   * @throws RuntimeException if the given text is null or blank.
   */
  FMMTH addText(String text);

  /**
   * Removes the text that equals the given text from the current
   * {@link IFluentMutableMultiTextHolder} if the current
   * {@link IFluentMutableMultiTextHolder} contains such a text.
   * 
   * @param text
   * @return the current {@link IFluentMutableMultiTextHolder}.
   */
  FMMTH removeText(String text);

  /**
   * Removes all texts from the current {@link IFluentMutableMultiTextHolder}.
   * 
   * @return the current {@link IFluentMutableMultiTextHolder}.
   */
  FMMTH removeTexts();
}
