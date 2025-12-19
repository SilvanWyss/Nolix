package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IQualifiedNameHolder;

/**
 * A {@link IMutableQualifiedNameHolder} is a {@link IQualifiedNameHolder} whose
 * name can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableQualifiedNameHolder extends IQualifiedNameHolder, IMutableNameHolder {
  //This interface is just an union of other interfaces.
}
