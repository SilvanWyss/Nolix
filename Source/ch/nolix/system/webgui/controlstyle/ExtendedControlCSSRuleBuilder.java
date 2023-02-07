//package declaration
package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.CSSBorderStyleCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.system.webgui.main.ControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ExtendedControlCSSRuleBuilder<
	EC extends IControl<EC, ECS>,
	ECS extends IExtendedControlStyle<ECS>
>
extends ControlCSSRuleBuilder<EC, ECS> {
	
	//static attribute
	private static final ControlCSSValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCSSValueHelper(); 
		
	//method
	@Override
	protected final void fillUpCSSPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		
		fillUpOptionalCSSPropertiesForControlAndStateIntoList(control, state, list);
		
		fillUpMandatoryCSSPropertiesForControlAndStateIntoList(control, state, list);
		
		fillUpCSSPropertiesForExtendedControlAndStateIntoList(control, state, list);
	}
	
	//method declaration
	protected abstract void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		EC control,
		ControlState state,
		LinkedList<ICSSProperty> list
	);
	
	//method
	private void fillUpMandatoryCSSPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		
		var style = control.getRefStyle();
		
		list.addAtEnd(
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_STYLE,
				CSSBorderStyleCatalogue.SOLID
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_WIDTH,
				String.valueOf(style.getLeftBorderThicknessWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getLeftBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_LEFT,
				String.valueOf(style.getLeftPaddingWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_RIGHT_WIDTH,
				String.valueOf(style.getRightBorderThicknessWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_RIGHT_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getRightBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_RIGHT,
				String.valueOf(style.getRightPaddingWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_TOP_WIDTH,
				String.valueOf(style.getTopBorderThicknessWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_TOP_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getTopBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_TOP,
				String.valueOf(style.getTopPaddingWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_BOTTOM_WIDTH,
				String.valueOf(style.getBottomBorderThicknessWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_BOTTOM_COLOR,
				CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(style.getBottomBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_BOTTOM,
				String.valueOf(style.getBottomPaddingWhenHasState(state)) + CSSUnitCatalogue.PX
			)
		);
		
		list.addAtEnd(style.getBackgroundWhenHasState(state).toCSSProperties());
	}
	
	//method
	private void fillUpOptionalCSSPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		
		var style = control.getRefStyle();
		
		if (style.definesWidthForState(state)) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						style.getWidthForState(state),
						CSSUnitCatalogue.VW
					)
				)
			);
		}
		
		if (style.definesHeightForState(state)) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.HEIGHT,
					CONTROL_CSS_VALUE_HELPER.getCSSValueFromRelativeOrAbsoluteInt(
						style.getHeightForState(state),
						CSSUnitCatalogue.VH
					)
				)
			);
		}
	}
}
