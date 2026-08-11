/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.guicomponent;

/**
 * A {@link ControlComponent} can belong to a control.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the control a {@link ControlComponent} can belong to.
 */
public interface ControlComponent<C> {
  /**
   * @return true if the current {@link ControlComponent} belongs to a control,
   *         false otherwise
   */
  boolean belongsToControl();

  /**
   * @return the control of the current {@link ControlComponent}
   * @throws RuntimeException if the current {@link ControlComponent} does not
   *                          belong to a control
   */
  C getStoredParentControl();
}
