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
	void assertIsNotBackReferenced(IEntity<?, ?> entity);
	
	//method declaration
	void assertIsNotReferenced(IEntity<?, ?> entity);
}
