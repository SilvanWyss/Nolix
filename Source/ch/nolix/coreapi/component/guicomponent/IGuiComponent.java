/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.component.guicomponent;

/**
 * A {@link IGuiComponent} can belong to a GUI.
 * 
 * @author Silvan Wyss
 * @param <G> is the type of the GUI a {@link IGuiComponent} can belong to.
 */
public interface IGuiComponent<G> {
  /**
   * @return true if the current {@link IGuiComponent} belongs to a GUI, false
   *         otherwise.
   */
  boolean belongsToGui();

  /**
   * @return the GUI of the current {@link IGuiComponent}.
   * @throws RuntimeException if the current {@link IGuiComponent} does not belong
   *                          to a GUI.
   */
  G getStoredParentGui();
}
