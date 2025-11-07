package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IQualifiedNameHolder;

/**
 * A {@link IMutableQualifiedNameHolder} is a {@link IQualifiedNameHolder} whose
 * name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2024-01-19
 */
public interface IMutableQualifiedNameHolder extends IQualifiedNameHolder, IMutableNameHolder {
  //This interface is just an union of other interfaces.
}
