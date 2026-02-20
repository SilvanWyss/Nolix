/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.reflection.reflectionexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.arraylist.ArrayList;
import ch.nolix.base.container.base.AbstractContainer;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.container.matrix.Matrix;
import ch.nolix.base.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.IArrayList;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.container.matrix.IMatrix;

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
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, LinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_1B() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, AbstractContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_2A() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ILinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_2B() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_3A() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, Matrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_3B() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ArrayList.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_4A() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IMatrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_4B() throws NoSuchFieldException {
    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IArrayList.class);

    //verification
    expect(result).isFalse();
  }
}
