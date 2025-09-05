package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITextHolder;

/**
 * A {@link IFluentMutableTextHolder} is a {@link ITextHolder} whose text can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2021-06-19
 * @param <H> is the type of a {@link IFluentMutableTextHolder}.
 */
public interface IFluentMutableTextHolder<H extends IFluentMutableTextHolder<H>> extends ITextHolder {
  /**
   * Sets the text of the current {@link IFluentMutableTextHolder}.
   * 
   * @param text
   * @return the current {@link IFluentMutableTextHolder}.
   * @throws RuntimeException if the given text is null.
   */
  H setText(String text);
}
