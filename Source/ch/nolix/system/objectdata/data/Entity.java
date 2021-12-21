//package declaration
package ch.nolix.system.objectdata.data;

//class
public abstract class Entity extends BaseEntity {
	
	//method
	@Override
	public final String getTableName() {
		return getClass().getSimpleName();
	}
}
