//package declaration
package ch.nolix.system.databaseAdapter;

//Java import
import java.lang.reflect.ParameterizedType;

import ch.nolix.core.attributeAPI.Headered;
//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.helper.ReflectionHelper;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.core.validator.Validator;

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
	@Override
	public final List<DocumentNode> getAttributes() {
		return
		internal_getValues()
		.to(v -> new DocumentNode(v.toString()));
	}
	
	//method
	@Override
	public final String getHeader() {
		return ReflectionHelper.getFieldName(getParentEntity(), this);
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
	@Override
	public final String getType() {
		return ReflectionHelper.getFieldName(getParentEntity(), this);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<V> getValueClass() {
		
		final var actualClass =
		ReflectionHelper.getRefField(getParentEntity(), this).getGenericType();
		
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
			parentEntity.setChanged();
		}
	}
	
	//abstract method
	protected abstract void internal_setValue(Object value);
	
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
			throw new InvalidArgumentException(
				this,
				"does not belong to a entity"
			);
		}
	}
	
	//method
	private void supposeBelongsToNoEntity() {
		if (belongsToEntity()) {
			throw new InvalidArgumentException(
				this,
				"belongs to an entity"
			);
		}
	}
}
