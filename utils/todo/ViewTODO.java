package utils.todo;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/** ViewTODO
  * This class analyses a given list of classes from fully qualified class
  * names, reporting the details of @TODO annotations on classes, methods,
  * fields, constructors, and packages.
  * @aurthor Jonathan Cowling
  * @version 1.0
  * @since June 2017
 **/
public class ViewTODO {

  // the style of indentation (i.e. tab, 4 spaces, 2 spaces, etc.)
  private static final String INDENT_TYPE = "  ";

  /** main
    * First creates an array of classes then reports on the @TODO annotations
    * the order of reporting is 
    * @params the fully qualified names of the classes to analyse
    * @throws ClassNotFoundException
   **/
  public static void main(String[] args) throws ClassNotFoundException {

    // get each class by name one at a time
    for (String arg : args) {
      Class<?> classToView = Class.forName(arg);

      // the innitial indent level for the output
      int indentLevel = 0;

      // if there is a package first get the package TODOs
      Package packageToView = classToView.getPackage();
      if (packageToView != null) {
        if (printAccessibleObjectTODOs(classToView, indentLevel, Package.class)) {
          indentLevel++;
        }
      }

      // getting all class TODOs
      if (classToView.isAnnotationPresent(TODO.class)) {
        TODO todo = classToView.getAnnotation(TODO.class);

        // get class name, TODO description, TODO priority, and TODO tags
        // these can be found from reflections and from the TODO annotation 
        String name, description, priority;
        String[] tags;
        name = classToView.getName();
        description = todo.description();
        tags = todo.tags();

        // create a string representation for the priority
        switch (todo.priority()) {
          case HIGH:
            priority = "HIGH";
            break;
          case MEDIUM:
            priority = "MEDIUM";
            break;
          case LOW:
            priority = "LOW";
            break;
          case UNDEFINED: default:
            priority = "UNDEFINED";
            break;
        }


        // output dependent on indentLevel of class TODOs
        if (indentLevel == 0) {

          String indent = INDENT_TYPE;

          // output for class TODOs
          System.out.printf("Class TODO\n%sName: %s\n%sPriority: %s\n"
                            + "%sDescription: %s\n%sTags: %s\n\n",
                            indent, name,
                            indent, priority,
                            indent, description,
                            indent, Arrays.toString(tags));
        } else {

        // build indent based on INDENT_TYPE and indentLevel
        StringBuilder indentBuilder = new StringBuilder("");
        for (int index = 0; index < indentLevel; index++) {
          indentBuilder.append(INDENT_TYPE);
        }
        String indent = indentBuilder.toString();

        // output for class TODOs
        System.out.printf("%sClass TODO\n%s%sName: %s\n%s%sPriority: %s\n"
                          + "%s%sDescription: %s\n%s%sTags: %s\n\n",
                          indent, indent, indent, name,
                          indent, indent, priority,
                          indent, indent, description,
                          indent, indent, Arrays.toString(tags));
        }

      }

      indentLevel++;

      // print TODOs for AcessibleObjects in class
      printAccessibleObjectTODOs(classToView, indentLevel, Field.class);
      printAccessibleObjectTODOs(classToView, indentLevel, Constructor.class);
      printAccessibleObjectTODOs(classToView, indentLevel, Method.class);
    }
  }

