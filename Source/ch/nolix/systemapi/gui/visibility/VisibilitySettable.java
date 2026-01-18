/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.visibility;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link VisibilitySettable}.
 */
public interface VisibilitySettable<S extends VisibilitySettable<S>> extends VisibilityRequestable {
  S setInvisible();

  S setVisible();
}
