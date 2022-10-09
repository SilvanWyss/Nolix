//package declaration
package ch.nolix.system.webgui.controlstyle;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.CSSBorderStyleValueCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.system.webgui.main.ControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ExtendedControlCSSRuleCreator<
	EC extends IControl<EC, ECS>,
	ECS extends IExtendedControlStyle<ECS>
>
extends ControlCSSRuleCreator<EC, ECS> {
	
	//constructor
	protected ExtendedControlCSSRuleCreator(final EC parentExtendedControl) {
		super(parentExtendedControl);
	}
	
	//method
	@Override
	protected void fillUpControlCSSPropertiesForBaseStateIntoList(final LinkedList<CSSProperty> list) {
		//TODO: Implement.
	}
	
	//method
	@Override
	protected final void fillUpControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		
		final var style = getRefParentControl().getRefStyle();
		
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
				getColorCodeOfColor(style.getLeftBorderColorWhenHasState(state))
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
				getColorCodeOfColor(style.getRightBorderColorWhenHasState(state))
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
				getColorCodeOfColor(style.getTopBorderColorWhenHasState(state))
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
				getColorCodeOfColor(style.getBottomBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_BOTTOM,
				String.valueOf(style.getBottomPaddingWhenHasState(state)) + CSSUnitCatalogue.PX
			)
		);
		
		fillUpExtendedControlCSSPropertiesForStateIntoList(state, list);
	}
	
	//method declaration
	protected abstract void fillUpExtendedControlCSSPropertiesForStateIntoList(
		ControlState state,
		LinkedList<CSSProperty> list
	);
}
