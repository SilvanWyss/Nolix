//package declaration
package ch.nolix.system.webgui.controlstyle;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.CssBorderStyleCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.system.webgui.controlhelper.ControlCssValueHelper;
import ch.nolix.system.webgui.main.ControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ExtendedControlCssRuleBuilder<
	EC extends IControl<EC, ECS>,
	ECS extends IControlStyle<ECS>
>
extends ControlCssRuleBuilder<EC, ECS> {
	
	//constant
	private static final ControlCssValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCssValueHelper(); 
	
	//method
	@Override
	protected final void fillUpCssPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		
		fillUpOptionalCssPropertiesForControlAndStateIntoList(control, state, list);
		
		fillUpMandatoryCssPropertiesForControlAndStateIntoList(control, state, list);
		
		fillUpCssPropertiesForExtendedControlAndStateIntoList(control, state, list);
	}
	
	//method declaration
	protected abstract void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		EC control,
		ControlState state,
		LinkedList<ICssProperty> list
	);
	
	//method
	private void fillUpMandatoryCssPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		
		var style = control.getStoredStyle();
		
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
				CONTROL_CSS_VALUE_HELPER.getCssValueFromColor(style.getLeftBorderColorWhenHasState(state))
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
				CONTROL_CSS_VALUE_HELPER.getCssValueFromColor(style.getRightBorderColorWhenHasState(state))
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
				CONTROL_CSS_VALUE_HELPER.getCssValueFromColor(style.getTopBorderColorWhenHasState(state))
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
				CONTROL_CSS_VALUE_HELPER.getCssValueFromColor(style.getBottomBorderColorWhenHasState(state))
			),
			CssProperty.withNameAndValue(
				CssPropertyNameCatalogue.PADDING_BOTTOM,
				String.valueOf(style.getBottomPaddingWhenHasState(state)) + CssUnitCatalogue.PX
			)
		);
		
		list.addAtEnd(style.getBackgroundWhenHasState(state).toCssProperties());
	}
	
	//method
	private void fillUpOptionalCssPropertiesForControlAndStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		
		var style = control.getStoredStyle();
		
		if (style.definesWidthForState(state)) {
			list.addAtEnd(
				CssProperty.withNameAndValue(
					CssPropertyNameCatalogue.WIDTH,
					CONTROL_CSS_VALUE_HELPER.getCssValueFromRelativeOrAbsoluteInt(
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
					CONTROL_CSS_VALUE_HELPER.getCssValueFromRelativeOrAbsoluteInt(
						style.getHeightForState(state),
						CssUnitCatalogue.VH
					)
				)
			);
		}
	}
}
