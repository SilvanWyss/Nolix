//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databasedtoapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface INewEntityDto {

  //method declaration
  IContainer<IContentFieldDto> getContentFields();

  //method declaration
  String getId();
}
