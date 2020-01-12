::c:\Java\jdk13\bin\javac main.java
::c:\Java\jdk13\bin\java main

javac -cp libtensorflow-1.14.0.jar HelloTensorFlow.java
java -cp libtensorflow-1.14.0.jar;. -Djava.library.path=jni HelloTensorFlow

