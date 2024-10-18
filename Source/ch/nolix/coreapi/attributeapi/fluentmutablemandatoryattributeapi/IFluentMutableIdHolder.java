package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;

/**
 * A {@link IFluentMutableIdHolder} is a {@link IIdHolder} whose id can be set
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2020-03-29
 * @param <FMIH> is the type of a {@link IFluentMutableIdHolder}.
 */
public interface IFluentMutableIdHolder<FMIH> extends IIdHolder {

  /**
   * Sets the id of the current {@link IFluentMutableIdHolder}.
   * 
   * @param id
   * @return the current {@link IFluentMutableIdHolder}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  FMIH setId(String id);
}
