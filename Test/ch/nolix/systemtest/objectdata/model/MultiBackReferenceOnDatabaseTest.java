package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.MultiBackReference;
import ch.nolix.system.objectdata.model.Reference;

final class MultiBackReferenceOnDatabaseTest extends StandardTest {

  private static final class Plane extends Entity {

    public final MultiBackReference<Flight> flights = MultiBackReference
      .forBackReferencedEntityTypeAndBaseReference(Flight.class, "plane");

    public Plane() {
      initialize();
    }
  }

  private static final class Flight extends Entity {

    public final Reference<Plane> plane = Reference.forEntity(Plane.class);

    public Flight() {
      initialize();
    }
  }

  @Test
  void testCase_getAllStoredBackReferencedEntities_whenIsNewAndEmpty() {

    //setup part 1: Creates nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    //setup part 2: Creates and inserts plane.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);

    //execution
    final var result = a320.flights.getAllStoredBackReferencedEntities();

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getAllStoredBackReferencedEntities_whenIsNewAndNotEmpty() {

    //setup part 1: Creates nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    //setup part 2: Creates and inserts planes and flights.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);
    final var fx2650 = new Flight();
    fx2650.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2650);
    final var fx2651 = new Flight();
    fx2651.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2651);

    //execution
    final var result = a320.flights.getAllStoredBackReferencedEntities();

    //verification
    expect(result).containsExactly(fx2650, fx2651);
  }

  @Test
  void testCase_getAllBackReferencedEntityIds_whenIsLoaded() {

    //setup part 1: Creates nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    //setup part 2: Creates and inserts and saves planes and flights.
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

    //execution
    final var loaded320 = nodeDataAdapter.getStoredTableByEntityType(Plane.class).getStoredEntityById(a320.getId());
    final var result = loaded320.flights.getAllBackReferencedEntityIds();

    //verification
    expect(result).containsExactlyEqualing(fx2650.getId(), fx2651.getId());
  }

  @Test
  void testCase_isSaved_whenIsEmpty() {

    //setup part 1: Creates nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    //setup part 2: Creates and inserts and saves plane.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);

    //setup verification
    expect(a320.flights.isEmpty()).isTrue();

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  @Test
  void testCase_isSaved_whenBackReferencedEntityIsDeleted() {

    //setup part 1: Creates nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Plane.class, Flight.class);

    //setup part 2: Creates and inserts and saves plane and flight.
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var a320 = new Plane();
    nodeDataAdapter.insertEntity(a320);
    final var fx2650 = new Flight();
    fx2650.plane.setEntity(a320);
    nodeDataAdapter.insertEntity(fx2650);
    nodeDataAdapter.saveChanges();

    //setup part 2: Delete flight of plane.
    final var loadedFx2650 = nodeDataAdapter.getStoredTableByEntityType(Flight.class)
      .getStoredEntityById(fx2650.getId());
    loadedFx2650.delete();

    //verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }
}
