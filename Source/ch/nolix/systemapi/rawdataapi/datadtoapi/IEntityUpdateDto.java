package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IEntityUpdateDto {

  String getId();

  String getSaveStamp();

  IContainer<IContentFieldDto> getUpdatedContentFields();
}
