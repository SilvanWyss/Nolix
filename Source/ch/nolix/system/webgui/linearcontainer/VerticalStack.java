//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

//class
public final class VerticalStack extends LinearContainer<VerticalStack, VerticalStackStyle> {
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(this)),
			getRefChildControls().to(IControl::toHTMLElement)
		);
	}
	
	//method
	@Override
	protected VerticalStackStyle createStyle() {
		return new VerticalStackStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleCreator<VerticalStack, VerticalStackStyle> getCSSRuleCreator() {
		return VerticalStackCSSRuleCreator.forVerticalStack(this);
	}
		
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
