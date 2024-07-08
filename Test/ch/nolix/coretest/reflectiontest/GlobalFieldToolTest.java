//package declaration
package ch.nolix.coretest.reflectiontest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.reflection.GlobalFieldTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.matrixapi.IMatrix;

//class
final class GlobalFieldToolTest extends StandardTest {

  //constant
  private static final class Lecture {

    //attribute
    @SuppressWarnings("unused")
    public LinkedList<String> students;
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_1A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, LinkedList.class);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_1B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, Container.class);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_2A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, ILinkedList.class);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_2B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, IContainer.class);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_3A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, Matrix.class);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_3B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, ArrayList.class);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_4A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, IMatrix.class);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_hasGivenTypeOrSuperType_4B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");

    //execution
    final var result = GlobalFieldTool.hasGivenTypeOrSuperType(studentsField, IArrayList.class);

    //verification
    expectNot(result);
  }
}
