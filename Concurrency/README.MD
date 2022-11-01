
# Capítulo 13 Concurrencia
# OBJETIVOS DEL EXAMEN OCP CUBIERTOS EN ESTE CAPÍTULO:

-   Administrar la ejecución de código concurrente
    - Cree worker threads utilizando Runnable y Callable, administre el ciclo de vida del threads, incluidas las automatizaciones proporcionadas por diferentes servicios Executor y API concurrency.
    - Desarrolle código  thread-safe, usando diferentes mecanismos de locking  y API Concurrent
    - Procese las colecciones de Java de forma concurrente, incluido el uso de  parallel streams
    - Trabajar con expresiones Streams y Lambda
    - Realizar decomposition, concatenation ayd reduction, y grouping y partitioning de forma secuencial y en parallel streams
  
Como aprenderá en el Capítulo 14, “E/S”, y el Capítulo 15, “JDBC”, las computadoras son capaces de leer y escribir datos en recursos externos. Desafortunadamente, en comparación con las operaciones de la CPU, estas operaciones de disco/red tienden a ser extremadamente lentas, tan lentas, de hecho, que si el sistema operativo de su computadora se detuviera y esperara a que finalice cada operación de disco o red, su computadora parecería congelar constantemente.

Afortunadamente, todos los sistemas operativos admiten lo que se conoce como procesamiento multiproceso. La idea detrás del procesamiento de subprocesos múltiples es permitir que una aplicación o grupo de aplicaciones ejecute varias tareas al mismo tiempo. Esto permite que las tareas que esperan otros recursos den paso a otras solicitudes de procesamiento.

En este capítulo, le presentamos el concepto de subprocesos y brindamos numerosas formas de administrar los subprocesos mediante la API de concurrencia. Los subprocesos y la concurrencia son temas difíciles de comprender para muchos programadores, ya que los problemas con los subprocesos pueden ser frustrantes incluso para los desarrolladores veteranos. En la práctica, los problemas de concurrencia se encuentran entre los problemas más difíciles de diagnosticar y resolver.

Introducción de hilos
Comenzamos este capítulo revisando la terminología común asociada con los subprocesos. Un subproceso es la unidad de ejecución más pequeña que puede programar el sistema operativo. Un proceso es un grupo de subprocesos asociados que se ejecutan en el mismo entorno compartido. De ello se deduce, entonces, que un proceso de subproceso único es aquel que contiene exactamente un subproceso, mientras que un proceso de subprocesos múltiples admite más de un subproceso.

Por entorno compartido, queremos decir que los subprocesos en el mismo proceso comparten el mismo espacio de memoria y pueden comunicarse directamente entre sí. Consulte la Figura 13.1 para obtener una descripción general de los subprocesos y su entorno compartido dentro de un proceso.

Esta figura muestra un solo proceso con tres subprocesos. También muestra cómo se asignan a un número arbitrario de n CPU disponibles en el sistema. Tenga en cuenta este diagrama cuando analicemos los programadores de tareas más adelante en esta sección.

En este capítulo, hablamos mucho sobre las tareas y sus relaciones con los hilos. Una tarea es una sola unidad de trabajo realizada por un subproceso. A lo largo de este capítulo, una tarea se implementará comúnmente como una expresión lambda. Un subproceso puede completar varias tareas independientes, pero solo una tarea a la vez.

# Callable

Desde los primeros días de Java, los subprocesos múltiples han sido un aspecto importante del lenguaje. Runnable es la interfaz principal provista para representar tareas multiproceso, y Java 1.5 proporcionó Callable como una versión mejorada de Runnable .

En este tutorial, exploraremos las diferencias y las aplicaciones de ambas interfaces.

###  2. Mecanismo de ejecución

Ambas interfaces están diseñadas para representar una tarea que pueden ejecutar varios subprocesos. Podemos ejecutar tareas Runnable usando la clase Thread o ExecutorService , mientras que solo podemos ejecutar Callable s usando este último.

# Working with Parallel Streams


Hasta ahora, todas las streams con las que ha trabajado han sido streams en serie. Un streams en serie es un stream en el que se ordenan los resultados y solo se procesa una entrada a la vez.

Una streams parallel es capaz de procesar los resultados simultáneamente, utilizando varios subprocesos. Por ejemplo, puede usar una streams parallel y la operación `map()` para operar simultáneamente en los elementos de la streams, lo que mejora enormemente el rendimiento con respecto al procesamiento de un solo elemento a la vez.

