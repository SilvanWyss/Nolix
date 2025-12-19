package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IQualifiedNameHolder;

/**
 * A {@link IFluentMutableQualifiedNameHolder} is a {@link IQualifiedNameHolder}
 * whose name can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableQualifiedNameHolder}.
 */
public interface IFluentMutableQualifiedNameHolder<H extends IFluentMutableQualifiedNameHolder<H>>
extends IFluentMutableNameHolder<H>, IQualifiedNameHolder {
  //This interface is just an union of other interfaces.
}
