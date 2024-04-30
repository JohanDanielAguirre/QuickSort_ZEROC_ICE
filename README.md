# QuickSort ICE

## Acerca de

Este proyecto consiste en implementar un algoritmo de ordenamiento distribuido utilizando llamadas asíncronas en el contexto de ICE (Internet Communications Engine). Las principales características incluyen:

- Organizar una lista generada aleatoriamente de tamaño **n**.
- Organizar una lista dada a partir de un archivo .txt

## Cómo empezar

Para ejecutar el proyecto, debes seguir los siguientes pasos:

1. Clona el repositorio.
2. Abre la terminal en la carpeta del proyecto.
3. Inicia el servidor principal ejecutando el siguiente comando:

   ```bash
   $ slice2java QuickSort.ice
   ```
   ```bash
   $ .\gradlew build
   ```

   ```bash
   $ java -jar .\MotherServer\build\libs\MotherServer.jar
   ```
4. En una nueva terminal, inicia el servidor trabajador ejecutando el siguiente comando:

   ```bash
   $ java -jar .\SubServer\build\libs\SubServer.jar
   // puedes ejecutar tantos trabajadores como quieras
   ```
5. En una nueva terminal, inicia el cliente ejecutando el siguiente comando:

   ```bash
   $ java -jar .\client\build\libs\client.jar 
   ```
6. Sigue las instrucciones en la consola del cliente.

## Contribuidores

- Johan Daniel Aguirre Arias
- Alejandro Amu Garcia
- Miguel Angel Gonzalez Arango
- Rafaela Sofia Ruiz Pizarro