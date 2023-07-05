//package declaration
package ch.nolix.systemtest.webguitest.itemmenutest;

//own imports
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemtest.webguitest.controltest.ControlTest;

//class
public abstract class ItemMenuTest<
	IM extends IItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends ControlTest<IM> {}
