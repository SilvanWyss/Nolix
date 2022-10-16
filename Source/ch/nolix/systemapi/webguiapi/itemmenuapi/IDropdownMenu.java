//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//interface
public interface IDropdownMenu<
	DM extends IDropdownMenu<DM, DMS>,
	DMS extends IDropdownMenuStyle<DMS>
>
extends IItemMenu<DM, DMS> {}
