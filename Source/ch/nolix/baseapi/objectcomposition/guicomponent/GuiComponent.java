/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.guicomponent;

/**
 * A {@link GuiComponent} can belong to a GUI.
 * 
 * @author Silvan Wyss
 * @param <G> the type of the GUI a {@link GuiComponent} can belong to.
 */
public interface GuiComponent<G> {
  /**
   * @return true if the current {@link GuiComponent} belongs to a GUI, false
   *         otherwise
   */
  boolean belongsToGui();

  /**
   * @return the GUI of the current {@link GuiComponent}
   * @throws RuntimeException if the current {@link GuiComponent} does not belong
   *                          to a GUI
   */
  G getStoredParentGui();
}
