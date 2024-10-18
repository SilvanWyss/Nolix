package ch.nolix.techapi.relationaldocapi.datamodelapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi.IFluentMutableMultiValueHolder;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

public interface IConcreteValueContent
extends IFluentMutableMultiValueHolder<IConcreteValueContent, String>, IValueContent {

  IConcreteValueContent setDataType(DataType dataType);
}
