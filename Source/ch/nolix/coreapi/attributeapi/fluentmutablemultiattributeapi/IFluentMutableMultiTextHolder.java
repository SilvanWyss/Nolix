//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTextHolder;

//interface
/**
 * A {@link IFluentMutableMultiTextHolder} is a {@link MultiTexted} whose texts
 * can be added and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @date 2023-10-25
 * @param <FMMTH> is the type of a {@link IFluentMutableMultiTextHolder}.
 */
public interface IFluentMutableMultiTextHolder<FMMTH extends IFluentMutableMultiTextHolder<FMMTH>>
extends IMultiTextHolder {

  //method declaration
  /**
   * Adds the given text to the current {@link IFluentMutableMultiTextHolder}.
   * 
   * @param text
   * @return the current {@link IFluentMutableMultiTextHolder}.
   * @throws RuntimeException if the given text is null or blank.
   */
  FMMTH addText(String text);

  //method declaration
  /**
   * Removes the text that equals the given text from the current
   * {@link IFluentMutableMultiTextHolder} if the current
   * {@link IFluentMutableMultiTextHolder} contains such a text.
   * 
   * @param text
   * @return the current {@link IFluentMutableMultiTextHolder}.
   */
  FMMTH removeText(String text);

  //method declaration
  /**
   * Removes all texts from the current {@link IFluentMutableMultiTextHolder}.
   * 
   * @return the current {@link IFluentMutableMultiTextHolder}.
   */
  FMMTH removeTexts();
}
