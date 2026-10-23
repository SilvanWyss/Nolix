/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.verticalstack;

import ch.nolix.systemapi.control.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.gui.guiproperty.HorizontalContentAlignment;

/**
 * @author Silvan Wyss
 */
public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {
  /**
   * @return the {@link HorizontalContentAlignment} of the current
   *         {@link IVerticalStack}
   */
  HorizontalContentAlignment getContentAlignment();

  /**
   * Sets the given contentAlignment to the current {@link IVerticalStack}.
   * 
   * @param contentAlignment
   * @return the current {@link IVerticalStack}
   */
  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
