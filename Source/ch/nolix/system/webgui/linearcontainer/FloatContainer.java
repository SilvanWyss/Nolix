//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class FloatContainer extends LinearContainer<FloatContainer, FloatContainerStyle> {
	
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
	protected IControlHTMLBuilder<FloatContainer> getHTMLBuilder() {
		return FloatContainerHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
