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
public final class HorizontalStack extends LinearContainer<HorizontalStack, HorizontalStackStyle> {
	
	//method
	@Override
	public IControlCSSRuleCreator<HorizontalStack, HorizontalStackStyle> getCSSRuleCreator() {
		return HorizontalStackCSSRuleCreator.forHorizontalStack(this);
	}
	
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
	protected HorizontalStackStyle createStyle() {
		return new HorizontalStackStyle();
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
