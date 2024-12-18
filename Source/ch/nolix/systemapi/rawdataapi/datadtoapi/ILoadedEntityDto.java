package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;

public interface ILoadedEntityDto {

  IContainer<ContentFieldDto<Object>> getContentFields();

  String getId();

  String getSaveStamp();
}
