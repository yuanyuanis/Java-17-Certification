## Optional
### ¿Opcional es lo mismo que nulo?
Una alternativa a Opcional es devolver nulo. Hay algunas deficiencias con este enfoque. Una es que no hay una forma clara de expresar que nulo podría ser un valor especial. Por el contrario, devolver un Opcional es una declaración clara en la API de que es posible que no haya un valor.

Otra ventaja de Optional es que puede usar un estilo de programación funcional con ifPresent() y los otros métodos en lugar de necesitar una instrucción if. Finalmente, verá hacia el final del capítulo que puede encadenar llamadas opcionales.


```java
@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		
		System.out.println("media(50, 60, 80): "+media(50, 60, 80));
		System.out.println("media(): "+media());
		
		/***
		 * Resultado:
		 *  media(50, 60, 80): Optional[63.333333333333336]
			media(): Optional.empty
		 */
		
		// Normalmente queremos ver si hay valor para mostrarlo o no
		
		var resultado = media(92,55,44);
		if(resultado.isPresent()) 
			System.out.println(resultado.get());
		
		try {

			var resultadoVacio = media();
			resultadoVacio.get(); // NoSuchElementException
		} catch (NoSuchElementException e) {
			System.out.println("Si se extre un opcional vacio con get se obtiene excepcion:"+ e.getMessage());
		}
		
		
		// Al crear un Opcional, es común querer usar vacío() cuando el valor es nulo. Puede hacer esto con una instrucción 
		
		String value = null;
		Optional op1 = (value == null) ? Optional.empty() : Optional.of(value);
		// Si el valor es nulo, a o se le asigna el Opcional vacío. De lo contrario, envolvemos el valor. 
		// Dado que este es un patrón tan común, Java proporciona un método de fábrica para hacer lo mismo.

		Optional<String> op2 =Optional.ofNullable(value);
		
		/**
		 * Ya has visto get() e isPresent(). Los otros métodos le permiten escribir código que usa un Optional en una línea 
		 * sin tener que usar el operador ternario. Esto hace que el código sea más fácil de leer. 
		 * En lugar de usar una instrucción if, que usamos al verificar el promedio anteriormente, 
		 * podemos especificar que se ejecute un Consumer cuando haya un valor dentro de Optional. 
		 * Cuando no lo hay, el método simplemente omite la ejecución del Consumer.
		 */
		
		var resultado2 = media(2,5,45);
		resultado.ifPresent(System.out::println);
		
		// DEALING WITH EMPTY OPTIONAL
		// Si nuestro resultado es empty podemos querer hacer una acción, para eso tenemos la opcion orElse y orElseGet
		var resultadoVacio2 = media();
		System.out.println(resultadoVacio2.orElse(Double.NaN));
		System.out.println(resultadoVacio2.orElseGet(() -> Math.random()));
		
		// Imprime algo asi
		// NaN
		// 0.39556685131214986
		
		// Tambien podemos querer lanzar una excepción tenemos dos opciones
		// orElseThrow
		// orElseThrow(Supplier s)

		var resultadoVacio3 = media();
		try {
			resultadoVacio3.orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			resultadoVacio3.orElseThrow(() -> new IllegalStateException(" ¡La lista de scores no puede estar vacia tronco! "));
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
        // Los dos métodos que toman un Supplier  tienen nombres diferentes. ¿Ves por qué este código no se compila?

		System.out.println(media().orElseGet(
				   () -> new IllegalStateException())); // DOES NOT COMPILE
		
        // La variable opt es un <Double> opcional. Esto significa que el Supplier  debe devolver un Doble. 
        // Dado que este Supplier devuelve una excepción, el tipo no coincide.

        
		
		
	}
	
	/**
	 * 
	 * @param scores
	 * @return OPtional.Empty si el array de scores esta vario
	 * 		  Optional con la media en otro caso
	 */
	public static Optional<Double> media(int ...scores){
		
		if(scores.length == 0) {
			return Optional.empty();
		}
		int suma = 0;
		for(int score: scores) {
			suma = suma+score;
		}
		return Optional.of((double)suma/scores.length);
		
	}
```


![Descripción de la imagen](./resources/Figure1.jpg)

## Streams

Una secuencia en Java es una secuencia de datos. Un stream pipeline consiste en las operaciones que se ejecutan en un stream para producir un resultado. Primero, observamos conceptualmente pipelines. Después de eso, nos metemos en el código.

## Entendiendo el Pipeline Flow

## Crear Stream sources:

### Streams finitos
```java
	// crear un array vacio
	Stream<String> empty = Stream.empty();
	Stream<Integer> unElemento = Stream.of(1);
	Stream<Integer> masElementos = Stream.of(1, 2, 3);
```

