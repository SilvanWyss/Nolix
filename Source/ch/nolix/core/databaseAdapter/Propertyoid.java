//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.ParameterizedType;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
//own imports
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class Propertyoid<V> implements Specified {
	
	//attribute
	private Entity parentEntity;
	
	//method
	public final PropertyKind getPropertyKind() {
		return getPropertyType().getPropertyKind();
	}
	
	//abstract method
	public abstract PropertyoidType<V> getPropertyType() ;
	
	//method
	public final String getType() {
		return getRefEntity().getFieldName(this);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<V> getValueClass() {
		
		final var realClass = getRefEntity().getField(this).getGenericType();
		final var valueType = ((ParameterizedType)realClass).getActualTypeArguments()[0];
		
		return (Class<V>)(valueType);
	}
	
	
	//method
	public final boolean isDataProperty() {
		return getPropertyType().isDataType();
	}
	
	//method
	public final boolean isReferenceProperty() {
		return getPropertyType().isReferenceType();
	}
	
	//package-visible abstract method
	abstract IContainer<Object> inernal_getValues();
	
	//package-visible abstract method
	abstract void setValues(final List<Object> values);
	
	//method
	protected void noteUpdate() {
		if (belongsToEntity()) {
			parentEntity.setUpdated();
		}
	}
	
	//method
	protected void setParentEntity(final Entity parentEntity) {
		
		Validator
		.suppose(parentEntity)
		.thatIsOfType(Entity.class)
		.isNotNull();
		
		supposeBelongsToNoEntity();
		
		this.parentEntity = parentEntity;
	}
	
	//method
	private boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	private Entity getRefEntity() {
		
		supposeBelongsToEntity();
		
		return parentEntity;
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
