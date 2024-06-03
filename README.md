# Assignment for Compilers Course DIT HUA

## Authors:

- Nikos Gournakis
- Arvi Hoxha

### Building the compiler

``mvn clean package``

### Compiling and executing file

``java -jar target/compiler-0.0.2.jar file.alan``

### Building compiler and run tests

``python test_runner.py``

### Building the correct test outputs

## MAKE SURE THE OUTPUTS ARE CORRECT BEFORE RUNNING THIS COMMAND

``python test_runner.py -B``

### Running the lexer only on the file

``java -jar target/compiler-0.0.2.jar file.alan --lexer-only``

### Printing typechecking debug info

``java -jar target/compiler-0.0.2.jar file.alan --typecheck-debug``