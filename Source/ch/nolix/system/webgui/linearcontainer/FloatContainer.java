//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

//class
public final class FloatContainer extends LinearContainer<FloatContainer, FloatContainerStyle> {
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return FloatContainerHTMLCreator.INSTANCE.createHTMLElementForFloatContainer(this);
	}
	
	//method
	@Override
	protected FloatContainerStyle createStyle() {
		return new FloatContainerStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleCreator<FloatContainer, FloatContainerStyle> getCSSRuleCreator() {
		return FloatContainerCSSRuleCreator.forFloatContainer(this);
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
