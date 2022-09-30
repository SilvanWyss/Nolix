//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;

//class
public abstract class ItemMenuStyle<IMS extends ItemMenuStyle<IMS>>
extends ExtendedControlStyle<IMS>
implements IItemMenuStyle<IMS> {}