El uso de una secuencia parallel puede cambiar no solo el rendimiento de su aplicación, sino también los resultados esperados. Como verás, algunas operaciones también requieren un manejo especial para poder ser procesadas de manera parallel.

## Creating Parallel Streams

La API Stream fue diseñada para facilitar la creación de transmisiones paralelas. Para el examen, debe estar familiarizado con dos formas de crear una secuencia paralela.

```java
    Collection<Integer> collection = List.of(1,2);
    Stream<Integer> p1 = collection.stream().parallel();
    Stream<Integer> p2 = collection.parallelStream();
```

La interfaz `Stream` incluye un método `isParallel()` que se puede usar para probar si la instancia de una stream admite el procesamiento en paralelo. Algunas operaciones en stream conservan el atributo parallel, mientras que otras no.

## Realizando Parallel Decomposition

Una descomposición paralela es el proceso de tomar una tarea, dividirla en partes más pequeñas que se pueden realizar simultáneamente y luego volver a ensamblar los resultados. Cuanto más concurrente sea una descomposición, mayor será la mejora del rendimiento del uso de sterams paralelos.

Probémoslo. Primero, definamos una función reutilizable que "sí funciona" con solo esperar cinco segundos..

```java
    private static int doWork(int input) { 
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        return input; 
    }
```

Podemos simular que estamos  en una aplicación real, este trabajo puede implicar llamar a una BBDD o leer un archivo. Ahora usemos este método con un serial Stream.

```java
    long start = System.currentTimeMillis(); 
    List.of(1,2,3,4,5)
        .stream()
        .map(w -> doWork(w))
        .forEach(s -> System.out.print(s + " "));

    System.out.println();
    var timeTaken = (System.currentTimeMillis()-start)/1000; 
    System.out.println("Time: "+timeTaken+" seconds");
```

¿Qué crees que generará este código cuando se ejecute como parte de un método main()? Veamos:

```c++
    1 2 3 4 5
    Time: 25 seconds
```

Como era de esperar, los resultados están ordenados y son predecibles porque estamos usando una stream en serie. Por lo que tarda alrededor de 25 segundos procesar los cinco resultados, uno a la vez. ¿Qué sucede si reemplazamos la línea 12 con una que usa un `parallelStream()`? 

La siguiente es una salida de muestra:

```json
    3 2 1 5 4
    Time: 5 seconds
```

Como puede ver, los resultados ya no estan ordenados, ni son predecibles. Las operaciones `map()` y `forEach()` en `stream parallel` son equivalentes a enviar varias expresiones lambda Runnable a un Thread Pool Executor y luego esperar los resultados.

**Ordering Results**

If your stream operation needs to guarantee ordering and you’re not sure if it is serial or parallel, you can replace
line with one that uses forEachOrdered():

Si su operación `Stream` necesita garantizar el orden y no estás seguro de si es en serie o en paralelo, puedes reemplazar la línea con una que use `forEachOrdered()`:

```java
    .forEachOrdered(s -> System.out.print(s + " "));
```

Esto genera los resultados en el orden en que se definen en el `Stream`:

```go

    1 2 3 4 5
    Time: 5 seconds
```


## Processing Parallel Reductions

Un `parallel reduction` es una operación de reducción aplicada a una `parallel stream`. Los resultados de las `parallel reduction` pueden diferir de lo que espera cuando trabaja con `serial streams`.

### Performing Order-Based Tasks

Dado que el orden no está garantizado con`parallel stream`, los métodos como findAny() en `parallel stream` pueden generar un comportamiento inesperado. Mira siguiente ejemplo:

```java
    System.out.print(List.of(1,2,3,4,5,6)
    .parallelStream() 
    .findAny() 
    .get());
```

La JVM asigna una cantidad de threads y devuelve el valor del primero para devolver un resultado, que podría ser 4, 2, etc. Si bien no se garantiza que la `serial` o `parallel stream `devuelva el primer valor, la `stream serial` a menudo lo hace. Con `parallel stream`, es probable que los resultados sean más aleatorios.



¿Qué sucede con las operaciones que consideran el orden, como `findFirst()`, `limit()` y `skip()`? El orden aún se conserva, pero el rendimiento puede verse afectado en `parallel stream` como resultado de que una tarea de procesamiento paralelo se ve obligada a coordinar todos sus `threads` de forma sincronizada.

