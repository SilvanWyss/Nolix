//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
public abstract class BaseParametrizedReferenceType<
	IMPL,
	E extends IEntity<IMPL>
>
extends ParametrizedPropertyType<IMPL>
implements IBaseParametrizedReferenceType<IMPL, E> {
	
	//attribute
	private final Class<E> entityType;
	
	//constructor
	public BaseParametrizedReferenceType(final Class<E> entityType) {
		
		Validator.assertThat(entityType).thatIsNamed(LowerCaseCatalogue.ENTITY_TYPE).isNotNull();
		
		this.entityType = entityType;
	}
	
	//own imports
	@Override
	public final Class<E> getEntityType() {
		return entityType;
	}
}
