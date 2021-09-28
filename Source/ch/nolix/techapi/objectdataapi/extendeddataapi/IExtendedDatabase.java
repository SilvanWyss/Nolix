//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IExtendedDatabase<
	ED extends IExtendedDatabase<ED, ET, EE>,
	ET extends IExtendedTable<ET, EE>,
	EE extends IExtendedEntity<EE>
> extends IDatabase<ED, ET, EE>, IExtendedDatabaseObject {
	
	//method
	default IExtendedDatabase<ED, ET, EE> addEntity(final EE entity) {
		
		getRefTableForEntity(entity).addEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedDatabase<ED, ET, EE> addEntity(final EE... entities) {
		
		if (entities.length > 0) {
			getRefTableForEntity(entities[0]).addEntity(entities);
		}
		
		return this;
	}
	
	//method
	default ET getRefTableByEntityClass(final Class<?> entityClass) {
		return getRefTableByName(entityClass.getSimpleName());
	}
	
	//method
	private ET getRefTableForEntity(final EE entity) {
		return getRefTableByEntityClass(entity.getClass());
	}
}
