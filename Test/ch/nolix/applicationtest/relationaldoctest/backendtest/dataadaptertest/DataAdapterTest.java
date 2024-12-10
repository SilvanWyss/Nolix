package ch.nolix.applicationtest.relationaldoctest.backendtest.dataadaptertest;

import org.junit.jupiter.api.Test;

import ch.nolix.application.relationaldoc.backend.dataadapter.DataAdapter;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;

final class DataAdapterTest extends StandardTest {

  @Test
  void testCase_createEmptyCopy_whenHasChanges() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var testUnit = DataAdapter.forNodeDatabase(nodeDatabase);
    GlobalSequencer.forCount(5).run(testUnit::createObject);
    final var originTopLevelObjects = testUnit.getStoredTopLevelObjects();

    //setup verification
    expect(testUnit.hasChanges());

    //execution
    final var result = testUnit.createEmptyCopy();

    //verification
    expectNot(result.hasChanges());
    expect(result.getStoredTopLevelObjects()).isEmpty();
    expect(testUnit.getStoredTopLevelObjects().containsExactlyInSameOrder(originTopLevelObjects));
  }
}