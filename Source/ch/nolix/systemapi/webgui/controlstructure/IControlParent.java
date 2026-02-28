/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstructure;

import ch.nolix.baseapi.component.guicomponent.IControlComponent;
import ch.nolix.baseapi.component.guicomponent.IGuiComponent;
import ch.nolix.baseapi.component.guicomponent.ILayerComponent;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public interface IControlParent
extends IControlComponent<IControl<?, ?>>, IGuiComponent<IWebGui<?>>, ILayerComponent<ILayer<?>> {
  /**
   * @return the {@link IControl} the current {@link IControlParent} is.
   */
  IControl<?, ?> getStoredControl();

  /**
   * @return the {@link ILayer} the current {@link IControlParent} is.
   */
  ILayer<?> getStoredLayer();

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
