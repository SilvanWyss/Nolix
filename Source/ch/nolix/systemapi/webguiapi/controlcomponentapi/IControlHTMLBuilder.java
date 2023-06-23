//package declaration
package ch.nolix.systemapi.webguiapi.controlcomponentapi;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlHTMLBuilder<C extends IControl<C, ?>> {
	
	//method declaration
	IHtmlElement<?, ?> createHTMLElementForControl(C control);
}
