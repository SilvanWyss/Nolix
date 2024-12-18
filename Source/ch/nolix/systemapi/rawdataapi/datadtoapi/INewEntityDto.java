package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;

public interface INewEntityDto {

  IContainer<ContentFieldDto> getContentFields();

  String getId();
}
