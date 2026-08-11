/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guiattribute;

import ch.nolix.systemapi.graphic.image.Image;

/**
 * A {@link FluentMutableIconHolder} is a {@link IconHolder} whose icon can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableIconHolder}
 */
public interface FluentMutableIconHolder<H extends FluentMutableIconHolder<H>> extends IconHolder {
  /**
   * Sets the icon of the current {@link FluentMutableIconHolder}.
   * 
   * @param icon
   * @return the current {@link FluentMutableIconHolder}
   * @throws RuntimeException if the given icon is null
   */
  H setIcon(Image icon);
}
