package ch.nolix.systemapi.webgui.linearcontainer;

import ch.nolix.systemapi.gui.box.VerticalContentAlignment;

public interface IHorizontalStack extends ILinearContainer<IHorizontalStack, IHorizontalStackStyle> {
  VerticalContentAlignment getContentAlignment();

  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
