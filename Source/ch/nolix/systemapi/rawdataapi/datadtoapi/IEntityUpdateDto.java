package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;

public interface IEntityUpdateDto {

  String getId();

  String getSaveStamp();

  IContainer<ContentFieldDto> getUpdatedContentFields();
}
