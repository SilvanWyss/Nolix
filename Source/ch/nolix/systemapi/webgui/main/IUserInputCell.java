/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link IUserInputCell}.
 */
public interface IUserInputCell<C extends IUserInputCell<C>> {
  String getUserInput();

  C setUserInput(String userInput);
}
