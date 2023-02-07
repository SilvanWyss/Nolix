//package declaration
package ch.nolix.system.webgui.main;

//Java imports
import java.util.Locale;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlCSSRuleBuilder<
	C extends IControl<C, CS>,
	CS extends IControlStyle<CS>
>
implements IControlCSSRuleBuilder<C, CS> {
	
	//static attribute
	private static final ControlCSSValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCSSValueHelper(); 
	
	//method
	@Override
	public final IContainer<ICSSRule<?>> createCSSRulesForControl(final C control) {
		
		final var lCSSRules = new LinkedList<ICSSRule<?>>();
		
		fillUpCSSRulesForControlAndAllStatesIntoList(control, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.BASE, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.HOVER, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.FOCUS, lCSSRules);
		
		return lCSSRules;
	}
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		C control,
		LinkedList<? super ICSSRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<? super ICSSRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		C control,
		LinkedList<CSSProperty> list
	);
	
	//method declaration
	protected abstract void fillUpCSSPropertiesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<ICSSProperty> list
	);
	
	//method
	protected final String getCSSSelectorForControlAndAllStates(final C control) {
		return "#" + control.getFixedId();
	}
	
	//method
	protected final String getCSSSelectorForControlAndState(final C control, final ControlState state) {
		return
		switch (state) {
			case BASE ->
				"#" + control.getFixedId();
			case FOCUS ->
				"#" + control.getFixedId() + ":focus";
			case HOVER ->
				"#" + control.getFixedId() + ":hover";
			default ->
				throw InvalidArgumentException.forArgument(state);
		};
	}
	
	//method
	private void fillUpAllCSSPropertiesForControlAndAllStatesIntoList(
		final C control,
		final LinkedList<CSSProperty> list
	) {
		
		switch (control.getPresence()) {
			case VISIBLE:
				// Does nothing. Since presence is configured for all states, the Control will be visible per default.
				break;
			case INVISIBLE:
				list.addAtEnd(CSSProperty.withNameAndValue("visibility", "hidden"));
				break;
			case COLLAPSED:
				list.addAtEnd(CSSProperty.withNameAndValue("display", "none"));
		}
		
		if (control.hasMinWidth()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MIN_WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMinWidth(),
						CSSUnitCatalogue.VW
					)
				)
			);
		}
		
		if (control.hasMinHeight()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MIN_HEIGHT,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMinHeight(),
						CSSUnitCatalogue.VH
					)
				)
			);
		}
		
		if (control.hasMaxWidth()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MAX_WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMaxWidth(),
						CSSUnitCatalogue.VW
					)
				)
			);
		}
		
		if (control.hasMaxHeight()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MAX_HEIGHT,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMaxHeight(),
						CSSUnitCatalogue.VH
					)
				)
			);
		}
		
		list.addAtEnd(
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.CURSOR,
				control.getCursorIcon().toCSSValue()
			)
		);
		
		fillUpCSSPropertiesForControlAndAllStatesIntoList(control, list);
	}

	//method
	private void fillUpAllCSSPropertiesForControlAndStateIntoList(
		final C control,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
				
		final var style = control.getRefStyle();
		
		final var opacity = style.getOpacityWhenHasState(state);
		if (opacity < 1.0) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.OPACITY,
					opacity
				)
			);
		}
		
		list.addAtEnd(
			
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getTextColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.FONT_FAMILY,
				style.getFontWhenHasState(state).getCode().toLowerCase(Locale.ENGLISH)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.FONT_SIZE,
				String.valueOf(style.getTextSizeWhenHasState(state)) + CSSUnitCatalogue.PX
			)
		);
		
		fillUpCSSPropertiesForControlAndStateIntoList(control, state, list);
	}
	
	//method
	private void fillUpCSSRulesForControlAndAllStatesIntoList(final C control, final LinkedList<ICSSRule<?>> lCSSRules) {
		lCSSRules.addAtEnd(getCSSRuleForControlAndAllStates(control));
		fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(control, lCSSRules);
	}
	
	//method
	private void fillUpCSSRulesForStateOfControlIntoList(
		final C control,
		final ControlState state,
		final LinkedList<ICSSRule<?>> lCSSRules
	) {
		lCSSRules.addAtEnd(getCSSRuleForControlAndState(control, state));
		fillUpAdditionalCSSRulesForControlAndStateIntoList(control, state, lCSSRules);
	}
	
	//method
	private IContainer<CSSProperty> getCSSPropertiesForControlAndAllStates(final C control) {
		
		final var lCSSPropertiesForBaseState = new LinkedList<CSSProperty>();
		
		fillUpAllCSSPropertiesForControlAndAllStatesIntoList(control, lCSSPropertiesForBaseState);
		
		return lCSSPropertiesForBaseState;
	}
	
	//method
	private IContainer<ICSSProperty> getCSSPropertiesForControlAndState(final C control, final ControlState state) {
		
		final var lCSSProperties = new LinkedList<ICSSProperty>();
		
		fillUpAllCSSPropertiesForControlAndStateIntoList(control, state, lCSSProperties);
		
		return lCSSProperties;
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForControlAndAllStates(final C control) {
		return
		CSSRule.withSelectorAndProperties(
			getCSSSelectorForControlAndAllStates(control),
			getCSSPropertiesForControlAndAllStates(control)
		);
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForControlAndState(final C control, final ControlState state) {
		return CSSRule.withSelectorAndProperties(
			getCSSSelectorForControlAndState(control, state),
			getCSSPropertiesForControlAndState(control, state)
		);
	}
}
