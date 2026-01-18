/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * A {@link IFluentMutableNameHolder} is a {@link INameHolder} whose name can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableNameHolder}.
 */
public interface IFluentMutableNameHolder<H extends IFluentMutableNameHolder<H>> extends INameHolder {
  /**
   * Sets the name of the current {@link IFluentMutableNameHolder}.
   * 
   * @param name
   * @return the current {@link IFluentMutableNameHolder}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  H setName(String name);
}
