//package declaration
package ch.nolix.system.webgui.main;

//Java imports
import java.util.Locale;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlCssRuleBuilder<
	C extends IControl<C, CS>,
	CS extends IControlStyle<CS>
>
implements IControlCssRuleBuilder<C, CS> {
	
	//static attribute
	private static final ControlCSSValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCSSValueHelper(); 
	
	//method
	@Override
	public final IContainer<ICssRule<?>> createCSSRulesForControl(final C control) {
		
		final var lCSSRules = new LinkedList<ICssRule<?>>();
		
		fillUpCSSRulesForControlAndAllStatesIntoList(control, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.BASE, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.HOVER, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.FOCUS, lCSSRules);
		
		return lCSSRules;
	}
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		C control,
		LinkedList<? super ICssRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<? super ICssRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		C control,
		LinkedList<CssProperty> list
	);
	
	//method declaration
	protected abstract void fillUpCSSPropertiesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<ICssProperty> list
	);
	
	//method
	protected final String getCSSSelectorForControlAndAllStates(final C control) {
		return "#" + control.getInternalId();
	}
	
	//method
	protected final String getCSSSelectorForControlAndState(final C control, final ControlState state) {
		return
		switch (state) {
			case BASE ->
				"#" + control.getInternalId();
			case FOCUS ->
				"#" + control.getInternalId() + ":focus";
			case HOVER ->
				"#" + control.getInternalId() + ":hover";
			default ->
				throw InvalidArgumentException.forArgument(state);
		};
	}
	
	//method
	private void fillUpAllCSSPropertiesForControlAndAllStatesIntoList(
		final C control,
		final LinkedList<CssProperty> list
	) {
		
		switch (control.getPresence()) {
			case VISIBLE:
				// Does nothing. Since presence is configured for all states, the Control will be visible per default.
				break;
			case INVISIBLE:
				list.addAtEnd(CssProperty.withNameAndValue("visibility", "hidden"));
				break;
			case COLLAPSED:
				list.addAtEnd(CssProperty.withNameAndValue("display", "none"));
		}
		
		if (control.hasMinWidth()) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.MIN_WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMinWidth(),
						CssUnitCatalogue.VW
					)
				)
			);
		}
		
		if (control.hasMinHeight()) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.MIN_HEIGHT,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMinHeight(),
						CssUnitCatalogue.VH
					)
				)
			);
		}
		
		if (control.hasMaxWidth()) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.MAX_WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMaxWidth(),
						CssUnitCatalogue.VW
					)
				)
			);
		}
		
		if (control.hasMaxHeight()) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.MAX_HEIGHT,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMaxHeight(),
						CssUnitCatalogue.VH
					)
				)
			);
		}
		
		list.addAtEnd(
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.CURSOR,
				control.getCursorIcon().toCSSValue()
			)
		);
		
		fillUpCSSPropertiesForControlAndAllStatesIntoList(control, list);
	}

	//method
	private void fillUpAllCSSPropertiesForControlAndStateIntoList(
		final C control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
				
		final var style = control.getOriStyle();
		
		final var opacity = style.getOpacityWhenHasState(state);
		if (opacity < 1.0) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.OPACITY,
					opacity
				)
			);
		}
		
		list.addAtEnd(
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getTextColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.FONT_FAMILY,
				style.getFontWhenHasState(state).getCode().toLowerCase(Locale.ENGLISH)
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.FONT_SIZE,
				String.valueOf(style.getTextSizeWhenHasState(state)) + CssUnitCatalogue.PX
			),
			getFontWeightCSSPropertyForControlAndState(control, state)
		);
		
		fillUpCSSPropertiesForControlAndStateIntoList(control, state, list);
	}
	
	//method
	private void fillUpCSSRulesForControlAndAllStatesIntoList(final C control, final LinkedList<ICssRule<?>> lCSSRules) {
		lCSSRules.addAtEnd(getCSSRuleForControlAndAllStates(control));
		fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(control, lCSSRules);
	}
	
	//method
	private void fillUpCSSRulesForStateOfControlIntoList(
		final C control,
		final ControlState state,
		final LinkedList<ICssRule<?>> lCSSRules
	) {
		lCSSRules.addAtEnd(getCSSRuleForControlAndState(control, state));
		fillUpAdditionalCSSRulesForControlAndStateIntoList(control, state, lCSSRules);
	}
	
	//method
	private IContainer<CssProperty> getCSSPropertiesForControlAndAllStates(final C control) {
		
		final var lCSSPropertiesForBaseState = new LinkedList<CssProperty>();
		
		fillUpAllCSSPropertiesForControlAndAllStatesIntoList(control, lCSSPropertiesForBaseState);
		
		return lCSSPropertiesForBaseState;
	}
	
	//method
	private IContainer<ICssProperty> getCSSPropertiesForControlAndState(final C control, final ControlState state) {
		
		final var lCSSProperties = new LinkedList<ICssProperty>();
		
		fillUpAllCSSPropertiesForControlAndStateIntoList(control, state, lCSSProperties);
		
		return lCSSProperties;
	}
	
	//method
	private final ICssRule<?> getCSSRuleForControlAndAllStates(final C control) {
		return
		CssRule.withSelectorAndProperties(
			getCSSSelectorForControlAndAllStates(control),
			getCSSPropertiesForControlAndAllStates(control)
		);
	}
	
	//method
	private final ICssRule<?> getCSSRuleForControlAndState(final C control, final ControlState state) {
		return CssRule.withSelectorAndProperties(
			getCSSSelectorForControlAndState(control, state),
			getCSSPropertiesForControlAndState(control, state)
		);
	}
	
	//method
	private ICssProperty getFontWeightCSSPropertyForControlAndState(final C control, final ControlState state) {
		
		final var style = control.getOriStyle();
		final var boldTextFlag = style.getBoldTextFlagWhenHasState(state);
		
		if (!boldTextFlag) {
			return CssProperty.withNameAndValue("font-weight", "normal");
		}
		
		return CssProperty.withNameAndValue("font-weight", "bold");
	}
}
