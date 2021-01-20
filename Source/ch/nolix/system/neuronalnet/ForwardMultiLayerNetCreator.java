//package declaration
package ch.nolix.system.neuronalnet;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.baseneuron.InputConnection;
import ch.nolix.system.neuron.Neuron;

//class
/**
 * A {@link ForwardMultiLayerNetCreator} can create {@link NeuronalNet}s, that:
 * -Have a specific number of layers.
 * -Have a specific numbers of {link Neuron}s per layer.
 * -Have the effect that each {@link Neuron} of a layer
 *  contains all {@link Neuron}s of the previous layer as input neurons.
 * -Are triggered linearly through its layers.
 * 
 * @author Silvan Wyss
 * @date 2017-01-15
 * @lines 170
 * @param <IO> is the type of the inputs and output of the {@link NeuronalNet}s
 * a {@link ForwardMultiLayerNetCreator} creates.
 */
public final class ForwardMultiLayerNetCreator<IO> implements INeuronalNetCreator<IO> {
	
	//attributes
	private int layerCount = 1;
	private int neuronsPerLayer = 1;
	private IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> outputFunction = c -> null;
	
	//method
	/**
	 * @return a new {@link ForwardMultiLayerNetCreator}.
	 */
	@Override
	public NeuronalNet<IO> createNeuronalNet() {
			
		final var inputNeurons = new LinkedList<Neuron<IO>>();
		
		var previousLayer = new LinkedList<Neuron<IO>>();
		for (int i = 1; i <= getLayerCount(); i++) {
			
			var currentLayer = new LinkedList<Neuron<IO>>();
			
			for (int j = 1; j <= getNeuronsPerLayer(); j++) {
				
				//Creates the j-th Neuron of the i-th layer.
				final var neuron = new Neuron<IO>();
				if (i == 1) {
					inputNeurons.addAtEnd(neuron);
				} else {
					previousLayer.forEach(neuron::addInputNeuron);
				}
				neuron.setOutputFunction3(outputFunction);
				
				currentLayer.addAtEnd(neuron);
			}
			
			previousLayer = currentLayer;
		}
		
		return new NeuronalNet<>(inputNeurons, previousLayer);
	}
	
	//method
	/**
	 * @return the number of layers of the {@link NeuronalNet}s
	 * the current {@link ForwardMultiLayerNetCreator} creates.
	 */
	public int getLayerCount() {
		return layerCount;
	}
	
	//method
	/**
	 * @return the number of {@link Neuron}s per layer of the {@link NeuronalNet}s
	 * the current {@link ForwardMultiLayerNetCreator} creates.
	 */
	public int getNeuronsPerLayer() {
		return neuronsPerLayer;
	}
	
	//method
	/**
	 * Sets the number of layers of the {@link NeuronalNet}s the current {@link ForwardMultiLayerNetCreator} creates.
	 * 
	 * @param layerCount
	 * @return the current {@link ForwardMultiLayerNetCreator}r.
	 * @throws NonPositiveArgumentException if the given layerCount is not positive.
	 */
	public ForwardMultiLayerNetCreator<IO> setLayerCount(final int layerCount) {
		
		//Asserts that the given layerCount is positive.
		Validator.assertThat(layerCount).thatIsNamed("number of layers").isPositive();
		
		this.layerCount = layerCount;
		
		return this;
	}
	
	//method
	/**
	 * Sets the number of {@link Neuron}s per layer of the {@link NeuronalNet}s
	 * the current {@link ForwardMultiLayerNetCreator} creates.
	 * 
	 * @param neuronsPerLayer
	 * @return the current {@link ForwardMultiLayerNetCreator}.
	 * @throws NonPositiveArgumentException if the given neuronsPerLayer is not positive.
	 */
	public ForwardMultiLayerNetCreator<IO> setNeuronsPerLayer(final int neuronsPerLayer) {
		
		//Asserts that the given neuronsPerLayer is positive.
		Validator.assertThat(neuronsPerLayer).thatIsNamed("neurons per layer").isPositive();
		
		this.neuronsPerLayer = neuronsPerLayer;
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of the {@link Neuron}s of the {@link NeuronalNet}s
	 * the current {@link ForwardMultiLayerNetCreator} creates.
	 * 
	 * @param outputFunction
	 * @return the current {@link ForwardMultiLayerNetCreator}.
	 * @throws ArgumentIsNullException if the given outputFunction is null.
	 */
	public ForwardMultiLayerNetCreator<IO> setOutputFunction(
		final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction
	) {
		
		//Asserts that the given outputFunction is not null.
		Validator.assertThat(outputFunction).thatIsNamed("output function").isNotNull();
		
		//Sets the outputFunctionn of the current ForwardMultiLayerNetCreator.
		this.outputFunction =
		in -> {
			
			//Creates input list.
			final var inputs = new LinkedList<IO>();
			for (InputConnection<IO> n: in) {
				inputs.addAtEnd(n.getRefInputNeuronOutput());
			}
			
			return outputFunction.getOutput(inputs);
		};
		
		return this;
	}
	
	//method
	/**
	 * Sets the weight output function of the current {@link ForwardMultiLayerNetCreator}.
	 * 
	 * @param weightOutputFunction
	 * @return the current {@link ForwardMultiLayerNetCreator}.
	 * @throws ArgumentIsNullException if the given weightOutputFunction is null.
	 */
	public ForwardMultiLayerNetCreator<IO> setWeightOutputFunction(
		final IElementTakerElementGetter<Iterable<InputConnection<IO>>, IO> weightOutputFunction
	) {
		
		//Asserts that the given weightOutputFunction is not null.
		Validator.assertThat(weightOutputFunction).thatIsNamed("weight output function").isNotNull();
		
		outputFunction = weightOutputFunction;
		
		return this;
	}
}
