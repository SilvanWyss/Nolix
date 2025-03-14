package ch.nolix.system.webgui.itemmenu.base;

import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public abstract class ItemMenuStyle<S extends IItemMenuStyle<S> & IMultiStateConfiguration<S, ControlState>>
extends ControlStyle<S>
implements IItemMenuStyle<S> {
}
