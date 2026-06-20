/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseTarget extends IAuthenticationServerTarget, DatabaseNameHolder {
  //This interface is a dedicated union of other interfaces.
}
