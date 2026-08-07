/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.container;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link Container}.
 * @param <S> the type of the {@link ControlStyle} of a {@link Container}.
 */
public interface Container<C extends Container<C, S>, S extends ControlStyle<S>>
extends Clearable, Control<C, S> {
  ContainerRole getRole();

  boolean hasRole();

  void removeRole();

  C setRole(ContainerRole role);
}
