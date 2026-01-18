/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.component.guicomponent;

/**
 * A {@link IControlComponent} can belong to a control.
 * 
 * @author Silvan Wyss
 * @param <C> is the type of the control a {@link IControlComponent} can belong
 *            to.
 */
public interface IControlComponent<C> {
  /**
   * @return true if the current {@link IControlComponent} belongs to a control,
   *         false otherwise.
   */
  boolean belongsToControl();

  /**
   * @return the control of the current {@link IControlComponent}.
   * @throws RuntimeException if the current {@link IControlComponent} does not
   *                          belong to a control.
   */
  C getStoredParentControl();
}
