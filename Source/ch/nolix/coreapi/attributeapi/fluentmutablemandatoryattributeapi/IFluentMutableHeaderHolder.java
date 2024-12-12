package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IHeaderHolder;

/**
 * A {@link IFluentMutableHeaderHolder} is a {@link IHeaderHolder} whose header
 * can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2018-04-15
 * @param <H> is the type of a {@link IFluentMutableHeaderHolder}.
 */
public interface IFluentMutableHeaderHolder<H extends IHeaderHolder> extends IHeaderHolder {

  /**
   * Sets the header of the current {@link IFluentMutableHeaderHolder}.
   * 
   * @param header
   * @return the current {@link IFluentMutableHeaderHolder}.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  H setHeader(String header);
}
