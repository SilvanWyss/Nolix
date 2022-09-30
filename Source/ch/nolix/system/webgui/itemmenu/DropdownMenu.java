//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
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
	public void collapsMenu() {
		//TODO: Implement.
	}

	//method
	@Override
	public void expandMenu() {
		//TODO: Implement.
	}
	
	//method
	@Override
	public IControlCSSRuleCreator<DropdownMenu, DropdownMenuStyle> getCSSRuleCreator() {
		return mCSSRuleCreator;
	}
	
	//method
	@Override
	public boolean hasCollapsedMenu() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public boolean hasExpandedMenu() {
		//TODO: Implement.
		return false;
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
