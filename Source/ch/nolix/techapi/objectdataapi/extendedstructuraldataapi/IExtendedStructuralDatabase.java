//package declaration
package ch.nolix.techapi.objectdataapi.extendedstructuraldataapi;

//own imports
import ch.nolix.techapi.objectdataapi.structuraldataapi.IProperty;
import ch.nolix.techapi.objectdataapi.structuraldataapi.IStructuralDatabase;

//interface
public interface IExtendedStructuralDatabase<
	ESD extends IExtendedStructuralDatabase<ESD, EST, ESE, P>,
	EST extends IExtendedStructuralTable<EST, ESE, P>,
	ESE extends IExtendedStructuralEntity<ESE, P>,
	P extends IProperty<P>
> extends IStructuralDatabase<ESD, EST, ESE, P> {
	
	//method
	default IExtendedStructuralDatabase<ESD, EST, ESE, P> addEntity(final ESE entity) {
		
		getRefTableForEntity(entity).addEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedStructuralDatabase<ESD, EST, ESE, P> addEntity(final ESE... entities) {
		
		if (entities.length > 0) {
			getRefTableByEntityClass(entities[0].getClass()).addEntity(entities);
		}
		
		return this;
	}
	
	//method
	default EST getRefTableByEntityClass(final Class<?> entityClass) {
		return getRefTableByName(entityClass.getSimpleName());
	}
	
	//method
	private EST getRefTableForEntity(final ESE entity) {
		return getRefTableByEntityClass(entity.getClass());
	}
}
