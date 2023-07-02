//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.CssAlignItemsCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssFloatCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.guiapi.structureproperty.VerticalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class HorizontalStackCssRuleBuilder
extends ExtendedControlCssRuleBuilder<IHorizontalStack, IHorizontalStackStyle> {
	
	//static attribute
	public static final HorizontalStackCssRuleBuilder INSTANCE = new HorizontalStackCssRuleBuilder();
	
	//constructor
	private HorizontalStackCssRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final IHorizontalStack horizontalStack,
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(horizontalStack) + " > div",
				LinkedList.withElements(
					CssProperty.withNameAndValue(
						CssPropertyNameCatalogue.FLOAT,
						CssFloatCatalogue.LEFT
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
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(horizontalStack) + " > div",
				LinkedList.withElements(
					CssProperty.withNameAndValue(
						CssPropertyNameCatalogue.MARGIN_RIGHT,
						horizontalStack.getOriStyle().getChildControlMarginWhenHasState(state)
						+ CssUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final IHorizontalStack control,
		final LinkedList<CssProperty> list
	) {
		list.addAtEnd(
			CssProperty.withNameAndValue("display", "flex"),
			CssProperty.withNameAndValue("overflow", "auto"),
			createCSSPropertyForContentAlignmentOfControl(control)
		);
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final IHorizontalStack horizontalStack,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	private CssProperty createCSSPropertyForContentAlignment(final VerticalContentAlignment contentAlignment) {
		return
		switch (contentAlignment) {
			case TOP ->
				CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.START);
			case CENTER ->
				CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.CENTER);
			case BOTTOM ->
				CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.END);
		};
	}
	
	//method
	private CssProperty createCSSPropertyForContentAlignmentOfControl(final IHorizontalStack control) {
		
		final var contentAlignment = control.getContentAlignment();
		
		return createCSSPropertyForContentAlignment(contentAlignment);
	}
}
