//package declaration
package ch.nolix.system.webgui.controlstyle;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.CSSBorderStyleValueCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
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
	
	//method
	@Override
	protected void fillUpControlCSSPropertiesForBaseStateIntoList(final EC control, final LinkedList<CSSProperty> list) {
		//TODO: Implement.
	}
	
	//method
	@Override
	protected final void fillUpControlCSSPropertiesForStateIntoList(
		final EC control,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		
		final var style = control.getRefStyle();
		
		list.addAtEnd(
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_STYLE,
				CSSBorderStyleValueCatalogue.SOLID
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_WIDTH,
				String.valueOf(style.getLeftBorderThicknessWhenHasState(state)) + CSSUnitCatalogue.PX
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_COLOR,
				ControlCSSValueHelper.INSTANCE.getCSSValueFromColor(style.getLeftBorderColorWhenHasState(state))
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
				ControlCSSValueHelper.INSTANCE.getCSSValueFromColor(style.getRightBorderColorWhenHasState(state))
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
				ControlCSSValueHelper.INSTANCE.getCSSValueFromColor(style.getTopBorderColorWhenHasState(state))
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
				ControlCSSValueHelper.INSTANCE.getCSSValueFromColor(style.getBottomBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_BOTTOM,
				String.valueOf(style.getBottomPaddingWhenHasState(state)) + CSSUnitCatalogue.PX
			)
		);
		
		fillUpExtendedControlCSSPropertiesForStateIntoList(control, state, list);
	}
	
	//method declaration
	protected abstract void fillUpExtendedControlCSSPropertiesForStateIntoList(
		EC control,
		ControlState state,
		LinkedList<CSSProperty> list
	);
}
