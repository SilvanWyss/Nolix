package ch.nolix.systemapi.webgui.linearcontainer;

import ch.nolix.systemapi.gui.contentalignmentproperty.HorizontalContentAlignment;

public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {

  HorizontalContentAlignment getContentAlignment();

  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
