package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.DataType;

public interface ICategorizableValueContent extends IValueContent {

  ICategorizableValueContent setDataType(DataType dataType);
}
