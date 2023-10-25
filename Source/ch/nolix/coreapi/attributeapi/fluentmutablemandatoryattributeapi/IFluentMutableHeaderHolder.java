//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IHeaderHolder;

//interface
/**
 * A {@link IFluentMutableHeaderHolder} is a {@link IHeaderHolder} whose header can
 * be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-04-15
 * @param <FMHH> is the type of a {@link IFluentMutableHeaderHolder}.
 */
public interface IFluentMutableHeaderHolder<FMHH extends IHeaderHolder> extends IHeaderHolder {

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
