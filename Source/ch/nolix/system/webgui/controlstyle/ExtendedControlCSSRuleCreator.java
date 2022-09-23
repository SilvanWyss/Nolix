//package declaration
package ch.nolix.system.webgui.controlstyle;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.system.webgui.main.ControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
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
	protected final void fillUpControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		
		final var style = getRefParentControl().getRefStyle();
		
		list.addAtEnd(
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_WIDTH,
				style.getLeftBorderThicknessWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_COLOR,
				getColorCodeOfColor(style.getLeftBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_LEFT,
				style.getLeftPaddingWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_RIGHT_WIDTH,
				style.getRightBorderThicknessWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_RIGHT_COLOR,
				getColorCodeOfColor(style.getRightBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_RIGHT,
				style.getRightPaddingWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_TOP_WIDTH,
				style.getTopBorderThicknessWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_TOP_COLOR,
				getColorCodeOfColor(style.getTopBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_TOP,
				style.getTopPaddingWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_BOTTOM_WIDTH,
				style.getBottomBorderThicknessWhenHasState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_BOTTOM_COLOR,
				getColorCodeOfColor(style.getBottomBorderColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_BOTTOM,
				style.getBottomPaddingWhenHasState(state)
			)
		);
		
		fillUpExtendedControlCSSPropertiesForStateIntoList(state, list);
	}
	
	//method declaration
	protected abstract void fillUpExtendedControlCSSPropertiesForStateIntoList(
		ControlState state,
		LinkedList<CSSProperty> list
	);
	
	//method
	private String getColorCodeOfColor(final IColor color) {
		return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
	}
}
