//package declaration
package ch.nolix.system.entity;

//Java import
import java.lang.reflect.ParameterizedType;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.reflectionhelper.ReflectionHelper;
import ch.nolix.common.validator.Validator;

//class
public abstract class BaseBackReference<E extends Entity> extends Property<E> {
	
	//attributes
	private final String referencingPropertyHeader;
	private int backReferenceCount = 0;
	
	//constructor
	public BaseBackReference(final String referencingPropertyHeader) {
		
		Validator
		.assertThat(referencingPropertyHeader)
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
	protected final void internalClear() {
		backReferenceCount = 0;
	}
	
	//method
	@Override
	protected final LinkedList<Object> internalGetValues() {
		return LinkedList.withElements(getBackReferenceCount());
	}
	
	//method
	@Override
	protected void internalSetValue(final Object value) {
		backReferenceCount = (int)value;
	}

	//method
	@Override
	protected void internalSetValues(IContainer<Object> values) {
		internalSetValue(values.getRefOne());
	}
	
	//method
	final void supposeCanReferenceBack(final Entity entity) {
		if (!canReferenceBack(entity)) {
			throw new InvalidArgumentException(this, "cannot reference back the given entity");
		}
	}
	
	//method
	final void supposeCanReferenceBack(
		final Entity entity,
		final String referencingPropertyHeader
	) {
		
		supposeCanReferenceBack(entity);
		
		if (!hasReferencingPropertyHeader(referencingPropertyHeader)) {
			throw new InvalidArgumentException(this, "cannot reference back the given entity");
		}
	}
	
	//method declaration
	abstract void supposeCanReferenceBackAdditionally(Entity entity, String referencingPropertyHeader);
}
