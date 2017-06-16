# java-TODO-annotation
Implementation of @TODO annotations, and a utility class for viewing them.

## Synopsis
```java

@TODO(description="This is a TODO")
class myClass {
...
}
```
`java -classpath TODO.java utils.todo.ViewTODO class_names...`

## Motivation
A TODO feature makes development a easier, allowing programmers to keep track of the tasks they have to complete.
Here there is an easy to use TODO annotation, and a class to view them all.

## Installation
The TODO.jar file contains all the classes neccessary for everything to work,
all that needs to be done is download the jar and add it to the classpath.
1. Download TODO.jar
2. Import TODOs in java files with `import utils.todo.TODO`
3. Write @TODO annotations
4. Include TODO.jar in classpath when compiling
5. View TODOs with `java -classpath TODO.java utils.todo.ViewTODO class_names...`

## Reference

### @TODO Annotation
TODO annotations have the following syntax:  
@TODO(key=value, key=value...)

Where key value pairs are one or more of the following:  
**description="a descriptive text"** _<- description is required._  
priority=TODO.priority.[HIGH | MEDIUM | LOW | UNDEFINED]  
tags={"tag1", "tag2"...}  

Annotations can be placed on classes, fields, methods, constructors, and packages
(since java 6 package annotations and documentation should be in package-info.java).

### ViewTODO
ViewTODO reports all TODOs to standard output (System.out).  
`java -classpath TODO.java utils.todo.ViewTODO class_names...`  
Ensure class_names are fully qualified.

## Contributors
Pull requests welcome.

## License
GNU GPLv3
