//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Identified;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class Entity
implements Identified, Specified {
	
	//attribute
	private EntityState state = EntityState.CREATED;
	
	//optional attributes
	private int id = -1;
	private EntitySet<Entity> parentEntitySet;
	
	//multi-attribute
	private List<Propertyoid<?>> properties;
	
	//method
	public final boolean belongsToEntitySet() {
		return (parentEntitySet != null);
	}
		
	//method
	public final boolean canReferenceOtherEntities() {
		return getRefProperties().contains(p -> p.isReferenceProperty());
	}
	
	//method
	public final List<StandardSpecification> getAttributes() {
		
		final var attributes = new List<StandardSpecification>();
		
		if (hasId()) {
			attributes.addAtEnd(StandardSpecification.createFromInt(getId()));
		}
		
		for (final var p : getRefProperties()) {
			attributes.addAtEnd(p.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	public final List<Column<?>> getColumns() {
		
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
				
		final var columns = new List<Column<?>>();
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final var f : cl.getDeclaredFields()) {
				
				if (Propertyoid.class.isAssignableFrom(f.getType())) {
					
					try {
						
						f.setAccessible(true);
						
						final Propertyoid<?> property = (Propertyoid<?>)(f.get(this));
						
						Validator.suppose(property)
						.thatIsOfType(Propertyoid.class)
						.isNotNull();
						
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
	public final int getId() {
	
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
	public final StandardSpecification getRowSpecification() {
		
		final var rowSpecification = new StandardSpecification();
		
		rowSpecification.addAttribute(StandardSpecification.createFromInt(getId()));
		
		for (final var p : getRefProperties()) {
			rowSpecification.addAttribute(new StandardSpecification(p.internal_getValues().toString()));
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
	public final boolean isCreated() {
		return (getState() == EntityState.CREATED);
	}
	
	//method
	public final boolean isDeleted() {
		return (getState() == EntityState.DELETED);
	}
	
	//method
	public final boolean isPersisted() {
		return (getState() == EntityState.PERSISTED);
	}
	
	//method
	public final boolean isReferencedByOtherEntities() {
		
		//TODO
		return false;
	}
	
	//method
	public final boolean isRejected() {
		return (getState() == EntityState.REJECTED);
	}
	
	//method
	public final boolean isUpdated() {
		return (getState() == EntityState.UPDATED);
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
	final Field getField(final Propertyoid<?> property) {
		
		for (final var f : getClass().getFields()) {
			try {
				
				f.setAccessible(true);
				
				if (f.get(this) == property) {
					return f;
				}
			}
			catch (
				final
				IllegalArgumentException
				| IllegalAccessException
				exception
			) {
				throw new RuntimeException(exception);
			}
		}
		
		throw new InvalidStateException(this, "has no such property");
	}
	
	//package-visible method
	final String getFieldName(final Propertyoid<?> property) {
		return getField(property).getName();
	}
	
	//TODO
	final void set(final Iterable<Specification> allPropertiesInOrder) {
		
		final var iterator = getRefProperties().iterator();
		for (final var p : allPropertiesInOrder) {
			
			final var property = iterator.next();			
			
			if (property.isDataProperty()) {
				switch (property.getValueClass().getSimpleName()) {
					case "String":
						property.internal_setValues(new List<Object>(p.toString()));
						break;
					case "Integer":
						property.internal_setValues(new List<Object>(p.toInt()));
						break;
					default:
						throw new RuntimeException("Invalid case");
				}
			}
			
			if (property.isReferenceProperty()) {
				switch (property.getClass().getSimpleName()) {
					case "ReferenceProperty":
						property.internal_setValues(new List<Object>(p.toInt()));
						break;
				}
				
			}
		}
	}

	//package-visible method
	final void setDeleted() {
		switch (getState()) {
			case PERSISTED:
				state = EntityState.DELETED;
				break;
			case CREATED:
				throw new InvalidStateException(this, "is created");
			case UPDATED:
				state = EntityState.DELETED;
				break;
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void  setParentEntitySet(final EntitySet<Entity> parentEntitySet) {
		
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
			case UPDATED:
				throw new InvalidStateException(this, "is updated");
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
	final void setUpdated() {
		switch (getState()) {
			case PERSISTED:
				state = EntityState.UPDATED;
				break;
			case CREATED:
				break;
			case UPDATED:
				break;
			case DELETED:
				throw new InvalidStateException(this, "is deleted");
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//method
	private void extractProperties() {
		
		properties = new List<Propertyoid<?>>();
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final Field f : cl.getDeclaredFields()) {
				
				if (Propertyoid.class.isAssignableFrom(f.getType())) {
					
					try {
						
						f.setAccessible(true);
						
						final Propertyoid<?> property = (Propertyoid<?>)(f.get(this));
						
						Validator.suppose(property)
						.thatIsOfType(Propertyoid.class)
						.isNotNull();
						
						property.internal_setParentEntity(this);
						properties.addAtEnd(property);	
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
	}
	
	//method
	private IContainer<Propertyoid<?>> getRefProperties() {
		
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return properties;
	}
	
	//method
	private boolean propertiesAreExtracted() {
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
