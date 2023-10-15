//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//interface
public interface IUserInputCell<UIC extends IUserInputCell<UIC>> {

  // method declaration
  String getUserInput();

  // method declaration
  UIC setUserInput(String userInput);
}
