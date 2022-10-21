//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;

//class
public final class FloatContainer extends LinearContainer<FloatContainer, FloatContainerStyle> {
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return FloatContainerHTMLBuilder.INSTANCE.createHTMLElementForControl(this);
	}
	
	//method
	@Override
	protected FloatContainerStyle createStyle() {
		return new FloatContainerStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<FloatContainer, FloatContainerStyle> getCSSRuleCreator() {
		return FloatContainerCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
