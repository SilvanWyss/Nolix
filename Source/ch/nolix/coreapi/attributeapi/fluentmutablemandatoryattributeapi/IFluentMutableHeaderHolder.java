//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Headered;

//interface
/**
 * A {@link IFluentMutableHeaderHolder} is a {@link Headered} whose header can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-04-15
 * @param <FMHH> is the type of a {@link IFluentMutableHeaderHolder}.
 */
public interface IFluentMutableHeaderHolder<FMHH extends Headered> extends Headered {

  //method declaration
  /**
   * Sets the header of the current {@link IFluentMutableHeaderHolder}.
   * 
   * @param header
   * @return the current {@link IFluentMutableHeaderHolder}.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  FMHH setHeader(String header);
}
