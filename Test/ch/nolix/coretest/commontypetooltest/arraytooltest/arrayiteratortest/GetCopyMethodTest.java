package ch.nolix.coretest.commontypetooltest.arraytooltest.arrayiteratortest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.arraytool.ArrayIterator;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GetCopyMethodTest extends StandardTest {

  @Test
  void testCase_getCopy_whenIsAtStartIndex() {

    //setup part 1: Creates array.
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var element6 = new VoidObject();
    final var array = new Object[] { element1, element2, element3, element4, element5, element6 };

    //setup part 2: Creates testUnit.
    final var testUnit = ArrayIterator.forArray(array);

    //execution
    final var result = testUnit.createCopy();

    //verification part 1: Verifies testUnit.
    expect(testUnit.next()).is(element1);
    expect(testUnit.next()).is(element2);
    expect(testUnit.next()).is(element3);
    expect(testUnit.next()).is(element4);
    expect(testUnit.next()).is(element5);
    expect(testUnit.next()).is(element6);
    expectNot(testUnit.hasNext());

    //verification part 2: Verifies result.
    expect(result.next()).is(element1);
    expect(result.next()).is(element2);
    expect(result.next()).is(element3);
    expect(result.next()).is(element4);
    expect(result.next()).is(element5);
    expect(result.next()).is(element6);
    expectNot(result.hasNext());
  }

  @Test
  void testCase_getCopy_whenIsAtIndexBetweenStartAndEnd() {

    //setup part 1: Creates array.
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var element6 = new VoidObject();
    final var array = new Object[] { element1, element2, element3, element4, element5, element6 };

    //setup part 2: Creates testUnit.
    final var testUnit = ArrayIterator.forArray(array);
    GlobalSequencer.forCount(3).run(testUnit::next);

    //execution
    final var result = testUnit.createCopy();

    //verification part 1: Verifies testUnit.
    expect(testUnit.next()).is(element4);
    expect(testUnit.next()).is(element5);
    expect(testUnit.next()).is(element6);
    expectNot(testUnit.hasNext());

    //verification part 2: Verifies result.
    expect(result.next()).is(element4);
    expect(result.next()).is(element5);
    expect(result.next()).is(element6);
    expectNot(result.hasNext());
  }

  @Test
  void testCase_getCopy_whenIsAtEndIndex() {

    //setup part 1: Creates array.
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var element6 = new VoidObject();
    final var array = new Object[] { element1, element2, element3, element4, element5, element6 };

    //setup part 2: Creates testUnit.
    final var testUnit = ArrayIterator.forArray(array);
    GlobalSequencer.forCount(6).run(testUnit::next);

    //execution
    final var result = testUnit.createCopy();

    //verification part 1: Verifies testUnit.
    expectNot(testUnit.hasNext());

    //verification part 2: Verifies result.
    expectNot(result.hasNext());
  }
}
