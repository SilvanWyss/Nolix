//package declaration
package ch.nolix.techapi.objectdataapi.extendedstructuraldataapi;

//own imports
import ch.nolix.techapi.objectdataapi.structuraldataapi.IProperty;
import ch.nolix.techapi.objectdataapi.structuraldataapi.IStructuralTable;

//interface
public interface IExtendedStructuralTable<
	EST extends IExtendedStructuralTable<EST, ESE, P>,
	ESE extends IExtendedStructuralEntity<ESE, P>,
	P extends IProperty<P>
> extends IStructuralTable<EST, ESE, P> {
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedStructuralTable<EST, ESE, P> addEntity(final ESE... entities) {
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
}
