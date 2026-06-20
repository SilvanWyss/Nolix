/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalIdHolder;

/**
 * A {@link FluentMutableOptionalIdHolder} is a {@link OptionalIdHolder} whose
 * id can be set programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalIdHolder}
 */
public interface FluentMutableOptionalIdHolder<H extends FluentMutableOptionalIdHolder<H>> extends OptionalIdHolder {
  /**
   * Removes the id of the current {@link FluentMutableOptionalIdHolder}.
   */
  void removeId();

  /**
   * Sets the id of the current {@link FluentMutableOptionalIdHolder}.
   * 
   * @param id
   * @return the current {@link FluentMutableOptionalIdHolder}
   * @throws RuntimeException if the given id is null or blank
   */
  H setId(String id);
}
