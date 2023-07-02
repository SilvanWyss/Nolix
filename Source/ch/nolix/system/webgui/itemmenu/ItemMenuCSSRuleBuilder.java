//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class ItemMenuCSSRuleBuilder<
	IM extends ItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends ExtendedControlCssRuleBuilder<IM, IMS> {
	
	//method
	@Override
	protected final void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final IM itemMenu,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected final void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final IM itemMenu,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
}
