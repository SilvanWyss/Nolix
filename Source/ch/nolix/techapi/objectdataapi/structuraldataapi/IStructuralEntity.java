//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;

//interface
public interface IStructuralEntity<SE extends IStructuralEntity<SE, P>,	P extends IProperty<P>> extends IEntity<SE> {
	
	//method declaration
	IContainer<P> getRefProperties();
}
