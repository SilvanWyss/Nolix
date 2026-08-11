/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.model;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTitleHolder;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;
import ch.nolix.systemapi.gui.guiattribute.FluentMutableIconHolder;

/**
 * @author Silvan Wyss
 * @param <G> the type of a {@link Gui}
 */
public interface Gui<G extends Gui<G>> extends FluentMutableIconHolder<G>, FluentMutableTitleHolder<G> {
  /**
   * @return the {@link IFrontEndReader} of the current {@link Gui}
   */
  IFrontEndReader fromFrontEnd();

  /**
   * @return the {@link IFrontEndWriter} of the current {@link Gui}
   */
  IFrontEndWriter onFrontEnd();
}
