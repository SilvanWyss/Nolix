//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.Field;
import java.util.Iterator;

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
	
	//attributes
	private int id = -1;
	private EntityState state = EntityState.CREATED;

	//multi-attribute
	private List<Propertyoid<?>> properties;
		
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
	public List<Column<?>> getColumns() {
		
		//TODO
		extractProperties();
				
		final var columns = new List<Column<?>>();
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final var f : cl.getDeclaredFields()) {
				
				if (f.getType().isAssignableFrom(Property.class)) {
					
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
	public int getId() {
	
		supposeHasId();
		
		return id;
	}
	
	public final StandardSpecification getRowSpecification() {
		
		final var rowSpecification = new StandardSpecification();
		
		rowSpecification.addAttribute(StandardSpecification.createFromInt(getId()));
		
		for (final var p : getRefProperties()) {
			rowSpecification.addAttribute(new StandardSpecification(p.inernal_getValues().toString()));
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
	
	
	final Field getField(final Propertyoid<?> property) {
		
		for (final Field f : getClass().getFields()) {
			try {
				
				f.setAccessible(true);
				
				if (f.get(this) == property) {
					return f;
				}
			}
			catch (
				final
				IllegalArgumentException
				| IllegalAccessException exception
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
	
	final void set(final Iterable<Specification> allPropertiesInOrder) {
		
		final Iterator<Propertyoid<?>> iterator = getRefProperties().iterator();
		for (final var p : allPropertiesInOrder) {
			
			final var property = iterator.next();
			
			switch (property.getValueClass().getSimpleName()) {
				case "String":
					property.setValues(new List<Object>(p.toString()));
					break;
				case "Integer":
					property.setValues(new List<Object>(p.toInt()));
					break;
				default:
					throw new RuntimeException("Invalid case");
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
				
				if (f.getType().isAssignableFrom(Property.class)) {
					
					try {
						
						f.setAccessible(true);
						
						final Propertyoid<?> property = (Propertyoid<?>)(f.get(this));
						
						Validator.suppose(property)
						.thatIsOfType(Propertyoid.class)
						.isNotNull();
						
						property.setParentEntity(this);
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
