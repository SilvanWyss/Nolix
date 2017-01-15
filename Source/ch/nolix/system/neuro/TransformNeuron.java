//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A transform neuron is a neuron that transform its single input to its output.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 80
 * @param <I> - The type of the input of a transform neuron.
 * @param <O> - The type of the output of a transform neuron.
 */
public final class TransformNeuron<I, O>
extends Neuron<I, O, TransformNeuron<I, O>> {
	
	//attribute
	private final IElementTakerElementGetter<I, O> transformer;
	
	//constructor
	/**
	 * Creates new transform neuron with the given input neuron and transformer.
	 * 
	 * @param inputNeuron
	 * @param transformer
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NullArgumentException if the given transform function is null.
	 */
	public TransformNeuron(
		final Neuron<?, I, ?> inputNeuron,
		final IElementTakerElementGetter<I, O> transformer
	) {
		//Calls other constructor.
		this(new InputNeuronoid<I>(inputNeuron), transformer);
	}
	
	//constructor
	/**
	 * Creates new transform neuron with the given input neuron and transform function.
	 * 
	 * @param inputNeuron
	 * @param transformer
	 * @throws NullArgumentException if the given input neuron is null.
	 * @throws NullArgumentException if the given transform function is null.
	 */
	public TransformNeuron(
		final InputNeuronoid<I> inputNeuron,
		final IElementTakerElementGetter<I, O> transformer
	) {
		//Checks if the given transform function is not null.
		ZetaValidator.supposeThat(transformer).thatIsNamed("transformern").isNotNull();
		
		this.transformer = transformer;
		addInputNeuron(inputNeuron);
	}
	
	//method
	/**
	 * @return the maximum number of input neurons of this transform neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 1;
	}

	//method
	/**
	 * @return the minimal number of input neurons of this transform neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 1;
	}
	
	//method
	/**
	 * Triggers this transform neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void trigger(final Processor processor) {
		setOutput(transformer.getOutput(getRefOneInput()));
	}
}
