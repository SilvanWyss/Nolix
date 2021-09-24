//package declaration
package ch.nolix.system.database.parametrizeddatatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedBackReferenceType<E extends Entity> extends BaseParametrizedBackReferenceType<E> {
	
	//constructor
	public ParametrizedBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.BACK_REFERENCE;
	}
	
	//method
	@Override
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		//TODO: Refacotr.
		/*
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedBackReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName))
		);
		*/
		return null;
	}
}
