/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.trigger;

import ch.nolix.baseapi.generalstate.staterequest.AlivenessRequestable;

/**
 * @author Silvan Wyss
 */
public interface ITriggerableSubscriber extends AlivenessRequestable, Triggerable {
  // This interface is a dedicated union of other interfaces.
}
