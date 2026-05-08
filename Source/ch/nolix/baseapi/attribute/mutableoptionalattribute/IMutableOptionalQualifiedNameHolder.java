/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalQualifiedNameHolder;

/**
 * A {@link IMutableOptionalQualifiedNameHolder} is a
 * {@link IOptionalQualifiedNameHolder} whose qualified name can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalQualifiedNameHolder extends IMutableOptionalNameHolder, IOptionalQualifiedNameHolder {
  //This interface is a dedicated union of other interfaces.
}
