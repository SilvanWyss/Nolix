//package declaration
package ch.nolix.system.databaseadapter;

//own imports
import ch.nolix.common.attributeapi.Headered;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.datatype.DataType;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaDataType;

//class
public final class Column<C> implements Headered {
	
	//attributes
	private final String header;
	private final DataType<C> dataType;
	
	//constructor
	public Column(final String header, final DataType<C> dataType) {
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		Validator.assertThat(dataType).isOfType(DataType.class);
		
		this.header = header;
		this.dataType = dataType;
	}
	
	//method
	public boolean canReference(final Entity entity) {
		return dataType.canReference(entity);
	}
	
	//method
	public <E extends Entity> boolean canReferenceBackEntityOfType(final Class<E> type) {
		return (dataType.isAnyBackReferenceType() && dataType.getRefContentClass() == type);
	}
	
	//method
	public <E extends Entity> boolean canReferenceEntityOfType(final Class<E> type) {
		return (isAnyReferenceColumn() && dataType.getRefContentClass() == type);
	}
	
	//method
	public DataType<C> getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public PropertyKind getPropertyKind() {
		return dataType.getPropertyKind();
	}
	
	//method
	public Class<C> getRefContentClass() {
		return dataType.getRefContentClass();
	}
	
	//method
	public boolean isAnyBackReferenceColumn() {
		return dataType.isAnyBackReferenceType();
	}
	
	//method
	public boolean isAnyDataColumn() {
		return dataType.isAnyValueType();
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return dataType.isAnyReferenceType();
	}
	
	//method
	public boolean isAnyTechnicalColumn() {
		return dataType.isAnyTechnicalType();
	}
	
	//method
	public ch.nolix.system.databaseschemaadapter.Column toSchemaColumn(
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ch.nolix.system.databaseschemaadapter.Column(getHeader(), getSchemaDataType(schemaEntitySets));
	}
	
	//method
	private SchemaDataType<?> getSchemaDataType(
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return dataType.toSchemaDataType(schemaEntitySets);
	}
}
