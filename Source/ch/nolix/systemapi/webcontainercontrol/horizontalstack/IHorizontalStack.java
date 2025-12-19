package ch.nolix.systemapi.webcontainercontrol.horizontalstack;

import ch.nolix.systemapi.gui.box.VerticalContentAlignment;
import ch.nolix.systemapi.webcontainercontrol.linearcontainer.ILinearContainer;

/**
 * @author Silvan Wyss
 */
public interface IHorizontalStack extends ILinearContainer<IHorizontalStack, IHorizontalStackStyle> {
  VerticalContentAlignment getContentAlignment();

  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
