//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class FloatContainer extends LinearContainer<FloatContainer, FloatContainerStyle> {
	
	//constant
	private static final FloatContainerHtmlBuilder HTML_BUILDER = new FloatContainerHtmlBuilder();
	
	//constant
	private static final FloatContainerCssBuilder CSS_RULE_BUILDER = new FloatContainerCssBuilder();
	
	//method
	@Override
	protected FloatContainerStyle createStyle() {
		return new FloatContainerStyle();
	}
	
	//method
	@Override
	protected IControlCssBuilder<FloatContainer, FloatContainerStyle> getCssRuleCreator() {
		return CSS_RULE_BUILDER;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<FloatContainer> getHtmlBuilder() {
		return HTML_BUILDER;
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
