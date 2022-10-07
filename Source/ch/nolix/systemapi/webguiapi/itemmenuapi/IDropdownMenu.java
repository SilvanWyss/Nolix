//package declaration
package ch.nolix.systemapi.webguiapi.itemmenuapi;

//interface
public interface IDropdownMenu<
	DM extends IDropdownMenu<DM, DMS, IMI>,
	DMS extends IDropdownMenuStyle<DMS>,
	IMI extends IItemMenuItem<IMI>
>
extends IItemMenu<DM, DMS, IMI> {}
