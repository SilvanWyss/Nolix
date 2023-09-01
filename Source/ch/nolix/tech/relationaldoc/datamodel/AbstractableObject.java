//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableObjectValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractableObject extends Entity implements IAbstractableObject {
	
	//constant
	public static final boolean DEFAULT_ABSTRACT_FLAG = false;
	
	//constant
	private static final AbstractableObjectValidator ABSTRACTABLE_OBJECT_VALIDATOR = new AbstractableObjectValidator();
	
	//attribute
	private final Value<String> name = new Value<>();
	
	//attribute
	private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);
	
	//multi-attribute
	private final MultiReference<AbstractableObject> directBaseTypes =
	MultiReference.forEntity(AbstractableObject.class); 
	
	//multi-attribute
	private final MultiReference<AbstractableField> nonInheritedFields =
	MultiReference.forEntity(AbstractableField.class);
	
	//method
	@Override
	public IAbstractableObject addBaseType(final IAbstractableObject baseType) {
		
		ABSTRACTABLE_OBJECT_VALIDATOR.assertCanAddBaseType(this, baseType);
		
		directBaseTypes.addEntity(baseType);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableObject addField(final IAbstractableField field) {
		
		ABSTRACTABLE_OBJECT_VALIDATOR.assertCanAddField(this, field);
		
		nonInheritedFields.addEntity(field);
		
		return this;
	}
	
	//method
	@Override
	public String getName() {
		return name.getStoredValue();
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredBaseTypes() {
		return
		ReadContainer
		.forIterable(
			getStoredDirectBaseTypes(),
			getStoredDirectBaseTypes().toFromGroups(IAbstractableObject::getStoredBaseTypes)
		);
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredConcreteSubTypes() {
		return getStoredSubTypes().getStoredSelected(IAbstractableObject::isConcrete);
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredDirectBaseTypes() {
		return directBaseTypes.getReferencedEntities();
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredDirectSubTypes() {
		
		//TODO: Create MultiBackReference.
		return
		getStoredParentDatabase()
		.getStoredTableByEntityType(AbstractableObject.class)
		.getStoredEntities()
		.getStoredSelected(ao -> ao.getStoredDirectBaseTypes().contains(this));
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableField> getStoredFields() {
		return
		ReadContainer.forIterable(
			getStoredNonInheritedFields().getStoredOther(IAbstractableField::inheritsFromBaseField),
			getStoredDirectBaseTypes().toFromGroups(IAbstractableObject::getStoredFields)
		);
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableField> getStoredNonInheritedFields() {
		return nonInheritedFields.getReferencedEntities();
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredSubTypes() {
		
		//TODO: Create MultiBackReference.
		return
		getStoredParentDatabase()
		.getStoredTableByEntityType(AbstractableObject.class)
		.getStoredEntities()
		.getStoredSelected(ao -> ao.getStoredBaseTypes().contains(this));
	}
	
	//method
	@Override
	public boolean isAbstract() {
		return abstractFlag.getStoredValue();
	}
	
	//method
	@Override
	public void removeDirectBaseType(final IAbstractableObject directBaseType) {
		directBaseTypes.removeEntity((AbstractableObject)directBaseType);
	}
	
	//method
	@Override
	public void removeNonInheritedField(final IAbstractableField nonInheritedField) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public IAbstractableObject setAsAbstract() {
		
		abstractFlag.setValue(true);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableObject setAsConcrete() {
		
		ABSTRACTABLE_OBJECT_VALIDATOR.assertCanBeSetAsConcrete(this);
		
		abstractFlag.setValue(false);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableObject setName(final String name) {
		
		ABSTRACTABLE_OBJECT_VALIDATOR.assertCanSetName(this, name);
		
		this.name.setValue(name);
		
		return this;
	}
}
