package ch.nolix.systemapi.webatomiccontrol.itemmenu;

import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link IItemMenuStyle}.
 */
public interface IItemMenuStyle<S extends IItemMenuStyle<S>> extends IControlStyle<S> {
  //This interface is just an union of other interfaces.
}
