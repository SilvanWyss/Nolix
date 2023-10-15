//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITextHolder;

//interface
/**
 * A {@link IMutableTextHolder} is a {@link ITextHolder} whose text can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableTextHolder extends ITextHolder {

  // method declaration
  /**
   * Sets the text of the current {@link IMutableTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null.
   */
  void setText(String text);
}
