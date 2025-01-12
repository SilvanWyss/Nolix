package ch.nolix.systemapitest.rawschemaapitest.databasestrucureapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;

final class FixDatabasePropertyCatalogueTest extends StandardTest {

  @Test
  void testCase_numberOfEntityFields() {

    //execution
    final var result = FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS;

    //verification
    expect(result).isEqualTo(4);
  }
}
