//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractParameterizedValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;

//class
public final class AbstractParameterizedValueContent<V>
extends AbstractParameterizedContent<V>
implements IAbstractParameterizedValueContent<V> {
	
	//attribute
	private final BackReference<AbstractValueContent> parentAbstractValueContent =
	BackReference.forEntityAndBackReferencedPropertyName(AbstractValueContent.class, "abstractParameterizedValueContent");
	
	//constructor
	public AbstractParameterizedValueContent() {
		initialize();
	}
	
	//method
	@Override
	public IAbstractParameterizedValueContent<V> addConstraint(final IConstraint<V> constraint) {
		
		//TODO: Implement.
		return this;
	}
	
	//method
	@Override
	public IContainer<IConstraint<V>> getStoredConstraints() {
		
		//TODO: Implement.
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public IAbstractValueContent getStoredParentAbstractValueContent() {
		return parentAbstractValueContent.getBackReferencedEntity();
	}
	
	//method
	@Override
	public void removeConstraint(final IConstraint<V> constraint) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void removeConstraints() {
		//TODO: Implement.
	}
}
