//package declaration
package ch.nolix.system.neuronoid;

//own import
import ch.nolix.core.validator2.Validator;

//package-visible abstract class
/**
 * A connection provides restricted access to a connected neuron.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 10
 * @param <I> The type of the inputs of a connected neuron of a connection.
 * @param <O> the type of the output of a connected neuron of a connection.
 */
abstract class Connection<C extends Connection<C, I, O>, I, O> {

	//attribute
	private final Neuronoid<?, I, O> connectedNeuron;
	
	//constructor
	/**
	 * Creates a new connection with the given connected neuron.
	 * 
	 * @param connectedNeuron
	 * @throws NullArgumentException if the given connected neuron is null.
	 */
	public Connection(final Neuronoid<?, I, O> connectedNeuron) {
		
		//Checks if the given connected neuron is not null.
		Validator
		.suppose(connectedNeuron)
		.thatIsNamed("connected neuron")
		.isNotNull();
	
		//Sets the connected neuron of this connection.
		this.connectedNeuron = connectedNeuron;
	}
	
	public final C addInputNeuron(final Neuronoid<?, ?, I> inputNeuron) {
		
		connectedNeuron.addInputNeuron(inputNeuron);
		
		return getInstance();
	}
	
	//method
	/**
	 * @param neuron
	 * @return true if the connected neuron of this connection is the given neuron.
	 */
	public final boolean isSameAs(final Neuronoid<?, ?, ?> neuron) {
		return (connectedNeuron == neuron);
	}
	
	@SuppressWarnings("unchecked")
	protected final C getInstance() {
		return (C)this;
	}
	
	//package-visible method
	/**
	 * @return the connected neuron of this connection.
	 */
	final Neuronoid<?, I, O> getConnectedNeuron() {
		return connectedNeuron;
	}
}
