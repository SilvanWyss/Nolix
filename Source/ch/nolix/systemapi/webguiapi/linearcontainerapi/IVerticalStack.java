package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.systemapi.guiapi.contentalignmentproperty.HorizontalContentAlignment;

public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {

  HorizontalContentAlignment getContentAlignment();

  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
