//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.IContainer;

//class
public final class GeneralEntity extends BaseEntity {
	
	//static attribute
	private static final PropertyFromTableExtractor propertyFromTableExtractor = new PropertyFromTableExtractor();
	
	//static method
	public GeneralEntity forTable(final Table<GeneralEntity> table) {
		return new GeneralEntity(table);
	}
	
	//attribute
	private final String tableName;
	
	//constructor
	private GeneralEntity(final Table<GeneralEntity> table) {
		tableName = table.getName();
	}
	
	//method
	@Override
	public String getParentTableName() {
		return tableName;
	}
	
	//method
	@Override
	IContainer<Property> internalLoadProperties() {
		return propertyFromTableExtractor.createPropertiesFromTable(getParentTable());
	}
}
