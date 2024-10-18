package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.systemapi.guiapi.contentalignmentproperty.VerticalContentAlignment;

public interface IHorizontalStack extends ILinearContainer<IHorizontalStack, IHorizontalStackStyle> {

  VerticalContentAlignment getContentAlignment();

  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
