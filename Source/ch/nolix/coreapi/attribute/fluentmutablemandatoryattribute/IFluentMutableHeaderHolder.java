package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IHeaderHolder;

/**
 * A {@link IFluentMutableHeaderHolder} is a {@link IHeaderHolder} whose header
 * can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
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
