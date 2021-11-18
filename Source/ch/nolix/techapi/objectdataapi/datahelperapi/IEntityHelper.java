//package declaration
package ch.nolix.techapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;

//interface
public interface IEntityHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertBelongsToTable(IEntity<?, ?> entity);
	
	//method declaration
	void assertCanBeDeleted(IEntity<?, ?> entity);
	
	//method declaration
	void assertIsNotBackReferenced(IEntity<?, ?> entity);
	
	//method declaration
	void assertIsNotReferenced(IEntity<?, ?> entity);
	
	//method
	boolean canBeAddedToTable(IEntity<?, ?> entity);
	
	//method declaration
	boolean canBeDeleted(IEntity<?, ?> entity);
	
	//method declaration
	boolean isReferenced(IEntity<?, ?> entity);
	
	//method declaration
	boolean isReferencedInLocalData(IEntity<?, ?> entity);
}
