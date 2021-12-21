//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class GeneralEntity extends BaseEntity {
	
	//static method
	public GeneralEntity withTableName(final String tableName) {
		return new GeneralEntity(tableName);
	}
	
	//attribute
	private final String tableName;
	
	//constructor
	private GeneralEntity(final String tableName) {
		
		Validator.assertThat(tableName).thatIsNamed("table name").isNotBlank();
		
		this.tableName = tableName;
	}
	
	//method
	@Override
	public String getTableName() {
		return tableName;
	}
}
