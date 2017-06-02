import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Todo {

  public priority priority() default priority.MEDIUM;
  public String desc() default "none";

  public enum priority {
    HIGH,
    MEDIUM,
    LOW
  }
}