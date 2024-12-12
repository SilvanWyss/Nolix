package ch.nolix.systemapi.webguiapi.mainapi;

public interface IUserInputCell<C extends IUserInputCell<C>> {

  String getUserInput();

  C setUserInput(String userInput);
}
