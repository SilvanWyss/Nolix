package ch.nolix.systemapi.webcontainercontrol.verticalstack;

import ch.nolix.systemapi.gui.box.HorizontalContentAlignment;
import ch.nolix.systemapi.webcontainercontrol.linearcontainer.ILinearContainer;

public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {
  HorizontalContentAlignment getContentAlignment();

  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
