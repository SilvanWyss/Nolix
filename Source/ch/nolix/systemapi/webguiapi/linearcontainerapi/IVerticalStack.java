//package declaration
package ch.nolix.systemapi.webguiapi.linearcontainerapi;

//own imports
import ch.nolix.systemapi.guiapi.structureproperty.HorizontalContentAlignment;

//interface
public interface IVerticalStack extends ILinearContainer<IVerticalStack, IVerticalStackStyle> {

  // method declaration
  HorizontalContentAlignment getContentAlignment();

  // method declaration
  IVerticalStack setContentAlignment(HorizontalContentAlignment contentAlignment);
}
