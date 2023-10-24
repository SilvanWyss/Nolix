//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;

//interface
/**
 * A {@link IFluentMutableIdHolder} is a {@link Identified} whose id can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @param <FMIH> is the type of a {@link IFluentMutableIdHolder}.
 */
public interface IFluentMutableIdHolder<FMIH> extends Identified {

  //method declaration
  /**
   * Sets the id of the current {@link IFluentMutableIdHolder}.
   * 
   * @param id
   * @return the current {@link IFluentMutableIdHolder}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  FMIH setId(String id);
}
