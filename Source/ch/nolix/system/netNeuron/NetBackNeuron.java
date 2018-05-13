//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.client.StandardApplication;
import ch.nolix.system.client.StandardClient;
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * A net back neuron is a neuron that can be connected
 * from net front neurons on:
 * -the same process
 * -other processes on the same machine
 * -processes on other machines
 * 
 * A net back neuron is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 140
 * @param <I> The type of the input of a net back neuron.
 */
public final class NetBackNeuron<I>
extends Neuronoid<NetBackNeuron<I>, I, I>
implements Closable {
	
	//limits
	private static final int MIN_INPUT_NEURON_COUNT = 0;
	private static final int MAX_INPUT_NEURON_COUNT = 1;
		
	//constant
	private static final String APPLICATION_NAME = "NetBackNeuron";
	
	//attributes
	private final NetServer netServer;
	private final Application<StandardClient> application;
	private final IElementTakerElementGetter<I, StandardSpecification> transformator;
	
	//constructor
	/**
	 * Creates a new net back neuron
	 * that will listen to net front neurons on the given port
	 * and has the given transformer.
	 * 
	 * @param port
	 * @param transformator
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65'535].
	 * @throws NullArgumentException if the given transformator is null.
	 */
	public NetBackNeuron(
		final int port,
		final IElementTakerElementGetter<I, StandardSpecification> transformator)
	{
		//Checks if the given transform is not null.
		Validator
		.suppose(transformator)
		.thatIsNamed(VariableNameCatalogue.TRANSFORMATOR)
		.isNotNull();
		
		//Sets the transformer of this net back neuron.
		this.transformator = transformator;
		
		//Creates the net server of this net back neuron.
		netServer = new NetServer(port);
		
		//Creates the application of this net back neuron.
		application
		= new StandardApplication<StandardClient>(
			APPLICATION_NAME,
			StandardClient.class,
			NetBackNeuronSession.class
		);
		
		netServer.addArbitraryApplication(application);
	}
	
	//method
	/**
	 * Closes this net back neuron.
	 */
	public void close() {
		netServer.close();
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net back neuron.
	 */
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net back neuron.
	 */
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Lets this net back neuron fire.
	 */
	public void internal_fire() {
		
		//Handles the case that this net back neuron has no input neuron.
		if (getRefInputNeurons().isEmpty()) {
			internal_setOutput(null);
		}
		
		//Handles the case that this net back neuron has an input neuron.
		else {
			
			internal_setOutput(getRefOneInput());			
			final StandardSpecification output = transformator.getOutput(getRefOutput());
			
			//Iterates the clients of the application of this net back neuron.
			for (final StandardClient c : application.getRefClients()) {
				c.run("SetOutput(" + output + ")");
				c.run("Fire");
			}
		}
	}
	
	//method
	/**
	 * @return true if this net back neuron is closed.
	 */
	public boolean isClosed() {
		return netServer.isClosed();
	}
}
