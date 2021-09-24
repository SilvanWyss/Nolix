//package declaration
package ch.nolix.system.database.parametrizeddatatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedOptionalBackReferenceType<E extends Entity>
extends BaseParametrizedBackReferenceType<E> {
	
	//constructor
	public ParametrizedOptionalBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
	
	//method
	@Override
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalBackReferenceType
	toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		//TODO: Refactor.
		/*
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedOptionalBackReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName))
		);
		*/
		return null;
	}
}
