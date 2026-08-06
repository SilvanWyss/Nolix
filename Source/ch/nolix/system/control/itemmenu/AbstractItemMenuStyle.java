/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.itemmenu;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.itemmenu.IItemMenuStyle;
import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link AbstractItemMenuStyle}.
 */
public abstract class AbstractItemMenuStyle<S extends IItemMenuStyle<S> & IMultiStateConfiguration<S, ControlState>>
extends AbstractControlStyle<S>
implements IItemMenuStyle<S> {
  // This class is a sub class without additional methods.
}
