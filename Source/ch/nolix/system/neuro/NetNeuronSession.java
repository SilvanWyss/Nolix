/*
 * file:	StandardNeuron.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	30
 */

package ch.nolix.system.neuro;

import ch.nolix.common.specification.Specifiable;
import ch.nolix.system.application.ContextSession;
import ch.nolix.system.application.StandardClient;

class NetNeuronSession<O extends Specifiable>
extends ContextSession<StandardClient, NetNeuron<O>> {
	
	public NetNeuronSession(final NetNeuron<O> neuron) {
		
		super(neuron);
	}
	
	public Object Output() {
		return getRefContext().getRefOutput();
	}
}
