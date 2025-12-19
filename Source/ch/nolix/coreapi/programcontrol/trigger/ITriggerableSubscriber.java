package ch.nolix.coreapi.programcontrol.trigger;

import ch.nolix.coreapi.state.staterequest.AlivenessRequestable;

/**
 * @author Silvan Wyss
 */
public interface ITriggerableSubscriber extends AlivenessRequestable, Triggerable {
  //This interface is just an union of other interfaces.
}