En el lado positivo, los resultados de las operaciones ordenadas en un `parallel stream ` serán consistentes con `stream serial`. Por ejemplo, llamar a `skip(5).limit(2).findFirst()` devolverá el mismo resultado en streams ordenados en serie y en paralelo.

**Creating Unordered Streams**

Todas las streams con las que ha estado trabajando se consideran ordenadas de forma predeterminada. Es posible crear una streams desordenados a partir de una streams ordenados, de forma similar a como se crea una `parallel stream ` a partir de `serial steram`.

```java
    List.of(1,2,3,4,5,6).stream().unordered();
```

Este método no reordena los elementos; simplemente le dice a la JVM que si se aplica unaorder-based stream operation, el orden puede ignorarse. Por ejemplo, llamar a skip(5) en un steram desordenado omitirá 5 elementos, no necesariamente los primeros 5 requeridos en un steram ordenado.

Para `serial streams`, usar la versión desordenada no tiene ningún efecto. Pero en `parallel streams`, los resultados pueden mejorar mucho el rendimiento.

```java
    List.of(1,2,3,4,5,6).stream().unordered().parallel();
```

Aunque las streams desordenados no están en el examen, si estás desarrollando aplicaciones con streams paralelos, debes saber cuándo aplicar una secuencia desordenada para mejorar el rendimiento.

## Combining Results with reduce()

Recuerde que el primer parámetro del método reduce() se llama identity, el segundo parámetro se llama accumulator y el tercer parámetro se llama combiner. 
La siguiente es la firma del método:

    <U> U reduce(U identity, 
        BiFunction<U,? super T,U> accumulator, 
        BinaryOperator<U> combiner)
        
Podemos concatenar una lista de valores char usando el método reduce(), como se muestra en el siguiente ejemplo:

    System.out.println(List.of('w', 'o', 'l', 'f') 
    .parallelStream()
    .reduce("",
        (s1,c) -> s1 + c,
        (s2,s3) -> s2 + s3)); // wolf


Con ´parallel streams´, ahora tenemos que preocuparnos por el orden. ¿Qué pasa si los elementos de una cadena se combinan en el 
orden incorrecto para producir wlfo o flowo? La API Stream evita este problema al mismo tiempo que permite que las transmisiones
se procesen en paralelo, siempre que siga una regla simple: asegúrese de que el acumulador y el combinador 
produzcan el mismo resultado, independientemente del orden en que se llamen.

Si bien esto no está dentro del alcance del examen, el acumulador y el combinador deben ser asociativos, sin interferencias y sin estado.

Echemos un vistazo a un ejemplo usando un acumulador problemático. En particular, el orden importa cuando se restan números; por lo tanto, el siguiente código puede generar diferentes valores dependiendo de si use streams en serie o en paralelo. Podemos omitir un parámetro combinador en estos ejemplos, ya que el acumulador se puede usar cuando los tipos de datos intermedios son los mismos.

    System.out.println(List.of(1,2,3,4,5,6)
    .parallelStream()
    .reduce(0, (a, b) -> (a - b))); // PROBLEMATIC ACCUMULATOR

Puede generar -21, 3 o algún otro valor.

Puedes ver otros problemas si usamos un parámetro de identidad que no es realmente un valor de identidad. Por ejemplo, ¿qué esperas que genere el siguiente código?

    System.out.println(List.of("w","o","l","f") 
    .parallelStream()
    .reduce("X", String::concat)); // XwXoXlXf

En una transmisión en serie, imprime Xwolf, pero en una transmisión en paralelo, el resultado es XwXoXlXf. Como parte del proceso paralelo, la identidad se aplica a varios elementos de la secuencia, lo que da como resultado datos muy inesperados.


### Combining Results with collect()

Al igual que `reduce()`, Stream API incluye una versión de tres argumentos de `collect()` que toma operadores de acumulador y combinador junto con un operador de proveedor en lugar de una identidad.

    <R> R collect(Supplier<R> supplier, 
    BiConsumer<R, ? super T> accumulator, 
    BiConsumer<R, R> combiner)

Además, como reduce(), las operaciones de acumulador y combinador deben poder procesar los resultados en cualquier orden. 
De esta manera, la versión de tres argumentos de collect() se puede realizar como una reducción paralela, como se muestra en el siguiente ejemplo:

    Stream<String> stream = Stream.of("w", "o", "l", "f").parallel(); 
    SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,
        Set::add,
        Set::addAll);
    System.out.println(set); // [f, l, o, w]

