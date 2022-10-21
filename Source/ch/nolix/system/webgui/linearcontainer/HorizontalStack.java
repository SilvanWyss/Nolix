//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;

//class
public final class HorizontalStack extends LinearContainer<HorizontalStack, HorizontalStackStyle> {
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return HorizontalStackHTMLCreator.INSTANCE.createHTMLElementForHorizontalStack(this);
	}
	
	//method
	@Override
	protected HorizontalStackStyle createStyle() {
		return new HorizontalStackStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<HorizontalStack, HorizontalStackStyle> getCSSRuleCreator() {
		return HorizontalStackCSSRuleBuilder.forHorizontalStack(this);
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
