//package declaration
package ch.nolix.system.database.databaseadapter;

//Java import
import java.lang.reflect.InvocationTargetException;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.valuecreator.ValueCreator;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.EntityAccessor;
import ch.nolix.system.database.parametrizeddatatype.DataTypeHelper;

//class
public final class EntityType<E extends Entity> implements Named {
	
	//attribute
	private final Class<E> entityClass;
	
	//multi-attribute for caching
	private final LinkedList<Column<?>> columns = new LinkedList<>();
	
	//constructor
	public EntityType(final Class<E> entityClass) {	
		
		Validator.assertThat(entityClass).thatIsNamed("Entity class").isNotNull();
		
		this.entityClass = entityClass;
		extractColumns();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E createEmptyEntity() {
		try {
			
			final var constructor = entityClass.getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			
			return (E)constructor.newInstance();
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| SecurityException
			exception
		) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	public E createPersistedEntity(
		final long id,
		final IContainer<BaseNode> valueSpecificationsInOrder,
		final ValueCreator<BaseNode> valueCreator
	) {
		
		/*
		 * The order of these instructions is important. The created Entity has to get a persisted state.
		 * The setValues method can change the persisted state into edited state. This behavior is correct.
		 * So the Entity has to be set persisted at the end.
		 */
		final var entity = createEmptyEntity();
		EntityAccessor.setId(entity, id);
		EntityAccessor.setValues(entity, valueSpecificationsInOrder, valueCreator);
		EntityAccessor.setPersisted(entity);
		
		return entity;
	}
	
	//method
	public LinkedList<Column<?>> getColumns() {
		return columns;
	}
	
	//method
	public Class<E> getRefEntityClass() {
		return entityClass;
	}
	
	//method
	@Override
	public String getName() {	
		return entityClass.getSimpleName();
	}
	
	//method
	public ch.nolix.system.database.databaseschemaadapter.EntitySet toEmptySchemaEntitySet() {
		return new ch.nolix.system.database.databaseschemaadapter.EntitySet(getName());
	}
	
	//method
	public void fillUpColumnsInOwnSchemaEntitySetFrom(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> entitySets
	) {
		
		final var ownSchemaEntitySet = entitySets.getRefOne(es -> es.hasSameNameAs(this));
		
		fillUpColumnsInOnwSchemaEntitySet(ownSchemaEntitySet, entitySets);
	}
	
	//method
	private void extractColumns() {
		
		columns.clear();
		
		for (final var p : createEmptyEntity().getRefProperties()) {
			columns.addAtBegin(new Column<>(p.getHeader(), DataTypeHelper.createDatatypeFor(p)));
		}
	}
	
	//method
	private void fillUpColumnsInOnwSchemaEntitySet(
		final ch.nolix.system.database.databaseschemaadapter.EntitySet ownEntitySet,
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		ownEntitySet.addColumns(getSchemaColumns(schemaEntitySets));
	}
	
	//method
	private LinkedList<ch.nolix.system.database.databaseschemaadapter.Column> getSchemaColumns(
		IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return getColumns().to(c -> c.toSchemaColumn(schemaEntitySets));
	}
}
