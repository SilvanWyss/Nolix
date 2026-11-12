/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.database.databaseattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.database.databaseattribute.CardinalityHolder;
import ch.nolix.systemapi.database.databaseproperty.BaseCardinality;
import ch.nolix.systemapi.database.databaseproperty.Cardinality;

/**
 * @author Silvan Wyss
 */
final class CardinalityHolderTest extends StandardTest {
  @Test
  void testCase_getBaseCardinality_whenCardinalityIsToMany() {
    // setup
    final var testUnit = Mockito.mock(CardinalityHolder.class);
    Mockito.when(testUnit.getBaseCardinality()).thenCallRealMethod();
    Mockito.when(testUnit.getCardinality()).thenReturn(Cardinality.TO_MANY);

    // execute
    final var result = testUnit.getBaseCardinality();

    // verify
    expect(result).is(BaseCardinality.MULTI);
  }

  @Test
  void testCase_getBaseCardinality_whenCardinalityIsToOne() {
    // setup
    final var testUnit = Mockito.mock(CardinalityHolder.class);
    Mockito.when(testUnit.getBaseCardinality()).thenCallRealMethod();
    Mockito.when(testUnit.getCardinality()).thenReturn(Cardinality.TO_ONE);

    // execute
    final var result = testUnit.getBaseCardinality();

    // verify
    expect(result).is(BaseCardinality.SINGLE);
  }

  @Test
  void testCase_getBaseCardinality_whenCardinalityIsToOneOrNone() {
    // setup
    final var testUnit = Mockito.mock(CardinalityHolder.class);
    Mockito.when(testUnit.getBaseCardinality()).thenCallRealMethod();
    Mockito.when(testUnit.getCardinality()).thenReturn(Cardinality.TO_ONE_OR_NONE);

    // execute
    final var result = testUnit.getBaseCardinality();

    // verify
    expect(result).is(BaseCardinality.SINGLE);
  }
}
