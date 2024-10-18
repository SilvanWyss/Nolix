package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface INewEntityDto {

  IContainer<IContentFieldDto> getContentFields();

  String getId();
}
