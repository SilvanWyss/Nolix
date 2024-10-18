package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

/**
 * A {@link IFluentMutableNameHolder} is a {@link INameHolder} whose name can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <FMNH> is the type of a {@link IFluentMutableNameHolder}.
 */
public interface IFluentMutableNameHolder<FMNH extends IFluentMutableNameHolder<FMNH>> extends INameHolder {

  /**
   * Sets the name of the current {@link IFluentMutableNameHolder}.
   * 
   * @param name
   * @return the current {@link IFluentMutableNameHolder}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  FMNH setName(String name);
}
