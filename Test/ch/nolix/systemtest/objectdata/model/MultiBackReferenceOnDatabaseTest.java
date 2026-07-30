/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.MultiBackReference;
import ch.nolix.system.objectdata.model.Reference;

/**
 * @author Silvan Wyss
 */
final class MultiBackReferenceOnDatabaseTest extends StandardTest {
  private static final class Plane extends Entity {
    public final MultiBackReference<Flight> flights = //
    MultiBackReference.forBackReferencedFieldNameAndBackReferenceableEntityTypes("plane", Flight.class);

    public Plane() {
      initialize();
    }
  }

  private static final class Flight extends Entity {
    public final Reference<Plane> plane = Reference.forEntityTypes(Plane.class);

    public Flight() {
      initialize();
    }
  }

  @Test
  void testCase_getAllStoredBackReferencedEntities_whenIsNewAndEmpty() {
    // setup step 1: create nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    // setup step 2: Creates and inserts plane.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);

    // execute
    final var result = a320.flights.getAllStoredBackReferencedEntities();

    // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_getAllStoredBackReferencedEntities_whenIsNewAndNotEmpty() {
    // setup step 1: create nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    // setup step 2: Creates and inserts planes and flights.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);
    final var fx2650 = new Flight();
    fx2650.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2650);
    final var fx2651 = new Flight();
    fx2651.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2651);

    // execute
    final var result = a320.flights.getAllStoredBackReferencedEntities();

    // verify
    expect(result).containsExactly(fx2650, fx2651);
  }

  @Test
  void testCase_getAllBackReferencedEntityIds_whenIsLoaded() {
    // setup step 1: create nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    // setup step 2: Creates and inserts and saves planes and flights.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);
    final var fx2650 = new Flight();
    fx2650.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2650);
    final var fx2651 = new Flight();
    fx2651.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2651);
    nodeDataAdapter.saveChanges();

    // execute
    final var loaded320 = nodeDataAdapter.getStoredTableByEntityType(Plane.class).getStoredEntityById(a320.getId());
    final var result = loaded320.flights.getAllBackReferencedEntityIds();

    // verify
    expect(result).containsExactlyEqualing(fx2650.getId(), fx2651.getId());
  }

  @Test
  void testCase_isSaved_whenIsEmpty() {
    // setup step 1: create nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    // setup step 2: Creates and inserts and saves plane.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);

    // setup verification
    expect(a320.flights.isEmpty()).isTrue();

    // execute & verify
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  @Test
  void testCase_isSaved_whenBackReferencedEntityIsDeleted() {
    // setup step 1: create nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    // setup step 2: Creates and inserts and saves plane and flight.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);
    final var fx2650 = new Flight();
    fx2650.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2650);
    nodeDataAdapter.saveChanges();

    // setup step 2: Delete flight of plane.
    final var loadedFx2650 = nodeDataAdapter.getStoredTableByEntityType(Flight.class)
      .getStoredEntityById(fx2650.getId());
    loadedFx2650.delete();

    // verify
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }
}
