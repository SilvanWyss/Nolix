//package declaration
package ch.nolix.system.database.entity;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class MultiBackReference<E extends Entity> extends BaseBackReference<E> {
	
	//constructor
	public MultiBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
	
	//method
	@Override
	public boolean canBeSeveral() {
		return true;
	}
	
	//method
	@Override
	public boolean canReferenceBackSeveralEntities() {
		return true;
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_BACK_REFERENCE;
	}
	
	//method
	public LinkedList<E> getReferencingEntities() {
		return
		getReferencingEntitySet()
		.getRefEntities()
		.getRefSelected(e -> e.references(getReferencingPropertyHeader(), getParentEntity()));
	}
	
	//method
	@Override
	void supposeCanReferenceBackAdditionally(
		final Entity entity,
		final String referencingPropertyHeader
	) {
		supposeCanReferenceBack(entity, referencingPropertyHeader);
	}
}
