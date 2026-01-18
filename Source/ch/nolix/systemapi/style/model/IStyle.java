/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.model;

/**
 * @author Silvan Wyss
 */
public interface IStyle extends IBaseStyle<IStyle> {
  /**
   * @param style
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         style added.
   * @throws RuntimeException if the given style is null.
   */
  IStyle withStyle(IStyle style);
}
