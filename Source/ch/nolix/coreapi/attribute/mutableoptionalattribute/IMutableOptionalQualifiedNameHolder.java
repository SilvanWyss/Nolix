/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalQualifiedNameHolder;

/**
 * A {@link IMutableOptionalQualifiedNameHolder} is a
 * {@link IOptionalQualifiedNameHolder} whose qualified name can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalQualifiedNameHolder extends IMutableOptionalNameHolder, IOptionalQualifiedNameHolder {
  //This interface is just an union of other interfaces.
}
