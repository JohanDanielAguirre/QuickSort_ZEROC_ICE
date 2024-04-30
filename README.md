# QuickSort ICE

## About

This project consists in implementing a distributed sorting algorithm using asynchronous calls in the context of ICE (Internet Communications Engine). The main features include:

- Organize a randomly generated list of size **n**.
- Organize a list Given a .txt file

## Getting Started

To run the project you need to follow the next steps:

1. Clone the repository.
2. Open the terminal in the project folder.
3. Start the main server running the following command:



   ```bash
   $ slice2java QuickSort.ice
   ```
   ```bash
   $ /.gradlew build
   ```

   ```bash
   $ java -jar .\MotherServer\build\libs\MotherServer.jar
   ```
4. In a new terminal start the worker server running the following command

   ```bash
   $ java -jar .\SubServer\build\libs\SubServer.jar
   // you can execute many workers as you want
   ```
5. In a new terminal start the client running the following command:

   ```bash
   $ java -jar .\client\build\libs\client.jar 
   ```
6. Follow the instructions on the client console.

## Contributors

- Johan Daniel Aguirre Arias
- Alejandro Amu Garcia
- Miguel Angel Gonzalez Arango
-  Rafaela Sofia Ruiz Pizarro
