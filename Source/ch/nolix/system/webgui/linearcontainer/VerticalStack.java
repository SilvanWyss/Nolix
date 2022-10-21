//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;

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
	protected IControlCSSRuleBuilder<VerticalStack, VerticalStackStyle> getCSSRuleCreator() {
		return VerticalStackCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
