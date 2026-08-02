/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.refresh;

import ch.nolix.baseapi.state.staterequest.AlivenessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IRefreshableSubscriber extends AlivenessRequestable, Refreshable {
  // This interface is a dedicated union of other interfaces.
}
