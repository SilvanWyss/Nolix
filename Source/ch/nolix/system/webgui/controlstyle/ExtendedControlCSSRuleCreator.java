//package declaration
package ch.nolix.system.webgui.controlstyle;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
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
	protected final void fillUpControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		
		final var style = getRefParentControl().getRefStyle();
		
		list.addAtEnd(
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_WIDTH,
				style.getLeftBorderThicknessOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_LEFT_COLOR,
				"#" + style.getLeftBorderColorOfState(state).toAlphaRedGreenBlueValue()
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_LEFT,
				style.getLeftPaddingOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_RIGHT_WIDTH,
				style.getRightBorderThicknessOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_RIGHT_COLOR,
				"#" + style.getRightBorderColorOfState(state).toAlphaRedGreenBlueValue()
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_RIGHT,
				style.getRightPaddingOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_TOP_WIDTH,
				style.getTopBorderThicknessOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_TOP_COLOR,
				"#" + style.getTopBorderColorOfState(state).toAlphaRedGreenBlueValue()
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_TOP,
				style.getTopPaddingOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_BOTTOM_WIDTH,
				style.getBottomBorderThicknessOfState(state)
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.BORDER_BOTTOM_COLOR,
				"#" + style.getBottomBorderColorOfState(state).toAlphaRedGreenBlueValue()
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.PADDING_BOTTOM,
				style.getBottomPaddingOfState(state)
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
