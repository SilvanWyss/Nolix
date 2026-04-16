/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.horizontalstack;

import ch.nolix.systemapi.containercontrol.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.gui.box.VerticalContentAlignment;

/**
 * @author Silvan Wyss
 */
public interface IHorizontalStack extends ILinearContainer<IHorizontalStack, IHorizontalStackStyle> {
  VerticalContentAlignment getContentAlignment();

  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
