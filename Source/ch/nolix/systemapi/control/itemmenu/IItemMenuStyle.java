/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.itemmenu;

import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link IItemMenuStyle}.
 */
public interface IItemMenuStyle<S extends IItemMenuStyle<S>> extends IControlStyle<S> {
  //This interface is a dedicated union of other interfaces.
}
