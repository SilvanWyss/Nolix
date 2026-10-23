/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.horizontalstack;

import ch.nolix.systemapi.control.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.gui.guiproperty.VerticalContentAlignment;

/**
 * @author Silvan Wyss
 */
public interface IHorizontalStack extends ILinearContainer<IHorizontalStack, IHorizontalStackStyle> {
  /**
   * @return the {@link VerticalContentAlignment} of the current
   *         {@link IHorizontalStack}
   */
  VerticalContentAlignment getContentAlignment();

  /**
   * Sets the given contentAlignment to the current {@link IHorizontalStack}.
   * 
   * @param contentAlignment
   * @return the current {@link IHorizontalStack}
   */
  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
