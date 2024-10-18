package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ILoadedEntityDto {

  IContainer<ILoadedContentFieldDto> getContentFields();

  String getId();

  String getSaveStamp();
}
