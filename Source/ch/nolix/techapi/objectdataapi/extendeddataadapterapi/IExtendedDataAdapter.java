//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataadapterapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedDatabase;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedEntity;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedTable;

//interface
public interface IExtendedDataAdapter<
	ED extends IExtendedDatabase<ED, ET, EE, P>,
	ET extends IExtendedTable<ET, EE, P>,
	EE extends IExtendedEntity<EE, P>,
	P extends IProperty<P>
> extends IDataAdapter<ED, ET, EE, P> {
	
	//method
	default IExtendedDataAdapter<ED, ET, EE, P> addEntity(final EE entity) {
		
		getRefDatabase().addEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedDataAdapter<ED, ET, EE, P> addEntity(final EE... entities) {
		
		getRefDatabase().addEntity(entities);
		
		return this;
	}
	
	//method declaration
	default ET getRefTableByName(final String name) {
		return getRefDatabase().getRefTableByName(name);
	}
}
