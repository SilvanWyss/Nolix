//package declaration
package ch.nolix.core.factory;

//own imports
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2018-06
 * @lines 80
 * @param <I> The type of the inputs of a {@link InstanceCreator}.
 * @param <O> The type of the instances a {@link InstnaceCreator} can create.
 */
final class InstanceCreator<I, O> {

	//attributes
	private final String instanceType;
	private final IElementTakerElementGetter<I, O> instanceCreatorFunction;
	
	//constructor
	/**
	 * Creates a new {@link InstanceCreator} with the given instance type and instance creator function.
	 * 
	 * @param instanceType
	 * @param instanceCreatorFunction
	 * @throws NullArgumentException if the given instance type is null.
	 * @throws EmptyArgumentException if the given instance type is empty.
	 * @throws NullArgumentException if the given instance creator function is null.
	 */
	public InstanceCreator(
		final String instanceType,
		IElementTakerElementGetter<I, O> instanceCreatorFunction
	) {
		
		//Checks if the given instance type is not null or empty.
		Validator
		.suppose(instanceType)
		.thatIsNamed("instance type")
		.isNotEmpty();
		
		//Checks if the given instance creator function is not null.
		Validator
		.suppose(instanceCreatorFunction)
		.thatIsNamed("instance creator function")
		.isNotNull();
		
		//Sets the instance type of the current instance creator.
		this.instanceType = instanceType;
		
		//Sets the instnace creator function of the current instance creator.
		this.instanceCreatorFunction = instanceCreatorFunction;
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link InstanceCreator} can create an instance of the given type.
	 */
	public boolean canCreateInstanceOf(final String type) {
		return instanceType.equals(type);
	}
	
	//method
	/**
	 * @param input
	 * @return a new instance from the current {@link InstanceCreator} from the given input.
	 */
	public O createInstance(final I input) {
		return instanceCreatorFunction.getOutput(input);
	}
	
	//method
	/**
	 * @return the instance type of the current {@link InstanceCreator}.
	 */
	public String getInstanceType() {
		return instanceType;
	}
}
