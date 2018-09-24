//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.skillInterfaces.Headered;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class Propertyoid<V>
implements Headered, Specified {
	
	//optional attribute
	private Entity parentEntity;
	
	//method
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//abstract method
	public abstract boolean canReferenceEntity(Entity entity);
	
	//method
	public final List<DocumentNode> getAttributes() {
		return
		internal_getValues()
		.to(v -> new DocumentNode(v.toString()));
	}
	
	//method
	public final String getHeader() {
		return getParentEntity().getFieldName(this);
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
	public final String getValueType() {
		return getValueClass().getSimpleName();
	}
	
	//method
	public final boolean isDataProperty() {
		return getPropertyType().isDataType();
	}
	
	//method
	public final boolean isMultiDataProperty() {
		return getPropertyType().isMultiDataType();
	}
	
	//method
	public final boolean isMultiReferenceProperty() {
		return getPropertyType().isMultiReferenceType();
	}
	
	//method
	public final boolean isOptionalDataProperty() {
		return getPropertyType().isOptionalDataType();
	}
	
	//method
	public final boolean isOptionalReferenceProperty() {
		return getPropertyType().isOptionalReferenceType();
	}
	
	//method
	public final boolean isReferenceProperty() {
		return getPropertyType().isReferenceType();
	}
	
	//abstract method
	public abstract boolean references(final Entity entity);
	
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
		.isInstance();
		
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
