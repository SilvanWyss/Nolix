//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IProperty<P extends IProperty<P>> {
	
	//method declaration
	boolean canReference(IStructuralEntity<?, P> entity);
	
	//method declaration
	boolean canReferenceBack(IStructuralEntity<?, P> entity);
	
	//method declaration
	boolean canReferenceBackEntityOfType(Class<IStructuralEntity<?, P>> entityType);
	
	//method declaration
	boolean canReferenceEntityOfType(Class<IStructuralEntity<?, P>> entityType);
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean references(IStructuralEntity<?, P> entity);
	
	//method declaration
	boolean referencesBack(IStructuralEntity<?, P> entity);
}
