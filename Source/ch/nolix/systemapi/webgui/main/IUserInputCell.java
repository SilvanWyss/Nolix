/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link IUserInputCell}.
 */
public interface IUserInputCell<C extends IUserInputCell<C>> {
  /**
   * @return the user input of the current {@link IUserInputCell}
   */
  String getUserInput();

  /**
   * Sets the given userInput to the current {@link IUserInputCell}.
   * 
   * @param userInput
   * @return the current {@link IUserInputCell}
   */
  C setUserInput(String userInput);
}
