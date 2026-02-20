/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

import ch.nolix.baseapi.attribute.mandatoryattribute.IDatabaseNameHolder;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseTarget extends IAuthenticationServerTarget, IDatabaseNameHolder {
  //This interface is just an union of other interfaces.
}
