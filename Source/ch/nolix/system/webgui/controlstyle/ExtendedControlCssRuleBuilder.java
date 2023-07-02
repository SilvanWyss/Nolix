//package declaration
package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.CssBorderStyleCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.system.webgui.main.ControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ExtendedControlCssRuleBuilder<
	EC extends IControl<EC, ECS>,
	ECS extends IExtendedControlStyle<ECS>
>
extends ControlCssRuleBuilder<EC, ECS> {
	
	//static attribute
	private static final ControlCSSValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCSSValueHelper(); 
		
	//method
	@Override
	protected final void fillUpCSSPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		
		fillUpOptionalCSSPropertiesForControlAndStateIntoList(control, state, list);
		
		fillUpMandatoryCSSPropertiesForControlAndStateIntoList(control, state, list);
		
		fillUpCSSPropertiesForExtendedControlAndStateIntoList(control, state, list);
	}
	
	//method declaration
	protected abstract void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		EC control,
		ControlState state,
		LinkedList<ICssProperty> list
	);
	
	//method
	private void fillUpMandatoryCSSPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		
		var style = control.getOriStyle();
		
		list.addAtEnd(
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_STYLE,
				CssBorderStyleCatalogue.SOLID
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_LEFT_WIDTH,
				String.valueOf(style.getLeftBorderThicknessWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_LEFT_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getLeftBorderColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.PADDING_LEFT,
				String.valueOf(style.getLeftPaddingWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_RIGHT_WIDTH,
				String.valueOf(style.getRightBorderThicknessWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_RIGHT_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getRightBorderColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.PADDING_RIGHT,
				String.valueOf(style.getRightPaddingWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_TOP_WIDTH,
				String.valueOf(style.getTopBorderThicknessWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_TOP_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getTopBorderColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.PADDING_TOP,
				String.valueOf(style.getTopPaddingWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_BOTTOM_WIDTH,
				String.valueOf(style.getBottomBorderThicknessWhenHasState(state)) + CssUnitCatalogue.PX
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.BORDER_BOTTOM_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getBottomBorderColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.PADDING_BOTTOM,
				String.valueOf(style.getBottomPaddingWhenHasState(state)) + CssUnitCatalogue.PX
			)
		);
		
		list.addAtEnd(style.getBackgroundWhenHasState(state).toCSSProperties());
	}
	
	//method
	private void fillUpOptionalCSSPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		
		var style = control.getOriStyle();
		
		if (style.definesWidthForState(state)) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						style.getWidthForState(state),
						CssUnitCatalogue.VW
					)
				)
			);
		}
		
		if (style.definesHeightForState(state)) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.HEIGHT,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						style.getHeightForState(state),
						CssUnitCatalogue.VH
					)
				)
			);
		}
	}
}
