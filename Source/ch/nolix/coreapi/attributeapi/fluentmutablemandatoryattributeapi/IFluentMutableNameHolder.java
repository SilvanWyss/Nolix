//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//interface
/**
 * A {@link IFluentMutableNameHolder} is a {@link INameHolder} whose name can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <FMNH> is the type of a {@link IFluentMutableNameHolder}.
 */
public interface IFluentMutableNameHolder<FMNH extends IFluentMutableNameHolder<FMNH>> extends INameHolder {

  //method declaration
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
