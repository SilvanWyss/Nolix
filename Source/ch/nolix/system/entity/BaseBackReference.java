//package declaration
package ch.nolix.system.entity;

//Java import
import java.lang.reflect.ParameterizedType;

import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.reflectionHelpers.ReflectionHelper;
import ch.nolix.common.validator.Validator;

//class
public abstract class BaseBackReference<E extends Entity> extends Property<E> {
	
	//attributes
	private final String referencingPropertyHeader;
	private int backReferenceCount = 0;
	
	//constructor
	public BaseBackReference(final String referencingPropertyHeader) {
		
		Validator
		.suppose(referencingPropertyHeader)
		.thatIsNamed("referencing property header")
		.isNotBlank();
		
		this.referencingPropertyHeader = referencingPropertyHeader;
	}
	
	//mehtod
	@Override
	public final boolean canReference(final Entity entity) {
		return false;
	}
	
	//method
	public final boolean canReferenceBackOnlyOneEntity() {
		return !canReferenceBackSeveralEntities();
	}
	
	//method
	public final boolean canReferenceBack(final Entity entity) {
		return (entity != null && getReferencingEntityClass() == entity.getClass());
	}
	
	//method declaration
	public abstract boolean canReferenceBackSeveralEntities();
	
	//method
	@Override
	public final boolean canReferenceEntity() {
		return false;
	}
	
	//method
	public int getBackReferenceCount() {
		return backReferenceCount;
	}
	
	//method
	@Override
	public Node getCellSpecification() {
		return new Node(getBackReferenceCount());
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<E> getReferencingEntityClass() {
		
		final var referencingEntityClass =
		ReflectionHelper.getRefField(getParentEntity(), this).getGenericType();
		
		return
		(Class<E>)
		((ParameterizedType)referencingEntityClass).getActualTypeArguments()[0];
	}
	
	//method
	public final IEntitySet<E> getReferencingEntitySet() {
		return getParentDatabaseAdapter().getRefEntitySet(getReferencingEntityClass());
	}
	
	//method
	public final String getReferencingPropertyHeader() {
		return referencingPropertyHeader;
	}
	
	//method
	public final boolean hasReferencingPropertyHeader(
		final String referencingPropertyHeader
	) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return this.referencingPropertyHeader.equals(referencingPropertyHeader);
	}
	
	//method
	@Override
	public final boolean references(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	protected final void internal_clear() {
		backReferenceCount = 0;
	}
	
	//method
	@Override
	protected final List<Object> internal_getValues() {
		return new List<>(getBackReferenceCount());
	}
	
	//method
	@Override
	protected void internal_setValue(final Object value) {
		backReferenceCount = (int)value;
	}

	//method
	@Override
	protected void internal_setValues(IContainer<Object> values) {
		internal_setValue(values.getRefOne());
	}
	
	//package-visible method
	final void supposeCanReferenceBack(final Entity entity) {
		if (!canReferenceBack(entity)) {
			throw new InvalidArgumentException(this, "cannot reference back the given entity");
		}
	}
	
	//package-visible method
	final void supposeCanReferenceBack(
		final Entity entity,
		final String referencingPropertyHeader
	) {
		
		supposeCanReferenceBack(entity);
		
		if (!hasReferencingPropertyHeader(referencingPropertyHeader)) {
			throw new InvalidArgumentException(this, "cannot reference back the given entity");
		}
	}
	
	//abstract package-visible method
	abstract void supposeCanReferenceBackAdditionally(
		Entity entity,
		String referencingPropertyHeader
	);
}
