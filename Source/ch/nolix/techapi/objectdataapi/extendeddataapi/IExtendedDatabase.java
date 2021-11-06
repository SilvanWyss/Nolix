//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.databaseapi.extendeddatabaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IExtendedDatabase<
	ED extends IExtendedDatabase<ED, ET, EE, EP>,
	ET extends IExtendedTable<ET, EE, EP>,
	EE extends IExtendedEntity<EE, EP>,
	EP extends IExtendedProperty<EP>
> extends IDatabase<ED, ET, EE, EP>, IExtendedDatabaseObject {
	
	//method
	default IExtendedDatabase<ED, ET, EE, EP> addEntity(final EE entity) {
		
		getRefTableForEntity(entity).addEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedDatabase<ED, ET, EE, EP> addEntity(final EE... entities) {
		
		if (entities.length > 0) {
			getRefTableByEntityClass(entities[0].getClass()).addEntity(entities);
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
