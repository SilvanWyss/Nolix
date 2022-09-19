//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
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
