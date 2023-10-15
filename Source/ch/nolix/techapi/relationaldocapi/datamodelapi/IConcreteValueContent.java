//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi.IFluentMutableMultiValueHolder;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IConcreteValueContent
    extends IFluentMutableMultiValueHolder<IConcreteValueContent, String>, IValueContent {

  // method declaration
  IConcreteValueContent setDataType(DataType dataType);
}
