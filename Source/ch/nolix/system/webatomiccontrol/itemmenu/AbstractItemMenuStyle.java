package ch.nolix.system.webatomiccontrol.itemmenu;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public abstract class AbstractItemMenuStyle<S extends IItemMenuStyle<S> & IMultiStateConfiguration<S, ControlState>>
extends AbstractControlStyle<S>
implements IItemMenuStyle<S> {
}
