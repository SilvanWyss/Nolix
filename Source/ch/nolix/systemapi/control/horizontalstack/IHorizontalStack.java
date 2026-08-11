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
  VerticalContentAlignment getContentAlignment();

  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
