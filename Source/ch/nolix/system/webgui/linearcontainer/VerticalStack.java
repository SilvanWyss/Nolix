//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.html.HTMLElement;
import ch.nolix.core.document.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class VerticalStack extends LinearContainer {
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return
		HTMLElement.withTypeAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			getRefChildControls().to(IControl::toHTMLElement)
		);
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
