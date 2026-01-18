/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webcontainercontrol.verticalstack;

import ch.nolix.systemapi.gui.box.HorizontalContentAlignment;
import ch.nolix.systemapi.webcontainercontrol.linearcontainer.ILinearContainer;

/**
 * @author Silvan Wyss
 */
public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {
  HorizontalContentAlignment getContentAlignment();

  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
