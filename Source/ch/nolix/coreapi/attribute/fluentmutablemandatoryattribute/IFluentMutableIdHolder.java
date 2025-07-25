package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;

/**
 * A {@link IFluentMutableIdHolder} is a {@link IIdHolder} whose id can be set
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2020-03-29
 * @param <H> is the type of a {@link IFluentMutableIdHolder}.
 */
public interface IFluentMutableIdHolder<H> extends IIdHolder {

  /**
   * Sets the id of the current {@link IFluentMutableIdHolder}.
   * 
   * @param id
   * @return the current {@link IFluentMutableIdHolder}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  H setId(String id);
}
