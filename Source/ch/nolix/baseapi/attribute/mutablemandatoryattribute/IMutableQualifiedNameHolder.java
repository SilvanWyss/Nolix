/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.IQualifiedNameHolder;

/**
 * A {@link IMutableQualifiedNameHolder} is a {@link IQualifiedNameHolder} whose
 * name can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableQualifiedNameHolder extends IQualifiedNameHolder, IMutableNameHolder {
  //This interface is just an union of other interfaces.
}
