# QuickSort ICE

## Acerca de
Este proyecto consiste en implementar un algoritmo de ordenamiento distribuido utilizando llamadas asíncronas en el contexto de ICE (Internet Communications Engine). Las principales características incluyen:

- Organizar una lista generada aleatoriamente de tamaño *n*.
- Organizar una lista dada a partir de un archivo .txt

## Introducción
Para iniciar, se abordará el concepto general del algoritmo. QuickSort, se basa en la estrategia divide y vencerás, el cual consiste en descomponer un problema en serie de subproblemas de menor tamaño, resolverlos recursivamente y combinar las soluciones para obtener una general (que es la solución del problema original). Así, el algoritmo, al dividir, realiza comparaciones para hacer correcta la división de subproblemas: elementos menores o iguales al objeto a comparar (pivote), a la derecha, y elementos mayores al pivote a la izquierda.

## Descripción
Este proceso estará guiado entre un Servidor y un Cliente. Así, el cliente inicialmente le otorgará la lista de elementos a ordenar al Servidor. Y éste último se encargará de realizar los procesos correspondientes de QuickSort sobre la lista con ayuda de sub-servidores; cada sub-server tendrá una parte de la lista otorgada por el Server, así cada uno se comprometerá en resolver/ordenar la sub-lista otorgada y entregársela correctamente a su Servidor. Finalmente, el Servidor tendrá la lista completa nuevamente, gracias a sus servidores ayudantes, y podrá darle entrega de la solución ordenada al Cliente.

## Análisis algorítmico
El algoritmo QuickSort tiene una complejidad algorítmica promedio de O(nlogn), donde n es el número de elementos en la lista. Esto se debe a que divide la lista en dos partes y realiza la operación de ordenamiento en cada una de ellas de manera recursiva. Sin embargo, se vuelve de menor complejidad al ser distribuido al ser afectado por una cantidad k de servidores, la complejidad quedaría determinada como: O(n/k  log⁡〖n/k〗).

## Pruebas
Para probarlo, se utilizó listas de diferentes tamaños de datos (100, 1000, 10000, 100000, 1000000). En los que se evidenció, en general, que para datos más grandes, el tiempo de ejecución mejora con el aumento de subservidores.

## Conclusión
La implementación del algoritmo QuickSort en un entorno distribuido con llamadas asíncronas ha demostrado ser eficiente y efectiva. La estrategia de dividir y vencer, junto con la distribución de tareas entre varios servidores, permite un procesamiento más rápido y eficiente de los datos. Donde su complejidad algorítmica promedio se mantiene, la distribución de la carga de trabajo entre varios servidores puede reducir efectivamente el tiempo de ejecución. Sin embargo, es importante tener en cuenta que la sobrecarga de comunicación entre el servidor y los sub-servidores puede afectar el rendimiento general.

A continuación el informe. [Informe - Sort Distribuido.pdf]

En donde se hablará todo esto esto de manera más detallada.
## Cómo empezar

Para ejecutar el proyecto, debes seguir los siguientes pasos:

1. Clona el repositorio.
2. Abre la terminal en la carpeta del proyecto.
3. Inicia el servidor principal ejecutando el siguiente comando:

   bash
   $ slice2java QuickSort.ice

   bash
   $ .\gradlew build


bash
$ java -jar .\MotherServer\build\libs\MotherServer.jar

4. En una nueva terminal, inicia el servidor trabajador ejecutando el siguiente comando:

   bash
   $ java -jar .\SubServer\build\libs\SubServer.jar
   // puedes ejecutar tantos trabajadores como quieras

5. En una nueva terminal, inicia el cliente ejecutando el siguiente comando:

   bash
   $ java -jar .\client\build\libs\client.jar

6. Sigue las instrucciones en la consola del cliente.

## Contribuidores

- Johan Daniel Aguirre Arias
- Alejandro Amu Garcia
- Miguel Angel Gonzalez Arango
- Rafaela Sofia Ruiz Pizarro