//package declaration
package ch.nolix.system.webgui.main;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlhelper.ControlCssValueHelper;
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
	
	//constant
	private static final ControlCssValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCssValueHelper(); 
	
	//method
	@Override
	public final IContainer<ICssRule<?>> createCssRulesForControl(final C control) {
		
		final var cssRules = new LinkedList<ICssRule<?>>();
		
		fillUpCssRulesForControlAndAllStatesIntoList(control, cssRules);
		fillUpCssRulesForStateOfControlIntoList(control, ControlState.BASE, cssRules);
		fillUpCssRulesForStateOfControlIntoList(control, ControlState.HOVER, cssRules);
		fillUpCssRulesForStateOfControlIntoList(control, ControlState.FOCUS, cssRules);
		
		return cssRules;
	}
	
	//method declaration
	protected abstract void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		C control,
		LinkedList<? super ICssRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpAdditionalCssRulesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<? super ICssRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpCssPropertiesForControlAndAllStatesIntoList(
		C control,
		LinkedList<CssProperty> list
	);
	
	//method declaration
	protected abstract void fillUpCssPropertiesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<ICssProperty> list
	);
	
	//method
	protected final String getCssSelectorForControlAndAllStates(final C control) {
		return "#" + control.getInternalId();
	}
	
	//method
	protected final String getCssSelectorForControlAndState(final C control, final ControlState state) {
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
	private void fillUpAllCssPropertiesForControlAndAllStatesIntoList(
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
					CONTROL_CSS_VALUE_HELPER.getCssValueFromRelativeOrAbsoluteInt(
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
					CONTROL_CSS_VALUE_HELPER.getCssValueFromRelativeOrAbsoluteInt(
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
					CONTROL_CSS_VALUE_HELPER.getCssValueFromRelativeOrAbsoluteInt(
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
					CONTROL_CSS_VALUE_HELPER.getCssValueFromRelativeOrAbsoluteInt(
						control.getMaxHeight(),
						CssUnitCatalogue.VH
					)
				)
			);
		}
		
		list.addAtEnd(
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.CURSOR,
				control.getCursorIcon().toCssValue()
			)
		);
		
		fillUpCssPropertiesForControlAndAllStatesIntoList(control, list);
	}

	//method
	private void fillUpAllCssPropertiesForControlAndStateIntoList(
		final C control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
				
		final var style = control.getStoredStyle();
		
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
				CONTROL_CSS_VALUE_HELPER.getCssValueFromColor(style.getTextColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.FONT_FAMILY,
				style.getFontWhenHasState(state).getCode().toLowerCase(Locale.ENGLISH)
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.FONT_SIZE,
				String.valueOf(style.getTextSizeWhenHasState(state)) + CssUnitCatalogue.PX
			),
			getFontWeightCssPropertyForControlAndState(control, state)
		);
		
		fillUpCssPropertiesForControlAndStateIntoList(control, state, list);
	}
	
	//method
	private void fillUpCssRulesForControlAndAllStatesIntoList(final C control, final LinkedList<ICssRule<?>> cssRules) {
		cssRules.addAtEnd(getCssRuleForControlAndAllStates(control));
		fillUpAdditionalCssRulesForControlAndAllStatesIntoList(control, cssRules);
	}
	
	//method
	private void fillUpCssRulesForStateOfControlIntoList(
		final C control,
		final ControlState state,
		final LinkedList<ICssRule<?>> cssRules
	) {
		cssRules.addAtEnd(getCssRuleForControlAndState(control, state));
		fillUpAdditionalCssRulesForControlAndStateIntoList(control, state, cssRules);
	}
	
	//method
	private IContainer<CssProperty> getCssPropertiesForControlAndAllStates(final C control) {
		
		final var cssPropertiesForBaseState = new LinkedList<CssProperty>();
		
		fillUpAllCssPropertiesForControlAndAllStatesIntoList(control, cssPropertiesForBaseState);
		
		return cssPropertiesForBaseState;
	}
	
	//method
	private IContainer<ICssProperty> getCssPropertiesForControlAndState(final C control, final ControlState state) {
		
		final var cssProperties = new LinkedList<ICssProperty>();
		
		fillUpAllCssPropertiesForControlAndStateIntoList(control, state, cssProperties);
		
		return cssProperties;
	}
	
	//method
	private final ICssRule<?> getCssRuleForControlAndAllStates(final C control) {
		return
		CssRule.withSelectorAndProperties(
			getCssSelectorForControlAndAllStates(control),
			getCssPropertiesForControlAndAllStates(control)
		);
	}
	
	//method
	private final ICssRule<?> getCssRuleForControlAndState(final C control, final ControlState state) {
		return CssRule.withSelectorAndProperties(
			getCssSelectorForControlAndState(control, state),
			getCssPropertiesForControlAndState(control, state)
		);
	}
	
	//method
	private ICssProperty getFontWeightCssPropertyForControlAndState(final C control, final ControlState state) {
		
		final var style = control.getStoredStyle();
		final var boldTextFlag = style.getBoldTextFlagWhenHasState(state);
		
		if (!boldTextFlag) {
			return CssProperty.withNameAndValue("font-weight", "normal");
		}
		
		return CssProperty.withNameAndValue("font-weight", "bold");
	}
}
