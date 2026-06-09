/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalIdHolder;

/**
 * A {@link IFluentMutableOptionalIdHolder} is a {@link IOptionalIdHolder} whose
 * id can be set programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalIdHolder}
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
   * @return the current {@link IFluentMutableOptionalIdHolder}
   * @throws RuntimeException if the given id is null or blank
   */
  H setId(String id);
}
