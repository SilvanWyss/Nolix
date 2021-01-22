//package declaration
package ch.nolix.system.netneuron;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Closeable;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.baseneuron.BaseNeuron;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.client.StandardClient;

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
 * @param <I> is the type of the input of a net back neuron.
 */
public final class BackNetNeuron<I>
extends BaseNeuron<BackNetNeuron<I>, I, I>
implements Closeable {
	
	//constants
	private static final int MIN_INPUT_NEURON_COUNT = 0;
	private static final int MAX_INPUT_NEURON_COUNT = 1;
		
	//constant
	private static final String APPLICATION_NAME = "NetBackNeuron";
	
	//attributes
	private final NetServer netServer;
	private final Application<StandardClient> application;
	private final IElementTakerElementGetter<I, Node> transformator;
	
	//constructor
	/**
	 * Creates a new net back neuron
	 * that will listen to net front neurons on the given port
	 * and has the given transformer.
	 * 
	 * @param port
	 * @param transformator
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65'535].
	 * @throws ArgumentIsNullException if the given transformator is null.
	 */
	public BackNetNeuron(final int port, final IElementTakerElementGetter<I, Node> transformator) {
		//Asserts that the given transform is not null.
		Validator
		.assertThat(transformator)
		.thatIsNamed(VariableNameCatalogue.TRANSFORMATOR)
		.isNotNull();
		
		//Sets the transformer of this net back neuron.
		this.transformator = transformator;
		
		//Creates the net server of this net back neuron.
		netServer = new NetServer(port);
		
		//Creates the application of this net back neuron.
		application
		= new Application<>(
			APPLICATION_NAME,
			StandardClient.class,
			BackNetNeuronSession.class
		);
		
		netServer.addDefaultApplication(application);
	}
	
	//method
	/**
	 * Closes this net back neuron.
	 */
	@Override
	public void close() {
		netServer.close();
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net back neuron.
	 */
	@Override
	public int getMaxInputNeuronCount() {
		return MAX_INPUT_NEURON_COUNT;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net back neuron.
	 */
	@Override
	public int getMinInputNeuronCount() {
		return MIN_INPUT_NEURON_COUNT;
	}
	
	//method
	/**
	 * Lets this net back neuron fire.
	 */
	@Override
	public void internalUpdate() {
		
		//Handles the case that this net back neuron does not have an input neuron.
		if (getRefInputNeurons().isEmpty()) {
			internalSetOutput(null);
			
		//Handles the case that this net back neuron has an input neuron.
		} else {
			
			internalSetOutput(getRefOneInput());
			final Node output = transformator.getOutput(getRefOutput());
			
			//Iterates the clients of the application of this net back neuron.
			for (final StandardClient c : application.getRefClients()) {
				c.getRefCurrentSession().run("SetOutput", output.toString());
				c.getRefCurrentSession().run("Fire");
			}
		}
	}
	
	//method
	/**
	 * @return true if this net back neuron is closed.
	 */
	@Override
	public boolean isClosed() {
		return netServer.isClosed();
	}
}
