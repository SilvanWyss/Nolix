//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//interface
public interface IDropdownMenu<IMI extends IItemMenuItem>
extends IItemMenu<IDropdownMenu<IMI>, IMI, IDropdownMenuStyle> {
	
	//method declaration
	void collapsMenu();
	
	//method declaration
	void expandMenu();
	
	//method declaration
	boolean hasCollapsedMenu();
	
	//method declaration
	boolean hasExpandedMenu();
}
