//package declaration
package ch.nolix.system.netNeuron;

import ch.nolix.core.constants.IPv6Catalogue;
//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.StandardClient;
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * A net front neuron is a neuron that connects
 * to a net back neuron on an other process or machine.
 * 
 * A net front neuron is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 * @param <O> The type of the output of a net front neuron.
 */
public final class NetFrontNeuron<O>
extends Neuronoid<NetFrontNeuron<O>, Object, O>
implements Closable {

	//attributes
	private final StandardClient client;
	private final IElementTakerElementGetter<StandardSpecification, O> transformer;
	
	//constructor
	/**
	 * Creates new net front neuron
	 * that will connect to the net back neuron
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @param transformer
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65'535].
	 * @throws NullArgumentException if the given transformer is null.
	 */
	public NetFrontNeuron(
		final int port,
		IElementTakerElementGetter<StandardSpecification, O> transformer
	) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, transformer);
	}	
	
	//constructor
	/**
	 * Creates new net front neuron
	 * that will connect to the net back neuron
	 * on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param transformer
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65'535].
	 * @throws NullArgumentException if the given transformer is null.
	 */
	public NetFrontNeuron(
		final String ip,
		final int port,
		IElementTakerElementGetter<StandardSpecification, O> transformer
	) {
		
		//Checks if the given transformer is not null.
		Validator.suppose(transformer).thatIsNamed("transformer").isNotNull();
				
		//Sets the transformer of this net neuron.
		this.transformer = transformer;
		
		//Creates the client of this net front neuron.
		client = new StandardClient(ip, port);
		client.setSession(new NetFrontNeuronSession<O>(this));	
	}	

	//method
	/**
	 * Closes this net front neuron.
	 */
	public void close() {
		client.close();
	}

	//method
	/**
	 * @return true if this net front neuron is closed.
	 */
	public boolean isClosed() {
		return client.isClosed();
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net front neuron.
	 */
	public int getMaxInputNeuronCount() {
		return 0;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net front neuron.
	 */
	public int getMinInputNeuronCount() {
		return 0;
	}
	
	//method
	/**
	 * Triggers this net front neuron using the given processor.
	 * 
	 * @param processor
	 */
	protected void internal_fire() {
		//getRefTriggerableNeurons().forEach(tn -> processor.addNeuron(tn));
	}
	
	//package-visible method
	/**
	 * Sets the output of this net front neuron.
	 * 
	 * @param output
	 */
	void setOutput(final StandardSpecification output) {
		setOutput(transformer.getOutput(output));
	}
	
	//package-visible method
	/**
	 * Sets the output of this net front neuron.
	 * 
	 * @param output
	 */
	void setOutput(final String output) {
		setOutput(transformer.getOutput(new StandardSpecification(output)));
	}
}
