/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;

/**
 * A {@link FluentMutableIdHolder} is a {@link IdHolder} whose id can be set
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableIdHolder}
 */
public interface FluentMutableIdHolder<H> extends IdHolder {
  /**
   * Sets the id of the current {@link FluentMutableIdHolder}.
   * 
   * @param id
   * @return the current {@link FluentMutableIdHolder}
   * @throws RuntimeException if the given id is null or blank
   */
  H setId(String id);
}
