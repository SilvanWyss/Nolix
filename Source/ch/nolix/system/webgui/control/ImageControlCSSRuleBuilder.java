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
	
	//static method
	public static ImageControlCSSRuleBuilder forImageControl(final ImageControl imageControl) {
		return new ImageControlCSSRuleBuilder(imageControl);
	}
	
	//constructor
	private ImageControlCSSRuleBuilder(final ImageControl imageControl) {
		super(imageControl);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForStateIntoList(
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForBaseStateIntoList(final LinkedList<? super ICSSRule<?>> list) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		list.addAtEnd(CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.DISPLAY, "block"));
	}
}
