//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IExtendedTable<
	ET extends IExtendedTable<ET, EE, P>,
	EE extends IExtendedEntity<EE, P>,
	P extends IProperty<P>
> extends ITable<ET, EE, P> {
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedTable<ET, EE, P> addEntity(final EE... entities) {
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
}
