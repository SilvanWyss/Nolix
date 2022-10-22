//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class ItemMenuCSSRuleBuilder<
	IM extends ItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends ExtendedControlCSSRuleBuilder<IM, IMS> {
	
	//method
	@Override
	protected final void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final IM itemMenu,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected final void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final IM itemMenu,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
}
