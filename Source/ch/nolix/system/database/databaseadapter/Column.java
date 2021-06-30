//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedDataType;
import ch.nolix.system.databaseschema.parametrizedschemadatatype.ParametrizedSchemaDataType;
import ch.nolix.techapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class Column<C> implements Headered {
	
	//attributes
	private final String header;
	private final ParametrizedDataType<C> parametrizedDataType;
	
	//constructor
	public Column(final String header, final ParametrizedDataType<C> dataType) {
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		Validator.assertThat(dataType).isOfType(ParametrizedDataType.class);
		
		this.header = header;
		this.parametrizedDataType = dataType;
	}
	
	//method
	public boolean canReference(final Entity entity) {
		return parametrizedDataType.canReference(entity);
	}
	
	//method
	public <E extends Entity> boolean canReferenceBackEntityOfType(final Class<E> type) {
		return (parametrizedDataType.isAnyBackReferenceType() && parametrizedDataType.getRefContentClass() == type);
	}
	
	//method
	public <E extends Entity> boolean canReferenceEntityOfType(final Class<E> type) {
		return
		getDataType().getBaseType() == BasePropertyType.BASE_REFERENCE
		&& parametrizedDataType.getRefContentClass() == type;
	}
	
	//method
	public PropertyType getDataType() {
		return parametrizedDataType.getPropertyKind();
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public ParametrizedDataType<C> getParametrizedDataType() {
		return parametrizedDataType;
	}
	
	//method
	public Class<C> getRefContentClass() {
		return parametrizedDataType.getRefContentClass();
	}
	
	//method
	public ch.nolix.system.databaseschema.databaseschemaadapter.Column toSchemaColumn(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ch.nolix.system.databaseschema.databaseschemaadapter.Column(getHeader(), getSchemaDataType(schemaEntitySets));
	}
	
	//method
	private ParametrizedSchemaDataType<?> getSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return parametrizedDataType.toSchemaDataType(schemaEntitySets);
	}
}
