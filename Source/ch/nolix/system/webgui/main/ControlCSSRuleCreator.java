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
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
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
	public IContainer<ICSSRule<?>> getCSSRules() {
		
		final var lCSSRules = new LinkedList<ICSSRule<?>>();
		
		lCSSRules.addAtEnd(
			getCSSRuleForBaseState(),
			getCSSRuleForState(ControlState.BASE),
			getCSSRuleForState(ControlState.HOVER),
			getCSSRuleForState(ControlState.FOCUS)
		);
		
		fillUpAdditionalCSSRulesForBaseStateIntoList(lCSSRules);
		fillUpAdditionalCSSRulesForStateIntoList(ControlState.BASE, lCSSRules);
		fillUpAdditionalCSSRulesForStateIntoList(ControlState.HOVER, lCSSRules);
		fillUpAdditionalCSSRulesForStateIntoList(ControlState.FOCUS, lCSSRules);
		
		return lCSSRules;
	}
	
	//method
	@Override
	public final C getRefParentControl() {
		return parentControl;
	}
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForStateIntoList(
		ControlState state,
		LinkedList<? super ICSSRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForBaseStateIntoList(LinkedList<? super ICSSRule<?>> list);
	
	//method declaration
	protected abstract void fillUpControlCSSPropertiesForBaseStateIntoList(LinkedList<CSSProperty> list);
	
	//method declaration
	protected abstract void fillUpControlCSSPropertiesForStateIntoList(
		ControlState state,
		LinkedList<CSSProperty> list
	);
	
	//method
	protected String getCSSSelectorForBaseState() {
		return "#" + getRefParentControl().getFixedId();
	}
	
	//method
	private void fillUpCSSPropertiesForBaseStateIntoList(final LinkedList<CSSProperty> list) {
		
		if (getRefParentControl().hasMaxWidth()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MAX_WIDTH,
					ControlCSSValueHelper.INSTANCE.getCSSValueFromRelativeOrAbsoluteInt(
						getRefParentControl().getMaxWidth(),
						CSSUnitCatalogue.VW
					)
				)
			);
		}
		
		if (getRefParentControl().hasMaxHeight()) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.MAX_HEIGHT,
					ControlCSSValueHelper.INSTANCE.getCSSValueFromRelativeOrAbsoluteInt(
						getRefParentControl().getMaxHeight(),
						CSSUnitCatalogue.VH
					)
				)
			);
		}
				
		if (getRefParentControl().getCursorIcon() != CursorIcon.ARROW) {
			list.addAtEnd(
				CSSProperty.withNameAndValue(
					CSSPropertyNameCatalogue.CURSOR,
					getRefParentControl().getCursorIcon().toCSSValue()
				)
			);
		}
		
		fillUpControlCSSPropertiesForBaseStateIntoList(list);
	}
	
	//method
	private void fillUpCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
				
		final var style = getRefParentControl().getRefStyle();
		
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
		
		fillUpControlCSSPropertiesForStateIntoList(state, list);
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForBaseState() {
		return CSSRule.withSelectorAndProperties(getCSSSelectorForBaseState(), getCSSPropertiesForBaseState());
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForState(final ControlState state) {
		return CSSRule.withSelectorAndProperties(getCSSSelectorForState(state), getCSSPropertiesForState(state));
	}
	
	//method
	private IContainer<CSSProperty> getCSSPropertiesForBaseState() {
		
		final var lCSSPropertiesForBaseState = new LinkedList<CSSProperty>();
		
		fillUpCSSPropertiesForBaseStateIntoList(lCSSPropertiesForBaseState);
		
		return lCSSPropertiesForBaseState;
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
