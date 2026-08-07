/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.itemmenu;

import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link IItemMenuStyle}.
 */
public interface IItemMenuStyle<S extends IItemMenuStyle<S>> extends ControlStyle<S> {
  // This interface is a dedicated union of other interfaces.
}
