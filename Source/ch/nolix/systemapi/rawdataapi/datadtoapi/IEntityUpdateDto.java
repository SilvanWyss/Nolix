//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IEntityUpdateDto {

  //method declaration
  String getId();

  //method declaration
  String getSaveStamp();

  //method declaration
  IContainer<IContentFieldDto> getUpdatedContentFields();
}
