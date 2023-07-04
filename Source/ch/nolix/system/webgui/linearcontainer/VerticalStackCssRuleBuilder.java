//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.CssAlignItemsCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.guiapi.structureproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class VerticalStackCssRuleBuilder
extends ExtendedControlCssRuleBuilder<IVerticalStack, IVerticalStackStyle> {
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final IVerticalStack verticalStack,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCssSelectorForControlAndAllStates(verticalStack) + " > div",
				LinkedList.withElements(
					CssProperty.withNameAndValue(
						CssPropertyNameCatalogue.MARGIN_BOTTOM,
						verticalStack.getOriStyle().getChildControlMarginWhenHasState(state)
						+ CssUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final IVerticalStack verticalStack,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final IVerticalStack control,
		final LinkedList<CssProperty> list
	) {
		list.addAtEnd(
			CssProperty.withNameAndValue("display", "flex"),
			CssProperty.withNameAndValue("flex-direction", "column"),
			createCssPropertyForContentAlignmentOfControl(control)
		);
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final IVerticalStack verticalStack,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	private CssProperty createCssPropertyForContentAlignment(final HorizontalContentAlignment contentAlignment) {
		return
		switch (contentAlignment) {
			case LEFT ->
				CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.START);
			case CENTER ->
				CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.CENTER);
			case RIGHT ->
				CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.END);
		};
	}
	
	//method
	private CssProperty createCssPropertyForContentAlignmentOfControl(final IVerticalStack control) {
		
		final var contentAlignment = control.getContentAlignment();
		
		return createCssPropertyForContentAlignment(contentAlignment);
	}
}
