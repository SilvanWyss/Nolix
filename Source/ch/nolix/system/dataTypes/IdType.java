//class
package ch.nolix.system.dataTypes;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class IdType extends BaseTechnicalType<Long> {
	
	//constructor
	public IdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.ID;
	}
}
