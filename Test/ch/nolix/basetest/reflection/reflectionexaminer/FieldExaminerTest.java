/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.reflection.reflectionexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.datastructure.matrix.Matrix;
import ch.nolix.base.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.datastructure.matrix.IMatrix;

/**
 * @author Silvan Wyss
 */
final class FieldExaminerTest extends StandardTest {
  private static final class Lecture {
    @SuppressWarnings("unused")
    public LinkedList<String> students;
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_1A() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, LinkedList.class);

    // verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_1B() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, AbstractExtendedIterable.class);

    // verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_2A() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ILinkedList.class);

    // verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_2B() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ExtendedIterable.class);

    // verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_3A() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, Matrix.class);

    // verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_3B() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ArrayList.class);

    // verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_4A() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IMatrix.class);

    // verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_4B() throws NoSuchFieldException {
    // setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    // execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IArrayList.class);

    // verification
    expect(result).isFalse();
  }
}
