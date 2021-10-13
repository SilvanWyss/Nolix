//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiValue<MV extends IMultiValue<MV, V>, V> extends IContainer<V>, IProperty<MV> {}
