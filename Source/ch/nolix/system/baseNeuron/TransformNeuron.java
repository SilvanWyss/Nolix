//package declaration
package ch.nolix.system.baseNeuron;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.validator.Validator;

//class
/**
 * A transform neuron is a neuron that transform an input to an output.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 70
 * @param <I> The type of the input of a transform neuron.
 * @param <O> The type of the output of a transform neuron.
 */
public final class TransformNeuron<I, O>
extends BaseNeuron<TransformNeuron<I, O>, I, O> {
	
	//constants
	private static final int MIN_INPUT_NEURON_COUNT = 1;
	private static final int MAX_INPUT_NEURON_COUNT = 1;
	
	//attribute
	private final IElementTakerElementGetter<I, O> transformator;
	
	//constructor
	/**
	 * Creates a new transform neuron with the given input neuron and transformator.
	 * 
	 * @param inputNeuron
	 * @param transformator
	 * @throws ArgumentIsNullException if the given input neuron is null.
	 * @throws ArgumentIsNullException if the given transformator is null.
	 */
	public TransformNeuron(
		final BaseNeuron<?, ?, I> inputNeuron,
		final IElementTakerElementGetter<I, O> transformator
	) {
		//Checks if the given transformator is not null.
		Validator
		.suppose(transformator).
		thatIsNamed(VariableNameCatalogue.TRANSFORMATOR).
		isNotNull();
						
		addInputNeuron(inputNeuron);
		
		//Sets the transformator of this transform neuron.
		this.transformator = transformator;
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this transform neuron.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this transform neuron.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Lets this transform neuron fire.
	 */
	@Override
	public void fire() {
		internal_setOutput(transformator.getOutput(getRefOneInput()));
	}
}
