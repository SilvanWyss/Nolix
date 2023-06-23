//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class VerticalStack extends LinearContainer<VerticalStack, VerticalStackStyle> {
	
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
	protected IControlHtmlBuilder<VerticalStack> getHTMLBuilder() {
		return VerticalStackHtmlBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
