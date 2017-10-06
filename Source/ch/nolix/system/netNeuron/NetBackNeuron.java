//package declaration
package ch.nolix.system.netNeuron;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.client.StandardApplication;
import ch.nolix.system.client.StandardClient;
import ch.nolix.system.neuronoid.Neuronoid;

//class
/**
 * A net back neuron is a neuron that can be connected
 * from net front neurons on other processes or machines.
 * 
 * A net back neuron is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 120
 * @param <I> The type of the input of a net back neuron.
 */
public final class NetBackNeuron<I>
extends Neuronoid<I, Object, NetBackNeuron<I>>
implements Closable {
		
	//application name
	private static final String APPLICATION_NAME = "NetBackNeuron";
	
	//attributes
	private final IElementTakerElementGetter<I, StandardSpecification> transformer;
	private final NetServer netServer;
	private final Application<StandardClient> application;
	
	//constructor
	/**
	 * Creates new net back neuron with the given port and transformer.
	 * 
	 * @param port
	 * @param transformer
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65'535].
	 * @throws NullArgumentException if the given transfromer is null.
	 */
	public NetBackNeuron(
		final int port,
		final IElementTakerElementGetter<I, StandardSpecification> transformer)
	{
		//Checks if the given transform is not null.
		Validator.suppose(transformer).thatIsNamed("transformer").isNotNull();
		
		//Sets the transformer of this net back neuron.
		this.transformer = transformer;
		
		//Creates the net server of this net back neuron.
		netServer = new NetServer(port);
		application
		= new StandardApplication<StandardClient>(
			APPLICATION_NAME,
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
	 * @return true if this net back neuron is closed.
	 */
	public boolean isClosed() {
		return netServer.isClosed();
	}

	//method
	/**
	 * @return the maximum number of input neurons of this net back neuron.
	 */
	protected int getMaxInputNeuronCount() {
		return 1;
	}

	//method
	/**
	 * @return the minimum number of input neurons of this net back neuron.
	 */
	protected int getMinInputNeuronCount() {
		return 0;
	}
	
	public void internal_fire() {
		if (getRefInputs().isEmpty()) {
			setOutput(null);
		}
		else {
			setOutput(transformer.getOutput(getRefOneInput()));
			application.getRefClients().forEach(
					
				c -> {
					c.run("SetOutput(" + getRefOutput() + ")");
					c.run("Trigger");
				}
			);
		}
	}
}
