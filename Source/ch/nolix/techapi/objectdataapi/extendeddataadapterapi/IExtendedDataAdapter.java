//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataadapterapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedDatabase;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedEntity;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedProperty;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedTable;

//interface
public interface IExtendedDataAdapter<
	ED extends IExtendedDatabase<ED, ET, EE, EP>,
	ET extends IExtendedTable<ET, EE, EP>,
	EE extends IExtendedEntity<EE, EP>,
	EP extends IExtendedProperty<EP>
> extends IDataAdapter<ED, ET, EE, EP> {
	
	//method
	default IExtendedDataAdapter<ED, ET, EE, EP> addEntity(final EE entity) {
		
		getRefDatabase().addEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedDataAdapter<ED, ET, EE, EP> addEntity(final EE... entities) {
		
		getRefDatabase().addEntity(entities);
		
		return this;
	}
	
	//method declaration
	default ET getRefTableByName(final String name) {
		return getRefDatabase().getRefTableByName(name);
	}
}
