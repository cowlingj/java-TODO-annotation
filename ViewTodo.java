import java.lang.annotation.Annotation;

public class ViewTodo {
  public static void main(String[] args) throws ClassNotFoundException {
    Class<?> givenClass = Class.forName(args[0]);
    
    public enum Order {
      ASEENDING,
      DESCENDING
    }

    public enum SortBy {
      PRIORITY,
      DESCRIPTION
    }

    Order givenOrder = null;
    switch (args[0].toUpperCase()) {
      case "A": case "ASCENDING":
        givenOrder = Order.ASCENDING;
        break;
      case "D": case "DESCENDING":
        givenOrder = Order.DESCENDING;
        break;
      case "H": case "HELP":
        showhelpDialog();
        System.exit(0);
      default throw(IllegalArgumentException);
        break;
    }

    SortBy givenSortBy = null;
    switch (args[1].toUpperCase()) {
      case "P": case "PRIORITY":
        givenSortBy = SortBy.PRIORITY;
        break;
      case "D": case "DESCRIPTION":
        givenSortBy = SortBy.DESCRIPTION;
        break;
      default throw(IllegalArgumentException);
        break;
    }

    System.out.println("Name: " + givenClass.getName());
    Annotation[] annotationArray = givenClass.getAnnotations();  
    
    if(givenClass.isAnnotationPresent(Todo.class)) {
      Annotation classTodo = givenClass.getAnnotation(Todo.class);
      Todo todo = (Todo) classTodo;
      System.out.println("Class Todo:\n  Priority: " + todo.priority()
                         + "\n  Description: " + todo.desc());
    }
    else {
      System.out.println("No Class Todo Present");
    }
    
  }
}

// make classes the last arguments, so there can be many
// implement sorting
// priority can be sorted quickly since there is a very finite set
// sord description out by mapping the annotation to the description strings
// sort into desending by  default then use collections reverse array order to flip if ascending is chosen
// complete help dialogue