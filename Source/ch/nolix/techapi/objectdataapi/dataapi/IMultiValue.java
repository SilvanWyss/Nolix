//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiValue<MV extends IMultiValue<MV, V>, V> extends IContainer<V>, IProperty<MV> {}
