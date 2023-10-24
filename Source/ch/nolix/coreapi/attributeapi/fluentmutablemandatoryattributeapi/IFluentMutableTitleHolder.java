//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Titled;

//interface
/**
 * A {@link IFluentMutableTitleHolder} is a {@link Titled} whose title can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 * @param <FMTH> is the type of a {@link IFluentMutableTitleHolder}.
 */
public interface IFluentMutableTitleHolder<FMTH extends IFluentMutableTitleHolder<FMTH>> extends Titled {

  //method declaration
  /**
   * Sets the title of the current {@link IFluentMutableTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentMutableTitleHolder}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  FMTH setTitle(String title);
}
