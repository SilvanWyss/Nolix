/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * A {@link FluentMutableNameHolder} is a {@link NameHolder} whose name can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableNameHolder}
 */
public interface FluentMutableNameHolder<H extends FluentMutableNameHolder<H>> extends NameHolder {
  /**
   * Sets the name of the current {@link FluentMutableNameHolder}.
   * 
   * @param name
   * @return the current {@link FluentMutableNameHolder}
   * @throws RuntimeException if the given name is null or blank
   */
  H setName(String name);
}
