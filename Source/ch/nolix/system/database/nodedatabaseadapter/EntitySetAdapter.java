//package declaration
package ch.nolix.system.database.nodedatabaseadapter;

//own imports
import ch.nolix.common.constant.PluralPascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.valuecreator.ValueCreator;
import ch.nolix.system.database.databaseadapter.BaseEntitySetAdapter;
import ch.nolix.system.database.databaseadapter.EntityType;
import ch.nolix.system.database.entity.Entity;

//class
public final class EntitySetAdapter<E extends Entity> extends BaseEntitySetAdapter<E> {

	//attribute
	private final BaseNode entitySetSpecification;
	
	//constructor
	EntitySetAdapter(
		final BaseNode entitySetSpecification,
		final EntityType<E> entityType,
		final ValueCreator<BaseNode> valueCreator
	) {
		
		super(entityType, valueCreator);
		
		Validator
		.assertThat(entitySetSpecification)
		.isNotNull();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void add(final E entity) {
		getEntitiesAdapter().add(entity);
	}
	
	//method
	@Override
	public boolean containsEntity(final long id) {
		return getEntitiesAdapter().containsEntity(id);
	}
	
	//method
	public void delete(final E entity) {
		getEntitiesAdapter().delete(entity);
	}
	
	//method
	public EntitiesAdapter<E> getEntitiesAdapter() {
		return
		new EntitiesAdapter<>(
			entitySetSpecification.getRefFirstAttribute(PluralPascalCaseCatalogue.ENTITIES),
			getValueCreator()
		);
	}
	
	//method
	@Override
	public LinkedList<E> getEntities() {
		return getEntitiesAdapter().getEntities(getEntityType());
	}
	
	//method
	@Override
	public E getEntity(final long id) {
		return getEntitiesAdapter().getEntity(id, getEntityType());
	}
	
	//method
	@Override
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute()
		.getOneAttributeHeader();
	}
	
	//method
	public void update(final E entity) {
		getEntitiesAdapter().update(entity);
	}
}
