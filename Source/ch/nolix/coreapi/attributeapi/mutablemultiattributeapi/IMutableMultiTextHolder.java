//package declaration
package ch.nolix.coreapi.attributeapi.mutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTextHolder;

//interface
/**
 * A {@link IMutableMultiTextHolder} is a {@link IMultiTextHolder} whose texts
 * can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-10-25
 */
public interface IMutableMultiTextHolder extends IMultiTextHolder {

  //method declaration
  /**
   * Adds the given text to the current {@link IMutableMultiTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null.
   */
  void addText(String text);

  //method declaration
  /**
   * Removes the given text from the current {@link IMutableMultiTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the current {@link IMutableMultiTextHolder} does
   *                          not contain the given text.
   */
  void removeText(String text);

  //method declaration
  /**
   * Removes all texts from the current {@link IMutableMultiTextHolder}.
   */
  void removeTexts();
}