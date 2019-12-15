//package declaration
package ch.nolix.system.entity;

//Java import
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.reflectionHelpers.ReflectionHelper;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseAPI.IElement;

//class
public abstract class Property<V> implements Headered, IElement {
	
	//optional attribute
	private Entity parentEntity;
	
	//method
	public final boolean belongsToDatabaseAdapter() {
		return (belongsToEntity() && getParentEntity().belongsToDatabaseAdapter());
	}
	
	//method
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	public final boolean belongsToEntitySet() {
		return (belongsToEntity() && getParentEntity().belongsToEntitySet());
	}
	
	//method declaration
	public abstract boolean canReference(Entity entity);
	
	//method
	public abstract boolean canReferenceEntity();
	
	//method
	@Override
	public final List<Node> getAttributes() {
		return
		internal_getValues()
		.to(v -> Node.fromString(v.toString()));
	}
	
	//method
	/**
	 * @return the cell specification of the current {@link Property}.
	 */
	public abstract Node getCellSpecification();
	
	//method
	@Override
	public final String getHeader() {
		return ReflectionHelper.getFieldName(getParentEntity(), this);
	}
	
	//method
	public final IDatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final Entity getParentEntity() {
		
		supposeBelongsToEntity();
		
		return parentEntity;
	}
	
	//method
	public final IEntitySet<Entity> getParentEntitySet() {
		return getParentEntity().getParentEntitySet();
	}
	
	//method
	public abstract PropertyKind getPropertyKind();
	
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
	
	//method declaration
	public abstract boolean references(final Entity entity);
	
	//method declaration
	public abstract void supposeCanBeSaved();
	
	//method declaration
	protected abstract void internal_clear();
	
	//method declaration
	protected abstract List<Object> internal_getValues();
	
	//method
	protected void internal_noteUpdate() {
		if (belongsToEntity() && !parentEntity.isNew()) {
			parentEntity.setEdited();
		}
	}
	
	//method declaration
	protected abstract void internal_setValue(Object value);
	
	//method declaration
	protected abstract void internal_setValues(IContainer<Object> values);
	
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
