//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IMultiValue<P extends IProperty<P>, V> extends IContainer<V>, IBaseValue<P, V> {}
