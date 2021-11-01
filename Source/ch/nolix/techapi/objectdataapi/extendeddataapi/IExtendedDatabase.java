//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;

//interface
public interface IExtendedDatabase<
	ED extends IExtendedDatabase<ED, ET, EE, P>,
	ET extends IExtendedTable<ET, EE, P>,
	EE extends IExtendedEntity<EE, P>,
	P extends IProperty<P>
> extends IDatabase<ED, ET, EE, P>, IExtendedDatabaseObject {
	
	//method
	default IExtendedDatabase<ED, ET, EE, P> addEntity(final EE entity) {
		
		getRefTableForEntity(entity).addEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedDatabase<ED, ET, EE, P> addEntity(final EE... entities) {
		
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
