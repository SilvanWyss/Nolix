package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITextHolder;

/**
 * A {@link IFluentMutableTextHolder} is a {@link ITextHolder} whose text can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2021-06-19
 * @param <FMTH> is the type of a {@link IFluentMutableTextHolder}.
 */
public interface IFluentMutableTextHolder<FMTH extends IFluentMutableTextHolder<FMTH>> extends ITextHolder {

  /**
   * Sets the text of the current {@link IFluentMutableTextHolder}.
   * 
   * @param text
   * @return the current {@link IFluentMutableTextHolder}.
   * @throws RuntimeException if the given text is null.
   */
  FMTH setText(String text);
}
