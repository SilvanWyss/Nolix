/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.midschema.databasestructure;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;

/**
 * @author Silvan Wyss
 */
final class FixDatabasePropertyCatalogueTest extends StandardTest {
  @Test
  void testCase_numberOfEntityFields() {
    //execution
    final var result = FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS;

    //verification
    expect(result).isEqualTo(4);
  }
}
