/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.container;

import ch.nolix.baseapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link IContainer}.
 * @param <S> is the type of the {@link IControlStyle} of a {@link IContainer}.
 */
public interface IContainer<C extends IContainer<C, S>, S extends IControlStyle<S>>
extends Clearable, Control<C, S> {
  ContainerRole getRole();

  boolean hasRole();

  void removeRole();

  C setRole(ContainerRole role);
}
