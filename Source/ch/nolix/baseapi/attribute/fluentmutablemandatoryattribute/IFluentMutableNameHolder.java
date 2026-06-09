/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;

/**
 * A {@link IFluentMutableNameHolder} is a {@link INameHolder} whose name can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableNameHolder}
 */
public interface IFluentMutableNameHolder<H extends IFluentMutableNameHolder<H>> extends INameHolder {
  /**
   * Sets the name of the current {@link IFluentMutableNameHolder}.
   * 
   * @param name
   * @return the current {@link IFluentMutableNameHolder}
   * @throws RuntimeException if the given name is null or blank
   */
  H setName(String name);
}
