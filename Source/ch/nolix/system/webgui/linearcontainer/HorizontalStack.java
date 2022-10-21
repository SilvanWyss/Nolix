//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class HorizontalStack extends LinearContainer<HorizontalStack, HorizontalStackStyle> {
	
	//method
	@Override
	protected HorizontalStackStyle createStyle() {
		return new HorizontalStackStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<HorizontalStack, HorizontalStackStyle> getCSSRuleCreator() {
		return HorizontalStackCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHTMLBuilder<HorizontalStack> getHTMLBuilder() {
		return HorizontalStackHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		//Does nothing.
	}
}
