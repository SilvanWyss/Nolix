//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.container.LinkedList;

//class
public final class MultiBackReference<E extends Entity> extends BaseBackReference<E> {
	
	//constructor
	public MultiBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public boolean canReferenceBackSeveralEntities() {
		return true;
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_BACK_REFERENCE;
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
	public void supposeCanBeSaved() {}
	
	//method
	@Override
	void supposeCanReferenceBackAdditionally(
		final Entity entity,
		final String referencingPropertyHeader
	) {
		supposeCanReferenceBack(entity, referencingPropertyHeader);
	}
}
