package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.DataType;
import ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi.IFluentMutableMultiValueHolder;

public interface IConcreteValueContent
extends IFluentMutableMultiValueHolder<IConcreteValueContent, String>, IValueContent {

  IConcreteValueContent setDataType(DataType dataType);
}
