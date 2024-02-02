//package declaration
package ch.nolix.systemapi.webguiapi.controltoolapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlAnalyser {

  //method declaration
  boolean firstControlContainsSecondControl(IControl<?, ?> firstControl, IControl<?, ?> secondControl);
}
