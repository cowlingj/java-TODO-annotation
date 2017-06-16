package utils.todo;

@TODO(priority = TODO.priority.LOW, description = "this is a description")
public class HelloTodo {

  @TODO(description="field desc")
  String myField;

  @TODO(description="methiod todo desc")
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  @TODO(priority=TODO.priority.LOW, tags={"tag1", "tag2"}, description="cons")
  HelloTodo(){}
}