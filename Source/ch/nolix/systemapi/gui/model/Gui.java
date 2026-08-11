/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTitleHolder;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;

/**
 * @author Silvan Wyss
 * @param <G> the type of a {@link Gui}.
 */
public interface Gui<G extends Gui<G>> extends FluentMutableTitleHolder<G> {
  /**
   * @return the {@link IFrontEndReader} of the current {@link Gui}.
   */
  IFrontEndReader fromFrontEnd();

  /**
   * @return the icon of the current {@link Gui}.
   */
  Image getIcon();

  /**
   * @return the {@link IFrontEndWriter} of the current {@link Gui}.
   */
  IFrontEndWriter onFrontEnd();

  /**
   * Sets the icon of the current{@link Gui}.
   * 
   * @param icon
   * @return the current{@link Gui}.
   */
  G setIcon(Image icon);
}
