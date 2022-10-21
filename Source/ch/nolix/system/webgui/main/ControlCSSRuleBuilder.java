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
		
		lCSSRules.addAtEnd(
			getCSSRuleForBaseState(control),
			getCSSRuleForState(control, ControlState.BASE),
			getCSSRuleForState(control, ControlState.HOVER),
			getCSSRuleForState(control, ControlState.FOCUS)
		);
		
		fillUpAdditionalCSSRulesForBaseStateIntoList(control, lCSSRules);
		fillUpAdditionalCSSRulesForStateIntoList(control, ControlState.BASE, lCSSRules);
		fillUpAdditionalCSSRulesForStateIntoList(control, ControlState.HOVER, lCSSRules);
		fillUpAdditionalCSSRulesForStateIntoList(control, ControlState.FOCUS, lCSSRules);
		
		return lCSSRules;
	}
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForStateIntoList(
		C control,
		ControlState state,
		LinkedList<? super ICSSRule<?>> list
	);
	
	//method declaration
	protected abstract void fillUpAdditionalCSSRulesForBaseStateIntoList(C control, LinkedList<? super ICSSRule<?>> list);
	
	//method declaration
	protected abstract void fillUpControlCSSPropertiesForBaseStateIntoList(C control, LinkedList<CSSProperty> list);
	
	//method declaration
	protected abstract void fillUpControlCSSPropertiesForStateIntoList(
		C control,
		ControlState state,
		LinkedList<CSSProperty> list
	);
	
	//method
	protected String getCSSSelectorForBaseState(final C control) {
		return "#" + control.getFixedId();
	}
	
	//method
	private void fillUpCSSPropertiesForBaseStateIntoList(final C control, final LinkedList<CSSProperty> list) {
		
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
		
		fillUpControlCSSPropertiesForBaseStateIntoList(control, list);
	}
	
	//method
	private void fillUpCSSPropertiesForStateIntoList(
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
		
		fillUpControlCSSPropertiesForStateIntoList(control, state, list);
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForBaseState(final C control) {
		return CSSRule.withSelectorAndProperties(getCSSSelectorForBaseState(control), getCSSPropertiesForBaseState(control));
	}
	
	//method
	private final ICSSRule<?> getCSSRuleForState(final C control, final ControlState state) {
		return CSSRule.withSelectorAndProperties(
			getCSSSelectorForState(control, state),
			getCSSPropertiesForState(control, state)
		);
	}
	
	//method
	private IContainer<CSSProperty> getCSSPropertiesForBaseState(final C control) {
		
		final var lCSSPropertiesForBaseState = new LinkedList<CSSProperty>();
		
		fillUpCSSPropertiesForBaseStateIntoList(control, lCSSPropertiesForBaseState);
		
		return lCSSPropertiesForBaseState;
	}
	
	//method
	private IContainer<CSSProperty> getCSSPropertiesForState(final C control, final ControlState state) {
		
		final var lCSSProperties = new LinkedList<CSSProperty>();
		
		fillUpCSSPropertiesForStateIntoList(control, state, lCSSProperties);
		
		return lCSSProperties;
	}
	
	//method
	private String getCSSSelectorForState(final C control, final ControlState state) {
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
}
