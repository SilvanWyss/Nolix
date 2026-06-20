/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstructure;

import ch.nolix.baseapi.component.guicomponent.ControlComponent;
import ch.nolix.baseapi.component.guicomponent.GuiComponent;
import ch.nolix.baseapi.component.guicomponent.LayerComponent;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public interface IControlParent
extends ControlComponent<IControl<?, ?>>, GuiComponent<IWebGui<?>>, LayerComponent<ILayer> {
  /**
   * @return the {@link IControl} the current {@link IControlParent} is.
   */
  IControl<?, ?> getStoredControl();

  /**
   * @return the {@link ILayer} the current {@link IControlParent} is.
   */
  ILayer getStoredLayer();

  /**
   * @return true if the current {@link IControlParent} is a {@link IControl},
   *         false otherwise.
   */
  boolean isControl();

  /**
   * @return true if the current {@link IControlParent} is a {@link ILayer}, false
   *         otherwise.
   */
  boolean isLayer();
}
