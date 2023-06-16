//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.webapi.cssapi.CSSAlignItemsCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSFloatCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.guiapi.structureproperty.VerticalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class HorizontalStackCSSRuleBuilder
extends ExtendedControlCSSRuleBuilder<IHorizontalStack, IHorizontalStackStyle> {
	
	//static attribute
	public static final HorizontalStackCSSRuleBuilder INSTANCE = new HorizontalStackCSSRuleBuilder();
	
	//constructor
	private HorizontalStackCSSRuleBuilder() {}

	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final IHorizontalStack horizontalStack,
		final LinkedList<? super ICSSRule<?>> list
	) {
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(horizontalStack) + " > div",
				LinkedList.withElements(
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.FLOAT,
						CSSFloatCatalogue.LEFT
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final IHorizontalStack horizontalStack,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(horizontalStack) + " > div",
				LinkedList.withElements(
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.MARGIN,
						horizontalStack.getOriStyle().getChildControlMarginWhenHasState(state)
						+ CSSUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final IHorizontalStack control,
		final LinkedList<CSSProperty> list
	) {
		list.addAtEnd(
			CSSProperty.withNameAndValue("display", "flex"),
			CSSProperty.withNameAndValue("overflow", "auto"),
			createCSSPropertyForContentAlignmentOfControl(control)
		);
	}

	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final IHorizontalStack horizontalStack,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	private CSSProperty createCSSPropertyForContentAlignment(final VerticalContentAlignment contentAlignment) {
		return
		switch (contentAlignment) {
			case TOP ->
				CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItemsCatalogue.START);
			case CENTER ->
				CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItemsCatalogue.CENTER);
			case BOTTOM ->
				CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItemsCatalogue.END);
		};
	}
	
	//method
	private CSSProperty createCSSPropertyForContentAlignmentOfControl(final IHorizontalStack control) {
		
		final var contentAlignment = control.getContentAlignment();
		
		return createCSSPropertyForContentAlignment(contentAlignment);
	}
}
