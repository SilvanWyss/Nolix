package ch.nolix.coreapi.programcontrol.trigger;

import ch.nolix.coreapi.state.staterequest.AlivenessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IRefreshableSubscriber extends AlivenessRequestable, Refreshable {
  //This interface is just an union of other interfaces.
}
