/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import ch.nolix.baseapi.objectcomposition.guicomponent.ControlComponent;
import ch.nolix.baseapi.objectcomposition.guicomponent.GuiComponent;
import ch.nolix.baseapi.objectcomposition.guicomponent.LayerComponent;

/**
 * @author Silvan Wyss
 */
public interface IControlParent
extends ControlComponent<Control<?, ?>>, GuiComponent<IWebGui<?>>, LayerComponent<ILayer> {
  /**
   * @return the {@link Control} the current {@link IControlParent} is.
   */
  Control<?, ?> getStoredControl();

  /**
   * @return the {@link ILayer} the current {@link IControlParent} is.
   */
  ILayer getStoredLayer();

  /**
   * @return true if the current {@link IControlParent} is a {@link Control},
   *         false otherwise
   */
  boolean isControl();

  /**
   * @return true if the current {@link IControlParent} is a {@link ILayer}, false
   *         otherwise
   */
  boolean isLayer();
}
