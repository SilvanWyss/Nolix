package ch.nolix.techapi.relationaldocapi.datamodelapi;

import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

public interface IAbstractValueContent extends IValueContent {

  IAbstractValueContent setDataType(DataType dataType);
}
