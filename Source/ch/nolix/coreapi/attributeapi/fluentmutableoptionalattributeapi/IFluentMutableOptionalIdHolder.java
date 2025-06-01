package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalIdHolder;

/**
 * A {@link IFluentMutableOptionalIdHolder} is a {@link IOptionalIdHolder} whose
 * id can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2020-02-01
 * @param <H> is the type of a {@link IFluentMutableOptionalIdHolder}.
 */
public interface IFluentMutableOptionalIdHolder<H extends IFluentMutableOptionalIdHolder<H>> extends IOptionalIdHolder {

  /**
   * Removes the id of the current {@link IFluentMutableOptionalIdHolder}.
   */
  void removeId();

  /**
   * Sets the id of the current {@link IFluentMutableOptionalIdHolder}.
   * 
   * @param id
   * @return the current {@link IFluentMutableOptionalIdHolder}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  H setId(String id);
}
