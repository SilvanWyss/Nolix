//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.skillAPI.Identified2;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.core.validator2.Validator;

//abstract class
public abstract class Entity implements Identified2, Specified {
	
	//attribute
	private EntityState state = EntityState.CREATED;
	
	//optional attributes
	private long id = -1;
	private EntitySet<Entity> parentEntitySet;
	
	//multi-attributes
	private List<Propertyoid<?>> properties;
	private List<BackReferenceoid<?>> backReferences;
	
	//method
	public final boolean belongsToEntitySet() {
		return (parentEntitySet != null);
	}
	
	//method
	public final boolean canReferenceEntity(final Entity entity) {
		return getRefProperties().contains(p -> p.canReferenceEntity(entity));
	}
	
	//method
	public final boolean canReferenceOtherEntities() {
		return getRefProperties().contains(p -> p.isReferenceProperty());
	}
	
	//method
	public final List<DocumentNode> getAttributes() {
		
		final var attributes = new List<DocumentNode>();
		
		if (hasId()) {
			attributes.addAtEnd(DocumentNode.createFromLong(getId()));
		}
		
		for (final var p : getRefProperties()) {
			attributes.addAtEnd(p.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	public final List<Column<?>> getColumns() {
		
		if (!propertiesAndBackReferencesAreExtracted()) {
			extractPropertiesAndBackReferences();
		}
		
		final var columns
		= new List<Column<?>>(new Column<>(PascalCaseNameCatalogue.ID, new IdPropertyType()));
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final var f : cl.getDeclaredFields()) {
				
				if (Propertyoid.class.isAssignableFrom(f.getType())) {
					
					try {
						
						f.setAccessible(true);
						
						final Propertyoid<?> property = (Propertyoid<?>)(f.get(this));
						
						Validator.suppose(property).isInstanceOf(Propertyoid.class);
						
						columns.addAtEnd(
							new Column<>(
								f.getName(),
								property.getPropertyType()
							)
						);
					}
					catch (
						final IllegalArgumentException
						| IllegalAccessException exception
					) {
						throw new RuntimeException(exception);
					}
				}
			}
			
			cl = cl.getSuperclass();
		}
		
		return columns;
	}
	
	//method
	public final long getId() {
	
		supposeHasId();
		
		return id;
	}
	
	//method
	public final DatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final EntitySet<Entity> getParentEntitySet() {
		
		supposeBelongsToEntitySet();
		
		return parentEntitySet;
	}
	
	//method
	public IContainer<Propertyoid<?>> getRefProperties() {
		
		if (!propertiesAndBackReferencesAreExtracted()) {
			extractPropertiesAndBackReferences();
		}
		
		return properties;
	}
	
	//method
	public final DocumentNode getRowSpecification() {
		
		final var rowSpecification = new DocumentNode();
		
		rowSpecification.addAttribute(DocumentNode.createFromLong(getId()));
		
		for (final var p : getRefProperties()) {
			rowSpecification.addAttribute(new DocumentNode(p.internal_getValues().toString()));
		}
		
		return rowSpecification;
	}
	
	//method
	public final EntityState getState() {
		return state;
	}
	
	//method
	public final String getType() {
		return getClass().getSimpleName();
	}

	//method
	public final boolean hasId() {
		return (id > -1);
	}
	
	//method
	public final boolean hasId(final long id) {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current entity does not have an id.
			if (this.id < 0) {
				return false;
			}
			
			//Handles the case that the current entity has an id.
			return (this.id == id);
	}
	
	//method
	public final boolean isChanged() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.CHANGED);
	}
	
	//method
	public final boolean isConcerned() {
		
		//For a better performance, this implementation does not use all comfortable methods.	
		return (state == EntityState.CONCERNED);
	}
	
	//method
	public final boolean isCreated() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.CREATED);
	}
	
	//method
	public final boolean isDeleted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.DELETED);
	}
	
	//method
	public final boolean isReferenced() {
		return
		getParentDatabaseAdapter()
		.getRefEntitySets()
		.contains(es -> es.references(this));
	}
	
	//method
	public final boolean isPersisted() {
		return (getState() == EntityState.PERSISTED);
	}
	
	//method
	public final boolean isRejected() {
		return (getState() == EntityState.REJECTED);
	}
	
	//method
	public final boolean references(final Entity entity) {
		return getRefProperties().contains(p -> p.references(entity));
	}
	
	//method
	public final boolean references(final String header, final Entity entity) {
		return getRefProperties().contains(p -> p.hasHeader(header) && p.references(entity));
	}
	
	//method
	public final void setId(final int id) {
		
		Validator
		.suppose(id)
		.thatIsNamed(VariableNameCatalogue.ID)
		.isPositive();
		
		supposeHasNoId();
		
		this.id = id;
	}
	
	//package-visible method
	final void setChanged() {
		switch (getState()) {
			case PERSISTED:
			case CONCERNED:
				
				state = EntityState.CHANGED;
				
				if (belongsToEntitySet()) {
					getParentDatabaseAdapter().noteMutatedEntity(this);
				}
				
				break;
			case CREATED:
				break;
			case CHANGED:
				break;
			case DELETED:
				throw new InvalidStateException(this, "is deleted");
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setDeleted() {
		switch (getState()) {
			case PERSISTED:
			case CONCERNED:
			case CHANGED:
				state = EntityState.DELETED;
				break;
			case CREATED:
				throw new InvalidStateException(this, "is created");
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setParentEntitySet(final EntitySet<Entity> parentEntitySet) {
		
		supposeDoesNotBelongToEntitySet();
		
		this.parentEntitySet = parentEntitySet;
	}

	//package-visible method
	final void setPersisted() {
		switch (getState()) {
			case PERSISTED:
				break;
			case CREATED:
				state = EntityState.PERSISTED;
				break;
			case CONCERNED:
				throw new InvalidStateException(this, "is concerned");
			case CHANGED:
				throw new InvalidStateException(this, "is changed");
			case DELETED:
				throw new InvalidStateException(this, "is deleted");
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setRejected() {
		state = EntityState.REJECTED;
	}
	
	//package-visible method
	@SuppressWarnings("incomplete-switch")
	final void setValues(final Iterable<DocumentNodeoid> valuesInOrder) {
		
		//Iterates the properties of the current entity and the given values in order synchronously.
		final var propertiesIterator = getRefProperties().iterator();
		for (final var v : valuesInOrder) {
			
			final var property = propertiesIterator.next();			
			
			//Enumerates the kind of the current property.
			switch (property.getPropertyKind()) {
				case DATA:
					
					property.internal_setValues(
						new List<Object>(
							DatabaseAdapter.createValue(property.getValueType(), v)
						)
					);
					
					break;					
				case OPTIONAL_DATA:
					
					if (v.containsAttributes()) {
						property.internal_setValues(
							new List<Object>(
								DatabaseAdapter.createValue(property.getValueType(), v)
							)
						);
					}
					
					break;
				case MULTI_DATA:
					
					final var valueType = property.getValueType();
					
					property.internal_setValues(
						v.getRefAttributes().to(
							a -> DatabaseAdapter.createValue(valueType, a)
						)						
					);
					
					break;
				case REFERENCE:
					
					property.internal_setValues(
						new List<Object>(
							v.toInt()
						)
					);
					
					break;
				case OPTIONAL_REFERENCE:
					
					if (v.containsAttributes()) {
						property.internal_setValues(
							new List<Object>(
								v.toInt()
							)
						);
					}
					
					break;
				case MULTI_REFERENCE:
					
					property.internal_setValues(
						v.getRefAttributes().to(
							a -> a.toInt()
						)
					);
					
					break;
			}
		}
	}
	
	//package-visible method
	final void supposeCanReferenceBackAdditionally(final Entity entity, final String referencingPropertyHeader) {
		backReferences
		.getRefSelected(br -> br.hasReferencingPropertyHeader(referencingPropertyHeader))
		.forEach(br -> br.supposeCanReferenceBackAdditionally(entity, referencingPropertyHeader));
	}
	
	//method
	private void extractPropertiesAndBackReferences() {
		
		properties = new List<Propertyoid<?>>();
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final Field f : cl.getDeclaredFields()) {
				
				f.setAccessible(true);
				
				if (Propertyoid.class.isAssignableFrom(f.getType())) {				
					try {			
						final var property = (Propertyoid<?>)(f.get(this));
						property.internal_setParentEntity(this);
						properties.addAtEnd(property);	
					}
					catch (
						final IllegalArgumentException | IllegalAccessException exception
					) {
						throw new RuntimeException(exception);
					}
				}
				else if (BackReferenceoid.class.isAssignableFrom(f.getType())) {
					try {			
						final var backReference = (BackReferenceoid<?>)(f.get(this));
						backReference.setParentEntity(this);
						backReferences.addAtEnd(backReference);
					}
					catch (
						final IllegalArgumentException | IllegalAccessException exception
					) {
						throw new RuntimeException(exception);
					}
				}
			}
			
			cl = cl.getSuperclass();
		}
	}
	
	//method
	private boolean propertiesAndBackReferencesAreExtracted() {
		return (properties != null);
	}
	
	//method
	private void supposeBelongsToEntitySet() {		
		if (!belongsToEntitySet()) {
			throw new InvalidStateException(
				this,
				"belongs to no entity set"
			);
		}		
	}
	
	//method
	private void supposeDoesNotBelongToEntitySet() {
		if (belongsToEntitySet()) {
			throw new InvalidStateException(
				this,
				"belongs to entity"
			);
		}		
	}
	
	//method
	private void supposeHasId() {
		if (!hasId()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.ID
			);
		}
	}
	
	//method
	private void supposeHasNoId() {		
		if (hasId()) {
			throw new InvalidStateException(
				this,
				"has an id"
			);
		}
	}
}
