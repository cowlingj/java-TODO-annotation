package utils.todo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/** TODO.java
  * This annotation is a handy utility for writing todo's in java source files.
  * @param priority an enum wich represents the importance of the todo.
  * @param description the description is the main content of the todo,
  * the actual thing to do.
  * @param tags a string array of tags for the todo.
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface TODO {

  public priority priority() default priority.UNDEFINED;
  public String description();
  public String[] tags() default {};

  /** priority
    * This enum represents the priority of a todo.
   **/
  public enum priority {
    HIGH,
    MEDIUM,
    LOW,
    UNDEFINED
  }
}