  /** printAccessibleObjectTODOs
    * A helper method that prints the TODO report of AccessibleObjects within
    * the class, such as methods and fields.
    * @param classToView The class that is being analysed for TODOs
    * @param indentLevel The indentation level for the beginning of the output 
    * @param type  AccessibleObject (Field, Constructor, Method)/ package class,
    * @return true if there has been output, false otherwise.
   **/
  private static boolean printAccessibleObjectTODOs(Class<?> classToView,
                                                 int indentLevel,
                                                 Class<?> type) {

    boolean todoPresent = false;
  
    // print package TODOs
    if (type.equals(Package.class)) {
      Package toView = classToView.getPackage();

      // check for @TODO
      if (toView.isAnnotationPresent(TODO.class)) {

        todoPresent = true;

        // the TODO of the package
        TODO todo = toView.getAnnotation(TODO.class);

        // get package name, TODO description, TODO priority, and TODO tags
        // these can be found from reflections and from the TODO annotation 
        String name, description, priority;
        String[] tags;
        name = toView.getName();
        description = todo.description();
        tags = todo.tags();

        // create a string representation for the priority
        switch (todo.priority()) {
          case HIGH:
            priority = "HIGH";
            break;
          case MEDIUM:
            priority = "MEDIUM";
            break;
          case LOW:
            priority = "LOW";
            break;
          case UNDEFINED: default:
            priority = "UNDEFINED";
            break;
        }

        // output for package TODOs
        String indent = INDENT_TYPE;
        System.out.printf("Package TODO\n%sName: %s\n%sPriority: %s\n"
                          + "%sDescription: %s\n%sTags: %s\n\n",
                          indent, name,
                          indent, priority,
                          indent, description,
                          indent, Arrays.toString(tags));
      }
    }
    // print field TODOs
    else if (type.equals(Field.class)) {
      Field[] thingsToView = classToView.getDeclaredFields();
      for (Field toView : thingsToView) {

        // check for @TODO
        if (toView.isAnnotationPresent(TODO.class)) {

          todoPresent = true;

          // the TODO of the current field
          TODO todo = toView.getAnnotation(TODO.class);

          // get field name, TODO description, TODO priority, and TODO tags
          // these can be found from reflections and from the TODO annotation 
          String name, description, priority;
          String[] tags;
          name = toView.getName();
          description = todo.description();
          tags = todo.tags();

          // create a string representation for the priority
          switch (todo.priority()) {
            case HIGH:
              priority = "HIGH";
              break;
            case MEDIUM:
              priority = "MEDIUM";
              break;
            case LOW:
              priority = "LOW";
              break;
            case UNDEFINED: default:
              priority = "UNDEFINED";
              break;
          }

          // build indent based on INDENT_TYPE and indentLevel
          StringBuilder indentBuilder = new StringBuilder("");
          for (int index = 0; index < indentLevel; index++) {
            indentBuilder.append(INDENT_TYPE);
          }
          String indent = indentBuilder.toString();

          // output for field TODOs
          System.out.printf("%sField TODO\n%s%sName: %s\n%s%sPriority: %s\n"
                          + "%s%sDescription: %s\n%s%sTags: %s\n\n",
                          indent, indent, indent, name,
                          indent, indent, priority,
                          indent, indent, description,
                          indent, indent, Arrays.toString(tags));
        }
      }
    }
    // print constructor TODOs
    else if (type.equals(Constructor.class)) {
      Constructor<?>[] thingsToView = classToView.getDeclaredConstructors();
      for (Constructor<?> toView : thingsToView) {

        // check for @TODO
        if (toView.isAnnotationPresent(TODO.class)) {

          todoPresent = true;

          // the TODO of the current constructor
          TODO todo = toView.getAnnotation(TODO.class);

          // get constructor name, TODO description, TODO priority, and TODO tags
          // these can be found from reflections and from the TODO annotation 
          String name, description, priority;
          String[] tags;
          name = toView.getName();
          description = todo.description();
          tags = todo.tags();

          // create a string representation for the priority
          switch (todo.priority()) {
            case HIGH:
              priority = "HIGH";
              break;
            case MEDIUM:
              priority = "MEDIUM";
              break;
            case LOW:
              priority = "LOW";
              break;
            case UNDEFINED: default:
              priority = "UNDEFINED";
              break;
          }

          // build indent based on INDENT_TYPE and indentLevel
          StringBuilder indentBuilder = new StringBuilder("");
          for (int index = 0; index < indentLevel; index++) {
            indentBuilder.append(INDENT_TYPE);
          }
          String indent = indentBuilder.toString();

          // output for constructor TODOs
          System.out.printf("%sConstructor TODO\n%s%sName: %s\n%s%sPriority: %s\n"
                          + "%s%sDescription: %s\n%s%sTags: %s\n\n",
                          indent, indent, indent, name,
                          indent, indent, priority,
                          indent, indent, description,
                          indent, indent, Arrays.toString(tags));
        }
      }
    }
    // print method TODOs
    else /*(type.equals(Method.class))*/ {
      Method[] thingsToView = classToView.getDeclaredMethods();
      for (Method toView : thingsToView) {

      todoPresent = false;

        // check for @TODO
        if (toView.isAnnotationPresent(TODO.class)) {

          todoPresent = true;

          // the TODO of the current method
          TODO todo = toView.getAnnotation(TODO.class);

          // get method name, TODO description, TODO priority, and TODO tags
          // these can be found from reflections and from the TODO annotation 
          String name, description, priority;
          String[] tags;
          name = toView.getName();
          description = todo.description();
          tags = todo.tags();

          // create a string representation for the priority
          switch (todo.priority()) {
            case HIGH:
              priority = "HIGH";
              break;
            case MEDIUM:
              priority = "MEDIUM";
              break;
            case LOW:
              priority = "LOW";
              break;
            case UNDEFINED: default:
              priority = "UNDEFINED";
              break;
          }

          // build indent based on INDENT_TYPE and indentLevel
          StringBuilder indentBuilder = new StringBuilder("");
          for (int index = 0; index < indentLevel; index++) {
            indentBuilder.append(INDENT_TYPE);
          }
          String indent = indentBuilder.toString();

          // output for method TODOs
          System.out.printf("%sMethod TODO\n%s%sName: %s\n%s%sPriority: %s\n"
                          + "%s%sDescription: %s\n%s%sTags: %s\n\n",
                          indent, indent, indent, name,
                          indent, indent, priority,
                          indent, indent, description,
                          indent, indent, Arrays.toString(tags));
        }
      }
    }
  // if there has been some output then TODOs are present so return true
  return todoPresent;
  }
  

  /** showHelpDialog
    * Prints help dialog to the stdOut 
   **/
  private static void showHelpDialog() {
    String HELP_DIALOG = "Usage: java ViewTODO class_names";
    System.out.println(HELP_DIALOG);
  }
}