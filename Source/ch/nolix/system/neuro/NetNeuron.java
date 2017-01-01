/*
 * file:	NetNeuron.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	30
 */

//package declaration
package ch.nolix.system.neuro;

//own imports
import ch.nolix.common.specification.Specifiable;
import ch.nolix.system.application.ContextApplication;
import ch.nolix.system.application.StandardClient;

//class
public final class NetNeuron<O extends Specifiable>
extends Neuron<O, NetNeuron<O>> {
	
	//attribute
	private final ContextApplication<NetNeuron<O>, StandardClient> application;
	
	//constructor
	public NetNeuron(final int port) {
		
		//Creates application.
		application =
		new ContextApplication<NetNeuron<O>, StandardClient>(
			"Neuron",
			port,
			this,
			NetNeuronSession.class
		);
	}
	
	//method
	public void trigger(Processor processor) {
		
		if (getRefInputs().isEmpty()) {
			setOutput(null);
		}
		else {
			setOutput(getRefInputs().getRefFirst());
		}
		
		application.getRefClients().forEach(c -> c.invokeOnOriginMachine("Trigger"));
	}
}
