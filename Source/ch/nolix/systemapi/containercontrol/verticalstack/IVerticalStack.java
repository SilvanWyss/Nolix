/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.verticalstack;

import ch.nolix.systemapi.containercontrol.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.gui.box.HorizontalContentAlignment;

/**
 * @author Silvan Wyss
 */
public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {
  HorizontalContentAlignment getContentAlignment();

  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
