//package declaration
package ch.nolix.system.netNeuron;

import ch.nolix.common.constants.IPv6Catalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Closable;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.client.StandardClient;
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * A net front neuron is a neuron
 * that connects to a net back neuron.
 * 
 * A net front neuron is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 140
 * @param <O> The type of the output of a net front neuron.
 */
public final class FrontNetNeuron<O>
extends Neuronoid<FrontNetNeuron<O>, Object, O>
implements Closable {

	//limits
	private static final int MIN_INPUT_NEURON_COUNT = 0;
	private static final int MAX_INPUT_NEURON_COUNT = 0;
	
	//attributes
	private final StandardClient client;
	private final IElementTakerElementGetter<Node, O> transformator;
	
	//constructor
	/**
	 * Creates a new net front neuron
	 * that will connect to the net back neuron
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @param transformator
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65'535].
	 * @throws ArgumentIsNullException if the given transformator is null.
	 */
	public FrontNetNeuron(
		final int port,
		IElementTakerElementGetter<Node, O> transformator
	) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, transformator);
	}
	
	//constructor
	/**
	 * Creates a new net front neuron
	 * that will connect to the net back neuron
	 * on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param transformator
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65'535].
	 * @throws ArgumentIsNullException if the given transformator is null.
	 */
	public FrontNetNeuron(
		final String ip,
		final int port,
		IElementTakerElementGetter<Node, O> transformator
	) {
		
		//Checks if the given transformer is not null.
		Validator
		.suppose(transformator)
		.thatIsNamed(VariableNameCatalogue.TRANSFORMATOR)
		.isNotNull();
				
		//Sets the transformator of this net neuron.
		this.transformator = transformator;
		
		//Creates the client of this net front neuron.
		client = new StandardClient(ip, port, new FrontNetNeuronSession<O>(this));
	}

	//method
	/**
	 * Closes this net front neuron.
	 */
	@Override
	public void close() {
		client.close();
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net front neuron.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net front neuron.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Lets this net front neuron fire.
	 */
	@Override
	public void fire() {}
	
	//method
	/**
	 * @return true if this net front neuron is closed.
	 */
	@Override
	public boolean isClosed() {
		return client.isClosed();
	}
	
	//package-visible method
	/**
	 * Sets the output of this net front neuron.
	 * 
	 * @param output
	 */
	void setOutput(final Node output) {
		internal_setOutput(transformator.getOutput(output));
	}
	
	//package-visible method
	/**
	 * Sets the output of this net front neuron.
	 * 
	 * @param output
	 */
	void setOutput(final String output) {
		internal_setOutput(
			transformator.getOutput(Node.fromString(output))
		);
	}
}
