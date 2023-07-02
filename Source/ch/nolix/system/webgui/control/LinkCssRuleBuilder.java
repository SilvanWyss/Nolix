//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlapi.ILink;
import ch.nolix.systemapi.webguiapi.controlapi.ILinkStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class LinkCssRuleBuilder extends ExtendedControlCssRuleBuilder<ILink, ILinkStyle> {
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final ILink control,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final ILink control,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final ILink control,
		final LinkedList<CSSProperty> list
	) {
		list.addAtEnd(CSSProperty.withNameAndValue("text-decoration", "none"));
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final ILink control,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
}
