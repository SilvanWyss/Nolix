//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;

//class
public abstract class ItemMenuCSSRuleCreator<
	IM extends ItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends ExtendedControlCSSRuleCreator<IM, IMS> {
	
	//constructor
	protected ItemMenuCSSRuleCreator(final IM parentExtendedControl) {
		super(parentExtendedControl);
	}
	
	//method
	@Override
	protected final void fillUpAdditionalCSSRulesForStateIntoList(
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected final void fillUpAdditionalCSSRulesIntoList(final LinkedList<? super ICSSRule<?>> list) {
		//Does nothing.
	}
}
