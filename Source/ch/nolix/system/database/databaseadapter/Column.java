//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.database.datatype.ParametrizedDataType;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.schemadatatype.SchemaDataType;

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
		return (isAnyReferenceColumn() && parametrizedDataType.getRefContentClass() == type);
	}
	
	//method
	public ParametrizedDataType<C> getDataType() {
		return parametrizedDataType;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public DataType getPropertyKind() {
		return parametrizedDataType.getPropertyKind();
	}
	
	//method
	public Class<C> getRefContentClass() {
		return parametrizedDataType.getRefContentClass();
	}
	
	//method
	public boolean isAnyBackReferenceColumn() {
		return parametrizedDataType.isAnyBackReferenceType();
	}
	
	//method
	public boolean isAnyDataColumn() {
		return parametrizedDataType.isAnyValueType();
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return parametrizedDataType.isAnyReferenceType();
	}
	
	//method
	public boolean isAnyTechnicalColumn() {
		return parametrizedDataType.isAnyTechnicalType();
	}
	
	//method
	public ch.nolix.system.database.databaseschemaadapter.Column toSchemaColumn(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ch.nolix.system.database.databaseschemaadapter.Column(getHeader(), getSchemaDataType(schemaEntitySets));
	}
	
	//method
	private SchemaDataType<?> getSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return parametrizedDataType.toSchemaDataType(schemaEntitySets);
	}
}
