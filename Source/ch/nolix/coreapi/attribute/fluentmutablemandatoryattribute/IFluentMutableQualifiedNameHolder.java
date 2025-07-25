package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IQualifiedNameHolder;

/**
 * A {@link IFluentMutableQualifiedNameHolder} is a {@link IQualifiedNameHolder}
 * whose name can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2024-01-19
 * @param <H> is the type of a {@link IFluentMutableQualifiedNameHolder}.
 */
public interface IFluentMutableQualifiedNameHolder<H extends IFluentMutableQualifiedNameHolder<H>>
extends IFluentMutableNameHolder<H>, IQualifiedNameHolder {
}
