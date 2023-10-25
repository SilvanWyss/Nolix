//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalHeadered;

//interface
/**
 * A {@link IFluentOptionalHeaderHolder} is a {@link OptionalHeadered} whose
 * header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-02-24
 * @param <FOHH> is the type of a {@link IFluentOptionalHeaderHolder}.
 */
public interface IFluentOptionalHeaderHolder<FOHH extends IFluentOptionalHeaderHolder<FOHH>> extends OptionalHeadered {

  //method declaration
  /**
   * Removes the header of current {@link IFluentOptionalHeaderHolder}.
   * 
   * @return the current {@link IFluentOptionalHeaderHolder}.
   */
  FOHH removeHeader();

  //method declaration
  /**
   * Sets the header of the current {@link IFluentOptionalHeaderHolder}.
   * 
   * @param header
   * @return the current {@link IFluentOptionalHeaderHolder}.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  FOHH setHeader(String header);
}
