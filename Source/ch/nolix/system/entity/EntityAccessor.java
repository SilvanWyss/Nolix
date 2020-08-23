//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.valueCreator.ValueCreator;

//class
/**
 * The {@link EntityAccessor} provides methods for {@link Entity}s,
 * that should not be visible on {@link Entity}s in common cases.
 * 
 * The {@link EntityAccessor} is designed to have static methods for a better performance.
 * Of the {@link EntityAccessor} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-12
 * @lines 80
 */
public final class EntityAccessor {
	
	//static method
	/**
	 * Extracts the properties of the given entity.
	 * 
	 * @param entity
	 */
	public static void extractProperties(final Entity entity) {
		entity.extractPropertiesWhenNotExtracted();
	}
	
	//static method
	/**
	 * @param entity
	 * @return the {@link Property}s of the given entity.
	 */
	public static IContainer<Property<?>> getRefProperties(final Entity entity) {
		return entity.getRefProperties();
	}
	
	//static method
	/**
	 * Sets the given id to the given entity.
	 * 
	 * @param entity
	 * @param id
	 */
	public static void setId(final Entity entity, final long id) {
		entity.setId(id);
	}
	
	//static method
	/**
	 * Sets the given entity persisted.
	 * 
	 * @param entity
	 */
	public static void setPersisted(final Entity entity) {
		entity.setPersisted();
	}
	
	//static method
	/**
	 * Sets the values from the given valueSpecificationsInOrder to the given entity using the given valueCreator.
	 * 
	 * @param entity
	 * @param valueSpecificationsInOrder
	 * @param valueCreator
	 */
	public static void setValues(
		final Entity entity,
		final IContainer<BaseNode> valueSpecificationsInOrder,
		final ValueCreator<BaseNode> valueCreator
	) {
		entity.setValues(valueSpecificationsInOrder, valueCreator);
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link EntityAccessor} can be created.
	 */
	private EntityAccessor() {}
}
