//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class ItemMenuStyle<IMS extends IItemMenuStyle<IMS> & IMultiStateConfiguration<IMS, ControlState>>
extends ExtendedControlStyle<IMS>
implements IItemMenuStyle<IMS> {}
