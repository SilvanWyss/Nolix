package ch.nolix.systemapi.webgui.main;

public interface IUserInputCell<C extends IUserInputCell<C>> {

  String getUserInput();

  C setUserInput(String userInput);
}
