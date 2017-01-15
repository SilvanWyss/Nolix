//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A forward multi layer net creator can create forward multi layer neuronal nets, that:
 * -Have a specific number of layers.
 * -Have a specific numbers of neurons per layer.
 * -Have the property that each neuron of a layer contains all neurons of the previous layer as input neurons.
 * -Are triggered linearly through its layers.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 130
 * @param <IO> - The type of the inputs and output of the neuronal nets a forward multi layer net creator creates.
 */
public final class ForwardMultiLayerNetCreator<IO> implements INeuronalNetCreator<IO> {

	//attributes
	private int layerCount = 1;
	private int neuronsPerLayer = 1;
	private IElementTakerElementGetter<Iterable<IO>, IO> outputFunction = c -> null;
	
	//method
	/**
	 * @return a new forward multi layer neoronal net.
	 */
	public NeuronalNet<IO> createNeuronalNet() {
			
		final List<StandardNeuron<IO>> inputNeurons = new List<StandardNeuron<IO>>();
		
		List<StandardNeuron<IO>> previousLayer = new List<StandardNeuron<IO>>();
		Neuron<?, ?, ?> triggeringNeuron = null;
		for (int i = 1; i <= getLayerCount(); i++) {
			
			List<StandardNeuron<IO>> currentLayer = new List<StandardNeuron<IO>>();
			
			for (int j = 1; j <= getNeuronsPerLayer(); j++) {
				
				//Creates the j-th neuron of the i-th layer.
				final StandardNeuron<IO> neuron = new StandardNeuron<IO>();
				if (i == 1) {
					inputNeurons.addAtEnd(neuron);
				}	
				else {
					previousLayer.forEach(n -> neuron.addInputNeuron(n));
				}		
				neuron.setOutputFunction(outputFunction);
				if (triggeringNeuron != null) {
					neuron.addTriggeringNeuron(triggeringNeuron);
				}
				
				currentLayer.addAtEnd(neuron);
				triggeringNeuron = neuron;
			}
			
			previousLayer = currentLayer;
		}
		
		return new NeuronalNet<IO>(inputNeurons, previousLayer, inputNeurons.getRefFirst());
	}
	
	//method
	/**
	 * @return the number of layers of the neuronal nets this forward multi layer net creator creates.
	 */
	public int getLayerCount() {
		return layerCount;
	}
	
	//method
	/**
	 * @return the number of neurons per layer of the neuronal nets this forward multi layer net creator creates.
	 */
	public int getNeuronsPerLayer() {
		return neuronsPerLayer;
	}
	
	//method
	/**
	 * Sets the number of layers of the neuronal nets this forward multi layer net creator creates.
	 * 
	 * @param layerCount
	 * @return this forward multi layer net creator.
	 * @throws NonPositiveArgumentException if the given layer count is not positive.
	 */
	public ForwardMultiLayerNetCreator<IO> setLayerCount(final int layerCount) {
		
		//Checks if the given layer count is positive.
		ZetaValidator.supposeThat(layerCount).thatIsNamed("number of layers").isPositive();
		
		this.layerCount = layerCount;
		
		return this;
	}
	
	//method
	/**
	 * Sets the number of neurons per layer of the neuronal nets this forward multi layer net creator creates.
	 * 
	 * @param neuronsPerLayer
	 * @return this forward multi layer net creator.
	 * @throws NonPositiveArgumentException if the given neurons per layer is not positive.
	 */
	public ForwardMultiLayerNetCreator<IO> setNeuronsPerLayer(final int neuronsPerLayer) {
		
		//Checks if the given layer count is positive.
		ZetaValidator.supposeThat(neuronsPerLayer).thatIsNamed("neurons per layer").isPositive();
		
		this.neuronsPerLayer = neuronsPerLayer;
		
		return this;
	}
	
	//method
	/**
	 * Sets the output function of the neurons of the neuronal nets this forward multi layer net creator creates.
	 * 
	 * @param outputFunction
	 * @return this forward multi layer net creator.
	 * @throws NullArgumentException if the given output function is null.
	 */
	public ForwardMultiLayerNetCreator<IO> setOutputFunction(final IElementTakerElementGetter<Iterable<IO>, IO> outputFunction) {
		
		//Checks if the given output function is not null.
		ZetaValidator.supposeThat(outputFunction).thatIsNamed("output function").isNotNull();
		
		this.outputFunction = outputFunction;
		
		return this;
	}
}
