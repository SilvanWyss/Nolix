//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ILoadedEntityDto {

  //method declaration
  IContainer<ILoadedContentFieldDto> getContentFields();

  //method declaration
  String getId();

  //method declaration
  String getSaveStamp();
}
