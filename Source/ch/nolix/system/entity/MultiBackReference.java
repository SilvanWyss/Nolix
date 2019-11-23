//package declaration
package ch.nolix.system.entity;

//owm import
import ch.nolix.common.containers.List;

//class
public final class MultiBackReference<E extends Entity> extends BackReferenceoid<E> {
	
	//constructor
	public MultiBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public final boolean canReferenceBackSeveralEntities() {
		return true;
	}
	
	//method
	public List<E> getReferencingEntities() {
		return
		getReferencingEntitySet()
		.getRefEntities()
		.getRefSelected(e -> e.references(getReferencingPropertyHeader(), getParentEntity()));
	}
	
	//package-visible method
	@Override
	void supposeCanReferenceBackAdditionally(
		final Entity entity,
		final String referencingPropertyHeader
	) {
		supposeCanReferenceBack(entity, referencingPropertyHeader);
	}
}
