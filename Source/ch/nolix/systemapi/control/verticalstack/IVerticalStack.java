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
  HorizontalContentAlignment getContentAlignment();

  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
