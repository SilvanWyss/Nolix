//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
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
}
