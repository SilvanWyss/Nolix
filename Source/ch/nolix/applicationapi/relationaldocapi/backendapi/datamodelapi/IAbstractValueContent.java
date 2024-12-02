package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;

public interface IAbstractValueContent extends IValueContent {

  IAbstractValueContent setDataType(DataType dataType);
}
