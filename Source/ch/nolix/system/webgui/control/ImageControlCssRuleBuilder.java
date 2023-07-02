//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ImageControlCssRuleBuilder extends ExtendedControlCssRuleBuilder<ImageControl, ImageControlStyle> {
	
	//static attribute
	public static final ImageControlCssRuleBuilder INSTANCE = new ImageControlCssRuleBuilder();
	
	//constructor
	private ImageControlCssRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final ImageControl imageControl,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final ImageControl imageControl,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final ImageControl control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final ImageControl imageControl,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		list.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalogue.DISPLAY, "block"));
	}
}
