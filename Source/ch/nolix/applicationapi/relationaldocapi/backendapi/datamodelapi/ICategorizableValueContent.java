package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;

public interface ICategorizableValueContent extends IValueContent {

  ICategorizableValueContent setDataType(DataType dataType);
}