Si ejecutasemos por pantalla e empty
```java
   System.out.println(empty);
        // Esto imprime algo así
        // java.util.stream.ReferencePipeline$Head@233fe9b6
```
```java
// Otra forma es generando cosas.
	Stream<Integer> imparesDebajo100 = Stream.iterate(
			1,                // seed
			n -> n < 100,     // Predicate to specify when done
			n -> n + 2);      // UnaryOperator to get next value

	var lista = imparesDebajo100.collect(Collectors.toList());
	lista.forEach(p -> System.out.print(""+p+","));
```

Para temas de concurrencia se ejecuta paralell Strem
```java
	24: var list = List.of("a", "b", "c");
	25: Stream<String> fromListParallel = list.parallelStream();
```


### Stream infinitos

```java
	17: Stream<Double> randoms = Stream.generate(Math::random);
	18: Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
```

La línea 17 genera un flujo de números aleatorios. ¿Cuántos números aleatorios? Los que necesites. Si llama a randoms.forEach(System.out::println), el programa imprimirá números aleatorios hasta que lo elimine. Más adelante en el capítulo, aprenderá sobre operaciones como limit() para convertir el flujo infinito en un flujo finito.

La línea 18 te da más control. El método iterar() toma una semilla o un valor inicial como primer parámetro. Este es el primer elemento que formará parte de la corriente. El otro parámetro es una expresión lambda que se pasa el valor anterior y genera el siguiente valor. Al igual que con el ejemplo de números aleatorios, seguirá produciendo números impares mientras los necesite.

## Hay que estudiarse esta tabla

![Descripción de la imagen](./resources/Figure7.jpg)


## Common Terminal Operations

![Descripción de la imagen](./resources/Figure2.jpg)

## Count
public long count()


## Encontrar el maximo y el minimo

La firma de los métodos es la siguiente
```java

	public Optional<T> min(Comparator<? super T> comparator)
	public Optional<T> max(Comparator<? super T> comparator)
```

Algunos ejemplos

```java
    public void minNumber() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        Optional<Integer> min = numbers.stream().min((i1, i2) -> i1.compareTo(i2));
        
        min.ifPresent(System.out::println); // 1
        
    }

    public void maxNumber() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        Optional<Integer> max = numbers.stream().max((i1, i2) -> i1.compareTo(i2));    

        
        max.ifPresent(System.out::println); // 100
    }
    
    public void sortedText() {
        
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s1.length()-s2.length());
        
        min.ifPresent(System.out::println); // ape
    }
```
    

¿Qué sucede si necesita los valores min() y max() del mismo flujo? Por ahora, no puede tener ambos, al menos no usar estos métodos. Recuerde, una secuencia solo puede tener una operación de terminal. Una vez que se ha ejecutado una operación de terminal, la secuencia no se puede volver a utilizar. Como verá más adelante en este capítulo, existen métodos de resumen incorporados para algunos flujos numéricos que calcularán un conjunto de valores por usted.

## findAny findFirst

Como su nombre lo indica, el método findAny() puede devolver cualquier elemento de la secuencia. Cuando se invoca en las secuencias que ha visto hasta ahora, normalmente devuelve el primer elemento, aunque este comportamiento no está garantizado.

Estos métodos son operaciones terminales pero no reducciones. La razón es que a veces regresan sin procesar todos los elementos.

El metodo de la firma

```java

	public Optional<T> findAny()
	public Optional<T> findFirst()
```

Este ejemplo encuentra un animal.

```java
	Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
	Stream<String> infinite = Stream.generate(() -> "chimp");
	
	s.findAny().ifPresent(System.out::println); // monkey (usually)
	infinite.findAny().ifPresent(System.out::println); // chimp
```

Encontrar cualquier coincidencia es más útil de lo que parece. A veces solo queremos probar los resultados y obtener un elemento representativo, pero no necesitamos desperdiciar el procesamiento para generarlos todos. Después de todo, si planeamos trabajar con un solo elemento, ¿por qué molestarnos en buscar más?

## Matching
Estos pueden o no terminar para flujos infinitos. Depende de los datos. Al igual que los métodos de búsqueda, no son reducciones porque no necesariamente analizan todos los elementos.

```java
	public boolean anyMatch(Predicate <? super T> predicate)
	public boolean allMatch(Predicate <? super T> predicate)
	public boolean noneMatch(Predicate <? super T> predicate)
```

```java
	var list = List.of("monkey", "2", "chimp");
	Stream<String> infinite = Stream.generate(() -> "chimp");
	Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
	
	System.out.println(list.stream().anyMatch(pred));   // true
	System.out.println(list.stream().allMatch(pred));   // false
	System.out.println(list.stream().noneMatch(pred));  // false
	System.out.println(infinite.anyMatch(pred));        // true

```

