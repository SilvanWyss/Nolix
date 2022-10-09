//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

//class
public final class VerticalStack extends LinearContainer<VerticalStack, VerticalStackStyle> {
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return VerticalStackHTMLCreator.INSTANCE.createHTMLElementForVerticalStack(this);
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
