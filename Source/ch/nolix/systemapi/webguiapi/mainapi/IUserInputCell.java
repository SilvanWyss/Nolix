package ch.nolix.systemapi.webguiapi.mainapi;

public interface IUserInputCell<UIC extends IUserInputCell<UIC>> {

  String getUserInput();

  UIC setUserInput(String userInput);
}