Esto muestra que podemos reutilizar el mismo predicado, pero necesitamos un flujo diferente cada vez. El método `anyMatch`() devuelve verdadero porque dos de los tres elementos coinciden. El método `allMatch()` devuelve falso porque uno no coincide. El método `noneMatch`() también devuelve falso porque al menos uno coincide. En el flujo infinito, se encuentra una coincidencia, por lo que la llamada finaliza. Si llamamos a `allMatch`(), se ejecutaría hasta que elimináramos el programa.

## forEach
```java

	public void forEach(Consumer<? super T> action)
```

Recuerde que puede llamar a `forEach`() directamente en una colección o en un flujo. No se confunda en el examen cuando vea ambos enfoques.

## Reduce

El metodo reduce combina un stream en un objeto simple

Hay tres firma de método:

```java
public T reduce(T identity, BinaryOperator<T> accumulator)
 
public Optional<T> reduce(BinaryOperator<T> accumulator)
 
public <U> U reduce(U identity,
   BiFunction<U,? super T,U> accumulator,
   BinaryOperator<U> combiner)
```

Mira el siguiente ejemplo para tratar de concatenar un array de Strings en un String simple sin programación funcional,
Tenemos algo como esto

```java 
	var array = new String[] { "w", "o", "l", "f" };
	var result = "";
	for (var s: array) 
		result = result + s;
	System.out.println(result); // wolf
```

- La *identidad* es el valor inicial de la reducción, en este caso un `String` vacio.
- El *acumulador* combinan el resultado acual con el valor actual del stream.

```java
	Stream<String> stream = Stream.of("w", "o", "l", "f");
	var result = stream.reduce("", (s, c) -> s+c) 
	System.out.println(result); // wolf
```
Observe cómo todavía tenemos la cadena vacía como identidad. También seguimos concatenando los objetos String para obtener el siguiente valor. Incluso podemos reescribir esto con una referencia de método:

```java
	Stream<String> stream = Stream.of("w", "o", "l", "f");
	String word = stream.reduce("", String::concat);
	System.out.println(result); // wolf
```

¿Puedes escribir una reducción para multiplicar todos los objetos Integer en una secuencia? Intentalo. Nuestra solución se muestra aquí:

```java
	Stream<Integer> stream = Stream.of(3, 5, 6);
	System.out.println(stream.reduce(1, (a, b) -> a*b)); // 90
```

Aquí ponemos la identidad a 1 y el acumulador a la multiplicación. En muchos casos, la identidad no es realmente necesaria, por lo que Java nos permite omitirla. Cuando no especifica una identidad, se devuelve un Opcional porque es posible que no haya ningún dato. Hay tres opciones para lo que está en el Opcional:

- Si el stream  está vacío, se devuelve un Opcional empty .
- Si el stream  tiene un elemento, se devuelve.
- Si el stream  tiene varios elementos, se aplica el acumulador para combinarlos.

A continuación se ilustra cada uno de estos escenarios:

```java
    Stream<Integer> empty = Stream.empty();
	Stream<Integer> element = Stream.of(15);
	Stream<Integer> elements = Stream.of(1,3,5,6);
	
	BinaryOperator<Integer> accumulator = (a,b) -> a*b;
	Optional<Integer> emptyResult = empty.reduce(accumulator);
	Optional<Integer> oneElement = element.reduce(accumulator);
	Optional<Integer> threeElements = elements.reduce(accumulator);
	
	emptyResult.reduce(accumulator).ifPresent(System.out::println);           // no output
	oneElement.reduce(opaccumulator).ifPresent(System.out::println);          // 3
	threeElements.reduce(accumulator).ifPresent(System.out::println);         // 90
```

La tercera firma del método `reduce` se utiliza cuando estamos lidiando con diferentes tipos de datos

```java
	Stream<String> stream = Stream.of("w", "o", "l", "f!");
	int length = stream.reduce(0, (i, s) -> i+s.length(), (a, b) -> a+b);
	System.out.println(length); // 5
```

El primer parámetro (0) es el valor del inicializador. Si tuviéramos un flujo vacío, esta sería la respuesta. El segundo parámetro es el acumulador. A diferencia de los acumuladores que vio anteriormente, este maneja tipos de datos mixtos. En este ejemplo, el primer argumento, i, es un número entero, mientras que el segundo argumento, s, es una cadena. Agrega la longitud de la Cadena actual a nuestro total acumulado. El tercer parámetro se llama el combinador, que combina cualquier total intermedio. En este caso, a y b son ambos valores enteros.

## Collecting

El método `collect`() es un tipo especial de reducción llamado reducción mutable. Es más eficiente que una reducción regular porque usamos el mismo objeto mutable mientras acumulamos. Los objetos mutables comunes incluyen `StringBuilder` y `ArrayList`. Este es un método realmente útil, porque nos permite sacar datos de streams y ponerlos en otra forma. Las firmas del método son las siguientes:

