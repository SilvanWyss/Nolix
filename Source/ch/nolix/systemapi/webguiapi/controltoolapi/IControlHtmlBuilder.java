//package declaration
package ch.nolix.systemapi.webguiapi.controltoolapi;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlHtmlBuilder<C extends IControl<C, ?>> {

  //method declaration
  IHtmlElement createHtmlElementForControl(C control);
}
