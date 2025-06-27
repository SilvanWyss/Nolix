package ch.nolix.system.webgui.itemmenu.base;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public abstract class AbstractItemMenuStyle<S extends IItemMenuStyle<S> & IMultiStateConfiguration<S, ControlState>>
extends AbstractControlStyle<S>
implements IItemMenuStyle<S> {
}
