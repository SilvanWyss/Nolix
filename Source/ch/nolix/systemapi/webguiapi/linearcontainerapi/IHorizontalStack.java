//package declaration
package ch.nolix.systemapi.webguiapi.linearcontainerapi;

//own imports
import ch.nolix.systemapi.guiapi.contentalignmentproperty.VerticalContentAlignment;

//interface
public interface IHorizontalStack extends ILinearContainer<IHorizontalStack, IHorizontalStackStyle> {

  //method declaration
  VerticalContentAlignment getContentAlignment();

  //method declaration
  IHorizontalStack setContentAlignment(VerticalContentAlignment contentAlignment);
}
