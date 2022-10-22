//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
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
	
	//method
	@Override
	public final IContainer<ICSSRule<?>> createCSSRulesForControl(final C control) {
		
		final var lCSSRules = new LinkedList<ICSSRule<?>>();
		
		fillUpCSSRulesForAllStatesOfControlIntoList(control, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.BASE, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.HOVER, lCSSRules);
		fillUpCSSRulesForStateOfControlIntoList(control, ControlState.FOCUS, lCSSRules);
		
		return lCSSRules;
	}
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForAllStatesOfControlIntoList(
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
	protected abstract void fillUpControlCSSPropertiesForAllStatesOfControlIntoList(
		C control,
		LinkedList<CSSProperty> list
	);
	
	//method declaration
	protected abstract void fillUpControlCSSPropertiesForControlAndStateIntoList(
		C control,
		ControlState state,
		LinkedList<CSSProperty> list
	);
	
	//method
	protected final String getCSSSelectorForAllStatesOfControl(final C control) {
		return "#" + control.getFixedId();
	}
	
	//method
	protected final String getCSSSelectorForControlAndState(final C control, final ControlState state) {
		switch (state) {
			case BASE:
				return ("#" + control.getFixedId());
			case FOCUS:
				return ("#" + control.getFixedId() + ":focus");
			case HOVER:
				return ("#" + control.getFixedId() + ":hover");
			default:
				throw InvalidArgumentException.forArgument(state);
		}
	}
	
	//method
	private void fillUpCSSPropertiesForAllStatesOfControlIntoList(final C control, final LinkedList<CSSProperty> list) {
		
		if (control.hasMaxWidth()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MAX_WIDTH,
					ControlCSSValueHelper.INSTANCE.getCSSValueFromRelativeOrAbsoluteInt(
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
					ControlCSSValueHelper.INSTANCE.getCSSValueFromRelativeOrAbsoluteInt(
						control.getMaxHeight(),
						CSSUnitCatalogue.VH
					)
				)
			);
		}
				
		if (control.getCursorIcon() != CursorIcon.ARROW) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.CURSOR,
					control.getCursorIcon().toCSSValue()
				)
			);
		}
		
		fillUpControlCSSPropertiesForAllStatesOfControlIntoList(control, list);
	}
	
	//method
	private void fillUpCSSPropertiesForControlAndStateIntoList(
		final C control,
		final ControlState state,
		final LinkedList<CSSProperty> list
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
				ControlCSSValueHelper.INSTANCE.getCSSValueFromColor(style.getTextColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.FONT_FAMILY,
				style.getFontWhenHasState(state).getCode().toLowerCase()
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.FONT_SIZE,
				String.valueOf(style.getTextSizeWhenHasState(state)) + CSSUnitCatalogue.PX
			)
		);
		
		fillUpControlCSSPropertiesForControlAndStateIntoList(control, state, list);
	}
	
	//method
	private void fillUpCSSRulesForAllStatesOfControlIntoList(final C control, final LinkedList<ICSSRule<?>> lCSSRules) {
		lCSSRules.addAtEnd(getCSSRuleForAllStatesOfControl(control));
		fillUpAdditionalCSSRulesForAllStatesOfControlIntoList(control, lCSSRules);
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
	private IContainer<CSSProperty> getCSSPropertiesForAllStatesOfControl(final C control) {
		
		final var lCSSPropertiesForBaseState = new LinkedList<CSSProperty>();
		
		fillUpCSSPropertiesForAllStatesOfControlIntoList(control, lCSSPropertiesForBaseState);
		
		return lCSSPropertiesForBaseState;
	}
	
	//method
	private IContainer<CSSProperty> getCSSPropertiesForControlAndState(final C control, final ControlState state) {
		
		final var lCSSProperties = new LinkedList<CSSProperty>();
		
		fillUpCSSPropertiesForControlAndStateIntoList(control, state, lCSSProperties);
		
		return lCSSProperties;
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForAllStatesOfControl(final C control) {
		return
		CSSRule.withSelectorAndProperties(
			getCSSSelectorForAllStatesOfControl(control),
			getCSSPropertiesForAllStatesOfControl(control)
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
