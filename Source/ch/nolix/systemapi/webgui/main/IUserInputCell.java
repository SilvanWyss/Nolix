package ch.nolix.systemapi.webgui.main;

/**
 * @author Silvan Wyss
 */
public interface IUserInputCell<C extends IUserInputCell<C>> {
  String getUserInput();

  C setUserInput(String userInput);
}
