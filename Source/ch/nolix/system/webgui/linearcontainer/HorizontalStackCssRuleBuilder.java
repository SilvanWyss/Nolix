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
import ch.nolix.system.webgui.controlcssbuilder.ControlCssBuilder;
import ch.nolix.systemapi.guiapi.structureproperty.VerticalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class HorizontalStackCssRuleBuilder
extends ControlCssBuilder<IHorizontalStack, IHorizontalStackStyle> {
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final IHorizontalStack horizontalStack,
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCssSelectorForControlAndAllStates(horizontalStack) + " > div",
				LinkedList.withElement(
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
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final IHorizontalStack horizontalStack,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCssSelectorForControlAndAllStates(horizontalStack) + " > div",
				LinkedList.withElement(
					CssProperty.withNameAndValue(
						CssPropertyNameCatalogue.MARGIN_RIGHT,
						horizontalStack.getStoredStyle().getChildControlMarginWhenHasState(state)
						+ CssUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final IHorizontalStack control,
		final LinkedList<CssProperty> list
	) {
		list.addAtEnd(
			CssProperty.withNameAndValue("display", "flex"),
			CssProperty.withNameAndValue("overflow", "auto"),
			createCssPropertyForContentAlignmentOfControl(control)
		);
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final IHorizontalStack horizontalStack,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	private CssProperty createCssPropertyForContentAlignment(final VerticalContentAlignment contentAlignment) {
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
	private CssProperty createCssPropertyForContentAlignmentOfControl(final IHorizontalStack control) {
		
		final var contentAlignment = control.getContentAlignment();
		
		return createCssPropertyForContentAlignment(contentAlignment);
	}
}
