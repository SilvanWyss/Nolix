//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

//class
public final class DropdownMenu
extends ItemMenu<DropdownMenu, DropdownMenuStyle>
implements IDropdownMenu<DropdownMenu, DropdownMenuStyle, ItemMenuItem> {
	
	//attribute
	private final DropdownMenuCSSRuleCreator mCSSRuleCreator = DropdownMenuCSSRuleCreator.forDropdownMenu(this);
			
	//method
	@Override
	public IControlCSSRuleCreator<DropdownMenu, DropdownMenuStyle> getCSSRuleCreator() {
		return mCSSRuleCreator;
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalTypeScriptHTMLElementTakerInputGetter() {
		return new SingleContainer<>(
			"(select) => {if (select.selectedIndex == -1) {return '';} select.options[select.selectedIndex].text;}"
		);
	}
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return DropdownMenuHTMLCreator.INSTANCE.createHTMLElementForDropdownMenu(this);
	}
	
	//method
	@Override
	protected DropdownMenuStyle createStyle() {
		return new DropdownMenuStyle();
	}
}
