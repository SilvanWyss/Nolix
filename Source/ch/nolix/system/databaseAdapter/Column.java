//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.dataType.DataType;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class Column<C> implements Headered {
	
	//attributes
	private final String header;
	private final DataType<C> dataType;
	
	//constructor
	public Column(final String header, final DataType<C> dataType) {
		
		Validator.assertThat(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
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
}
