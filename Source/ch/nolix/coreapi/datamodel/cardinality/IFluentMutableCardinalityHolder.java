package ch.nolix.coreapi.datamodel.cardinality;

/**
 * A {@link IFluentMutableCardinalityHolder} is a {@link ICardinalityHolder}
 * whose {@link Cardinality} can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableCardinalityHolder}.
 */
public interface IFluentMutableCardinalityHolder<H extends IFluentMutableCardinalityHolder<H>>
extends ICardinalityHolder {
  /**
   * Sets the {@link Cardinality} of the current
   * {@link IFluentMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link IFluentMutableCardinalityHolder}.
   * @throws RuntimeException if the given cardinality is null.
   */
  H setCardinality(Cardinality cardinality);
}
