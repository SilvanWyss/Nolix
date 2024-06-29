//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITextHolder;

//interface
/**
 * A {@link IFluentMutableOptionalTextHolder} is a {@link ITextHolder} whose
 * text can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-02-06
 * @param <FMOTH> is the type of a {@link IFluentMutableOptionalTextHolder}.
 */
public interface IFluentMutableOptionalTextHolder<FMOTH extends IFluentMutableOptionalTextHolder<FMOTH>>
extends ITextHolder {

  //method declaration
  /**
   * Removes the text of the current {@link IFluentMutableOptionalTextHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalTextHolder}.
   */
  FMOTH removeText();

  //method declaration
  /**
   * Sets the text of the current {@link IFluentMutableOptionalTextHolder}.
   * 
   * @param text
   * @return the current {@link IFluentMutableOptionalTextHolder}.
   * @throws RuntimeException if the given text is null.
   */
  FMOTH setText(String text);
}
