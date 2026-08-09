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
 * @param <G> the type of a {@link IGui}.
 */
public interface IGui<G extends IGui<G>> extends FluentMutableTitleHolder<G> {
  /**
   * @return the {@link IFrontEndReader} of the current {@link IGui}.
   */
  IFrontEndReader fromFrontEnd();

  /**
   * @return the icon of the current {@link IGui}.
   */
  Image getIcon();

  /**
   * @return the {@link IFrontEndWriter} of the current {@link IGui}.
   */
  IFrontEndWriter onFrontEnd();

  /**
   * Sets the icon of the current{@link IGui}.
   * 
   * @param icon
   * @return the current{@link IGui}.
   */
  G setIcon(Image icon);
}
