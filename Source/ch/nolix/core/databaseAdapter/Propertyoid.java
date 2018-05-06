//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class Propertyoid<V> implements Specified {
	
	//optional attribute
	private Entity parentEntity;
	
	//method
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	public final List<StandardSpecification> getAttributes() {
		return internal_getValues().to(v -> new StandardSpecification(v.toString()));
	}
	
	//method
	public final DatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final Entity getParentEntity() {
		
		supposeBelongsToEntity();
		
		return parentEntity;
	}
	
	//method
	public final EntitySet<Entity> getParentEntitySet() {
		return getParentEntity().getParentEntitySet();
	}
	
	//method
	public final PropertyKind getPropertyKind() {
		return getPropertyType().getPropertyKind();
	}
	
	//abstract method
	public abstract PropertyoidType<V> getPropertyType();
	
	//method
	public final String getType() {
		return getParentEntity().getFieldName(this);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<V> getValueClass() {
		
		final var actualClass = 
		getParentEntity().getField(this).getGenericType();
		
		return
		(Class<V>)
		((ParameterizedType)actualClass).getActualTypeArguments()[0];
	}
	
	//method
	public final boolean isDataProperty() {
		return getPropertyType().isDataType();
	}
	
	//method
	public final boolean isReferenceProperty() {
		return getPropertyType().isReferenceType();
	}
	
	//abstract method
	protected abstract void internal_clear();
	
	//abstract method
	protected abstract List<Object> internal_getValues();
	
	//method
	protected void internal_noteUpdate() {
		if (belongsToEntity()) {
			parentEntity.setUpdated();
		}
	}
	
	//abstract method
	protected abstract void internal_setValues(Iterable<Object> values);
	
	//method
	protected void internal_setParentEntity(final Entity parentEntity) {
		
		Validator
		.suppose(parentEntity)
		.thatIsNamed("parent entity")
		.isNotNull();
		
		supposeBelongsToNoEntity();
		
		this.parentEntity = parentEntity;
	}
	
	//method
	private void supposeBelongsToEntity() {
		if (!belongsToEntity()) {
			throw new InvalidStateException(
				this,
				"belongs to no entity"
			);
		}
	}
	
	//method
	private void supposeBelongsToNoEntity() {
		if (belongsToEntity()) {
			throw new InvalidStateException(
				this,
				"belongs to an entity"
			);
		}
	}
}