Recuerde que los elementos de un ConcurrentSkipListSet se ordenan según su orden natural. Debe usar una colección concurrente para combinar los resultados, asegurándose de que los resultados de subprocesos concurrentes no provoquen una ConcurrentModificationException.

Realizar reducciones paralelas con un colector requiere consideraciones adicionales. Por ejemplo, si la colección en la que está insertando es un conjunto de datos ordenados, como una Lista, los elementos de la colección resultante deben estar en el mismo orden, independientemente de si usa una transmisión en serie o paralela. Sin embargo, esto puede reducir el rendimiento, ya que algunas operaciones no se pueden completar en paralelo.

## Performing a Parallel Reduction on a Collector

Every Collector instance defines a characteristics() method that returns a set of Collector.Characteristics attributes.
When using a Collector to perform a parallel reduction, a number of properties must hold true. Otherwise, the collect()
operation will execute in a single-threaded fashion.

**Requirements for Parallel Reduction with collect()**

- The stream is parallel.
- The parameter of the collect() operation has the Characteristics.CONCURRENT characteristic.
- Either the stream is unordered or the collector has the characteristic Characteristics.UNORDERED.

For example, while Collectors.toSet() does have the UNORDERED characteristic, it does not have the CONCURRENT
characteristic. Therefore, the following is not a parallel reduction even with a parallel stream:

    parallelStream.collect(Collectors.toSet()); // Not a parallel reduction

The Collectors class includes two sets of static methods for retrieving collectors, toConcurrentMap() and
groupingByConcurrent(), both of which are UNORDERED and CONCURRENT. These methods produce Collector instances capable of
performing parallel reductions efficiently. Like their nonconcurrent counterparts, there are overloaded versions that
take additional arguments.

    Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
    ConcurrentMap<Integer, String> map = ohMy
            .collect(Collectors.toConcurrentMap(String::length, k -> k,
                    (s1, s2) -> s1 + "," + s2));
    System.out.println(map); // {5=lions,bears, 6=tigers}
    System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap

We use a ConcurrentMap reference, although the actual class returned is likely ConcurrentHashMap. The particular class
is not guaranteed; it will just be a class that implements the interface ConcurrentMap.

Finally, we can rewrite our groupingBy() example from Chapter 10 to use a parallel stream and parallel reduction.

    var ohMy = Stream.of("lions", "tigers", "bears").parallel();
    ConcurrentMap<Integer, List<String>> map = ohMy.collect(
            Collectors.groupingByConcurrent(String::length));
    System.out.println(map); // {5=[lions, bears], 6=[tigers]}

**Avoiding Stateful Streams**

Side effects can appear in parallel streams if your lambda expressions are stateful. A stateful lambda expression is
one whose result depends on any state that might change during the execution of a pipeline. For example, the following
method that filters out even numbers is stateful:
```java

    public List<Integer> addValues(IntStream source) {
        var data = Collections.synchronizedList(new ArrayList<Integer>());
        source.filter(s -> s % 2 == 0)
                .forEach(i -> {
                    data.add(i);
                }); // STATEFUL: DON'T DO THIS!
        return data;
    }
```

Let’s say this method is executed with a serial stream:

```java
    var list = addValues(IntStream.range(1, 11)); 
    System.out.print(list); // [2, 4, 6, 8, 10]
```

Great, the results are in the same order that they were entered. But what if someone else passes in a parallel stream?

```java
    var list = addValues(IntStream.range(1, 11).parallel()); 
    System.out.print(list); // [6, 8, 10, 2, 4]
```

Oh, no: our results no longer match our input order!The problem is that our lambda expression is stateful and modifies a
list that is outside our stream. We can fix this solution by rewriting our stream operation to be stateless:

```java
    public List<Integer> addValuesBetter(IntStream source) { 
        return source.filter(s -> s % 2 == 0)
        .boxed()
        .collect(Collectors.toList()); 
    }
```

This method processes the stream and then collects all the results into a new list. It produces the same ordered
result on both serial and parallel streams. It is strongly recommended that you avoid stateful operations when using
parallel streams, to remove any potential data side effects. In fact, they should be avoided in serial streams since
doing so limits the code’s ability to someday take advantage of parallelization.
