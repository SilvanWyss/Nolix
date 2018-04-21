//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Identifiable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class Entity
implements Identifiable, Specified {
	
	//attributes
	private int id = -1;
	private EntityState state = EntityState.CREATED;

	//multi-attribute
	private final List<Propertyoid<?>> properties = new List<Propertyoid<?>>();
	
	//constructor
	public Entity() {
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final var f : cl.getDeclaredFields()) {
				
				if (f.getType().isAssignableFrom(Propertyoid.class)) {
					
					try {
						
						f.setAccessible(true);
						
						final Propertyoid<?> property = (Propertyoid<?>)(f.get(this));
						
						Validator.suppose(property)
						.thatIsOfType(Propertyoid.class)
						.isNotNull();
						
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
	public final boolean canReferenceOtherEntities() {
		return properties.contains(p -> p.isReferenceProperty());
	}
	
	//method
	public final List<StandardSpecification> getAttributes() {
		return properties.to(p -> new StandardSpecification(p.toString()));
	}
	
	//method
	public List<Column<?>> getColumns() {
		
		final var columns = new List<Column<?>>();
		
		Class<?> cl = getClass();
		while (cl != null) {
			
			for (final var f : cl.getDeclaredFields()) {
				
				if (f.getType().isAssignableFrom(Propertyoid.class)) {
					
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
	public final boolean isEdited() {
		return (getState() == EntityState.EDITED);
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
	final void setDeleted() {
		switch (getState()) {
			case PERSISTED:
				state = EntityState.DELETED;
				break;
			case CREATED:
				throw new InvalidStateException(this, "is created");
			case EDITED:
				state = EntityState.DELETED;
				break;
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//method
	final void setEdited() {
		switch (getState()) {
			case PERSISTED:
				state = EntityState.EDITED;
				break;
			case CREATED:
				break;
			case EDITED:
				break;
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
	
	//method
	private void supposeHasId() {
		if (!hasId()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.ID
			);
		}
	}
}
