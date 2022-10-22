//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ImageControlCSSRuleBuilder extends ExtendedControlCSSRuleBuilder<ImageControl, ImageControlStyle> {
	
	//static attribute
	public static final ImageControlCSSRuleBuilder INSTANCE = new ImageControlCSSRuleBuilder();
	
	//consturctor
	private ImageControlCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final ImageControl imageControl,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForAllStatesOfControlIntoList(
		final ImageControl imageControl,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final ImageControl imageControl,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		list.addAtEnd(CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.DISPLAY, "block"));
	}
}
