//package declaration
package ch.nolix.coretest.commontypetooltest.commontypehelpertest;

import ch.nolix.core.commontypetool.commontypehelper.GlobalIterableHelper;
import ch.nolix.core.independent.container.List;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalIterableHelperTest extends Test {

  //method
  @TestCase
  public void testCase_containsAny_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;

    //execution
    final var result = GlobalIterableHelper.containsAny(iterable);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_containsAny_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();

    //execution
    final var result = GlobalIterableHelper.containsAny(iterable);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_containsAny_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x" });

    //execution
    final var result = GlobalIterableHelper.containsAny(iterable);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_containsAny_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx" });

    //execution
    final var result = GlobalIterableHelper.containsAny(iterable);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_containsEqualing_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;

    //execution
    final var result = GlobalIterableHelper.containsEqualing(iterable, "x");

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_containsEqualing_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();

    //execution
    final var result = GlobalIterableHelper.containsEqualing(iterable, "x");

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_containsEqualing_whenGivenIterableContainsOnlyUnequalElements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx", "xxx" });

    //execution
    final var result = GlobalIterableHelper.containsEqualing(iterable, "xxxx");

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_containsEqualing_whenGivenIterableContainsEqualElementAndUnequalElements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx", "xxx" });

    //execution
    final var result = GlobalIterableHelper.containsEqualing(iterable, "xx");

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_containsEqualing_whenGivenIterableContainsOnlyEqualElements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "x", "x" });

    //execution
    final var result = GlobalIterableHelper.containsEqualing(iterable, "x");

    //verification
    expect(result);
  }
}
