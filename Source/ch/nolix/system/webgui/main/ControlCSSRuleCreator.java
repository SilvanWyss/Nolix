//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

//class
public abstract class ControlCSSRuleCreator<
	C extends IControl<C, CS>,
	CS extends IControlStyle<CS>
>
implements IControlCSSRuleCreator<C, CS> {
	
	//attribute
	private final C parentControl;
	
	//constructor
	protected ControlCSSRuleCreator(final C parentControl) {
		
		GlobalValidator.assertThat(parentControl).thatIsNamed("parent control").isNotNull();
		
		this.parentControl = parentControl;
	}
	
	//method
	@Override
	public final ICSSRule<?> getCSSRuleForState(ControlState state) {
		return CSSRule.withSelectorAndProperties(getCSSSelectorForState(state), getCSSPropertiesForState(state));
	}
	
	//method
	@Override
	public final C getRefParentControl() {
		return parentControl;
	}
	
	//method declaration
	protected abstract void fillUpControlCSSPropertiesForStateIntoList(
		ControlState state,
		LinkedList<CSSProperty> list
	);
	
	//method
	private void fillUpCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		
		if (getRefParentControl().getCursorIcon() != CursorIcon.ARROW) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.CURSOR,
					getRefParentControl().getCursorIcon().toCSSValue()
				)
			);
		}
		
		final var style = getRefParentControl().getRefStyle();
		
		list.addAtEnd(
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.COLOR,
				getColorCodeOfColor(style.getTextColorWhenHasState(state))
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.FONT_FAMILY,
				style.getFontWhenHasState(state).toString().toLowerCase() //TODO: Create toHTMLFont method.
			),
			CSSProperty.withNameAndValue(
				CSSPropertyNameCatalogue.FONT_SIZE,
				String.valueOf(style.getTextSizeWhenHasState(state)) + CSSUnitCatalogue.PX
			)
		);
		
		fillUpControlCSSPropertiesForStateIntoList(state, list);
	}
	
	//method
	private String getColorCodeOfColor(final IColor color) {
		return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
	}
	
	//method
	private IContainer<CSSProperty> getCSSPropertiesForState(final ControlState state) {
		
		final var lCSSProperties = new LinkedList<CSSProperty>();
		
		fillUpCSSPropertiesForStateIntoList(state, lCSSProperties);
		
		return lCSSProperties;
	}
	
	//method
	private String getCSSSelectorForState(final ControlState state) {
		switch (state) {
			case BASE:
				return ("#" + getRefParentControl().getFixedId());
			case FOCUS:
				return ("#" + getRefParentControl().getFixedId() + ":focus");
			case HOVER:
				return ("#" + getRefParentControl().getFixedId() + ":hover");
			default:
				throw InvalidArgumentException.forArgument(state);
		}
	}
}
