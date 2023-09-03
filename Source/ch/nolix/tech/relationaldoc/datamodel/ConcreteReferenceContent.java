//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.tech.relationaldoc.datavalidator.ConcreteReferenceContentValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;

//class
public final class ConcreteReferenceContent extends ReferenceContent implements IConcreteReferenceContent {
	
	//constant
	private static final ConcreteReferenceContentValidator CONCRETE_REFERENCE_CONTENT_VALIDATOR =
	new ConcreteReferenceContentValidator();
	
	//attribute
	private final BackReference<AbstractableField> parentField =
	BackReference.forEntityAndBackReferencedPropertyName(AbstractableField.class, "concreteReferenceContent");
	
	//multi-attribute
	private final MultiReference<AbstractableObject> referencedObjects =
	MultiReference.forEntity(AbstractableObject.class);
	
	//constructor
	public ConcreteReferenceContent() {
		initialize();
	}
	
	//method
	@Override
	public IConcreteReferenceContent addObject(final IAbstractableObject object) {
		
		CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanAddObject(this, object);
		
		referencedObjects.addEntity(object);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableField getStoredParentField() {
		return parentField.getBackReferencedEntity();
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredReferencedObjects() {
		return referencedObjects.getReferencedEntities();
	}
	
	//method
	@Override
	public void removeObject(final IAbstractableObject object) {
		
		CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveOneObject(this);
		
		referencedObjects.removeEntity(null);
	}
	
	//method
	@Override
	public void removeObjects() {
		
		CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveObjects(this);
		
		referencedObjects.clear();
	}
}
