package ch.nolix.system.webgui.itemmenu;

import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public abstract class ItemMenuStyle<IMS extends IItemMenuStyle<IMS> & IMultiStateConfiguration<IMS, ControlState>>
extends ControlStyle<IMS>
implements IItemMenuStyle<IMS> {
}