```java
	public <R> R collect(Supplier<R> supplier,
	BiConsumer<R, ? super T> accumulator,
	BiConsumer<R, R> combiner)
	
	public <R,A> R collect(Collector<? super T, A,R> collector)
```

Basandose en la primera firma del mentodo podemos querer crear un TreeSet o StringBuilder y podriamos hacerlos de la siguinte forma
```java

	Stream<String> stream = Stream.of("w", "o", "l", "f");
	
	StringBuilder word = stream.collect(
	StringBuilder::new,
	StringBuilder::append,
	StringBuilder::append);
	System.out.println(word); // wolf
```

```java
   Stream<String> stream = Stream.of("w", "o", "l", "f");
   TreeSet<String> set =    stream.collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append);
	System.out.println(set); // [f, l, o, w]
```
Si no necesitas ordenar el codigo es nas corto

```java
	Stream<String> stream = Stream.of("w", "o", "l", "f");
	Set<String> set = stream.collect(Collectors.toSet());
	System.out.println(set); // [f, w, l, o]
```
## Using Common Intermediate Operations

## Filter

```java
	public Stream<T> filter(Predicate<? super T> predicate)
```
This operation is easy to remember and powerful because we can pass any ``Predicate`` to it. For example, this retains all elements that begin with the letter m:

```java
	Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
	s.filter(x -> x.startsWith("m"))
	.forEach(System.out::print); // monkey
```

## Removing Duplicates

public Stream<T> distinct()

```java
	Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
	s.distinct()
   .forEach(System.out::print); // duckgoose

```
## Restricting by Position

Los métodos `limit`() y `skip`() pueden hacer que un Stream sea más pequeño, o `limit`() podría hacer un flujo finito a partir de un flujo infinito. Las firmas del método se muestran aquí:

```java
	public Stream<T> limit(long maxSize)
	public Stream<T> skip(long n)
```

El siguiente código crea una secuencia infinita de números que cuentan desde 1. La operación skip() devuelve una secuencia infinita que comienza con los números que cuentan desde 6, ya que omite los primeros cinco elementos. La llamada limit() toma los dos primeros de esos. Ahora tenemos un flujo finito con dos elementos, que luego podemos imprimir con el método forEach():

```java
	Stream<Integer> s = Stream.iterate(1, n -> n + 1);
	s.skip(5)
	.limit(2)
	.forEach(System.out::print); // 67
```

El método map() crea un mapeo uno a uno de los elementos en la secuencia a los elementos del siguiente paso en la secuencia. La firma del método es la siguiente:

```java
	public <R> Stream<R> map(Function<? super T, ? extends R> mapper)
```

Este parece más complicado que los otros que has visto. Utiliza la expresión lambda para averiguar el tipo pasado a esa función y el devuelto. El tipo de devolución es la secuencia que se devuelve.

```java
	public class Book {
		private String name;
		private int releaseYear;
		private String is
	}
        List<Book> lista = List.of(
                new Book("dddRa", 1999, "123654"),
                new Book("ssa historia interminable", 1984, "1236asd54"),
                new Book("aaaRatatui", 1990, "123654asd"));
        
        
        List<String> listaTitulos = lista.stream()
            .map(Book::getName)
            .collect(Collectors.toList());
        
        listaTitulos.stream()
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);
                        // AAARATATUI
                        // DDDRA
                        // SSA HISTORIA INTERMINABLE
     
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
            s.map(String::length)
           .forEach(System.out::print); // 676
```


## FlatMap

El método flatMap() toma cada elemento de la secuencia y convierte los elementos que contiene en elementos de nivel superior en una sola secuencia. Esto es útil cuando desea eliminar elementos vacíos de una secuencia o combinar una secuencia de listas. Le mostramos la firma del método para mantener la coherencia con los otros métodos para que no crea que estamos ocultando nada. No se espera que puedas leer esto:

```java
public <R> Stream<R> flatMap( Functin<? super T, ? extends Stream<? extends R>> mapper)
```
Este galimatías básicamente dice que devuelve un Stream del tipo que contiene la función en un nivel inferior. No te preocupes por la firma. Es un dolor de cabeza.

Lo que debes entender es el ejemplo. Esto pone a todos los animales en el mismo nivel y elimina la lista vacía.

List<String> cero = List.of();
var uno = Lista.de("Bonobo");
var dos = List.of("Mamá Gorila", "Bebé Gorila");
Stream<List<String>> animales = Stream.of(cero, uno, dos);
 
animales.flatMap(m -> m.stream())
   .forEach(Sistema.out::println);
Aquí está la salida:

Bonobo
mamá gorila
bebé gorila
Como puede ver, eliminó la lista vacía por completo y cambió todos los elementos de cada lista para que estuvieran en el nivel superior de la transmisión.