/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.trigger;

import ch.nolix.baseapi.state.staterequest.AlivenessRequestable;

/**
 * @author Silvan Wyss
 */
public interface ITriggerableSubscriber extends AlivenessRequestable, Triggerable {
  //This interface is just an union of other interfaces.
}
