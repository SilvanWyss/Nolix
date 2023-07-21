//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class ItemMenuCssRuleBuilder<
	IM extends IItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends ControlCssRuleBuilder<IM, IMS> {
	
	//method
	@Override
	protected final void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final IM itemMenu,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected final void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final IM itemMenu,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
}
