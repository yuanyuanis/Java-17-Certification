## Lambdas

## Lambda Expressions vs. implementaciones Anonymous Interface 

Aunque las expresiones lambda están cerca de las implementaciones de interfaz anónima, hay algunas diferencias que vale la pena señalar.

La principal diferencia es que una implementación de interfaz anónima puede tener estado (variables miembro) mientras que una expresión lambda no puede. Mira esta interfaz:

```java
public interface MyEventConsumer {

    public void consume(Object event);

}
```

Esta interfaz se puede implementar usando una implementación de interfaz anónima, como esta:

```java
MyEventConsumer consumer = new MyEventConsumer() {
    public void consume(Object event){
        System.out.println(event.toString() + " consumed");
    }
};
```

Esta implementación anónima de MyEventConsumer puede tener su propio estado. Mira este rediseño:

```java
MyEventConsumer myEventConsumer = new MyEventConsumer() {
    private int eventCount = 0;
    public void consume(Object event) {
        System.out.println(event.toString() + " consumed " + this.eventCount++ + " times.");
    }
};
```
Observe cómo la implementación anónima de MyEventConsumer ahora tiene un campo llamado eventCount.

Una expresión lambda no puede tener dichos campos. Por lo tanto, se dice que una expresión lambda no tiene estado.

## Lambda Type Inference

Antes de Java 8, tendría que especificar qué interfaz implementar, al realizar implementaciones de interfaces anónimas. Aquí está el ejemplo de implementación de la interfaz anónima:

```java
stateOwner.addStateListener(new StateChangeListener() {

    public void onStateChange(State oldState, State newState) {
        // do something with the old and new state.
    }
});
```
Con las expresiones lambda, el tipo a menudo se puede inferir del código. Por ejemplo, el tipo de interfaz del parámetro puede deducirse de la declaración de método del método addStateListener() (un único método en la interfaz StateChangeListener). Esto se llama *inferencia de tipos*. El compilador infiere el tipo de un parámetro buscando el tipo en otra parte, en este caso, la definición del método. Este es el ejemplo del principio de este texto, que muestra que la interfaz `StateChangeListener` no se menciona en la expresión lambda:

```java
stateOwner.addStateListener(
    (oldState, newState) -> System.out.println("State changed")
);
```

En la expresión lambda, los tipos de parámetros a menudo también se pueden inferir. En el ejemplo anterior, el compilador puede inferir su tipo a partir de la declaración del método onStateChange(). Por lo tanto, el tipo de los parámetros oldState y newState se deducen de la declaración del método onStateChange().

## Lambda Parameters

Dado que las expresiones lambda de Java son efectivamente solo métodos, las expresiones lambda pueden tomar parámetros como los métodos. La parte (oldState, newState) de la expresión lambda mostrada anteriormente especifica los parámetros que toma la expresión lambda. Estos parámetros tienen que coincidir con los parámetros del método en la interfaz de método único. En este caso, estos parámetros deben coincidir con los parámetros del método `onStateChange()` de la interfaz `StateChangeListener`:

```java
public void onStateChange(State oldState, State newState);
```

Como mínimo, la cantidad de parámetros en la expresión lambda y el método deben coincidir.

En segundo lugar, si ha especificado algún tipo de parámetro en la expresión lambda, estos tipos también deben coincidir. Todavía no le he mostrado cómo poner tipos en los parámetros de expresión lambda (se muestra más adelante), pero simplemente en muchos casos no los necesita.

### Zero Parameters

Si el método con el que estás haciendo coincidir tu expresión lambda no tiene parámetros, entonces puedes escribir la expresión lambda así:

```java
() -> System.out.println("Zero parameter lambda");
```

Cuando ponemos parentesis vacios, es señal de que el método no tiene parametros.

### One Parameter

Si el método con el que estás comparando la expresión lambda de Java tiene un parámetro, puedes escribir la expresión lambda así:

```java
(param) -> System.out.println("One parameter: " + param);
```
El parentesis

Pero cuando una `lambda expression` tiene un solo parámetro, el paréntesis lo puedes omitir.

```java
 param -> System.out.println("One parameter: " + param);
```
### Multiple Parameters


Si el método con el que hace coincidir su expresión lambda de Java tien varios parámetros, los parámetros tienen que estar entre paréntesis. 
```java
(p1, p2) -> System.out.println("Multiple parameters: " + p1 + ", " + p2);
```

RECUERDA Solo cuando el método toma un solo parámetro se pueden omitir los paréntesis.

### Parameter Types

```java
(Car car) -> System.out.println("The car is: " + car.getName());  
```

Como puede ver, el tipo (Car) del parámetro car se escribe delante del propio nombre del parámetro, tal como lo harías al declarar un parámetro en un método.


## var Parameter Types from Java 11

Desde Java 11 podemos usar la palabra clave var como tipo de parámetro. La palabra clave var se introdujo en Java 10 como inferencia de tipo de variable local. Desde Java 11 var también se puede usar para tipos de parámetros lambda. 

Este es un ejemplo del uso de la palabra clave var de Java como tipos de parámetros en una expresión lambda:

```java
Function<String, String> toLowerCase = (var input) -> input.toLowerCase();
```

## Lambda Function Body

El cuerpo de una expresión lambda y, por lo tanto, el cuerpo de la función/método que representa, se especifica a la derecha de `->` en la declaración lambda

```java
 (oldState, newState) -> System.out.println("State changed")
```

Si tu expresión lambda tiene varias líneas, *`puedes`* encerrar el cuerpo de la función lambda dentro del corchete `{ }` 

```java
 (oldState, newState) -> {
    System.out.println("Old state: " + oldState);
    System.out.println("New state: " + newState);
  }
```
## Returning a Value From a Lambda Expression

Puedes retornar valores de expresiones lambda, al igual que puede hacerlo desde un método. Simplemente agregando una declaración de retorno al cuerpo de la función lambda

```java
 (param) -> {
    System.out.println("param: " + param);
    return "return value";
  }
```

En caso de que todo lo que tu expresión lambda esté haciendo sea calcular un valor de retorno y devolverlo, puedes especificar el valor de retorno de una manera más corta. 

```java
 (a1, a2) -> { return a1 > a2; }
 ```
o así:

```java
 (a1, a2) -> a1 > a2;
```
Luego, el compilador se da cuenta de que la expresión a1 > a2 es el valor de retorno de la expresión lambda (de ahí el nombre de expresiones lambda, ya que las expresiones devuelven un valor de algún tipo).




## Lambdas como Objetos

Una expresión Java lambda es esencialmente un objeto. Puedes asignar una expresión lambda a una variable y pasarla, como lo haces con cualquier otro objeto. Ejemplo:

```java
public interface MyComparator {

    public boolean compare(int a1, int a2);

}
MyComparator myComparator = (a1, a2) -> a1 > a2;

boolean result = myComparator.compare(2, 5);
```

## Valid lambdas?

Las siguientes son expresiones lambda validas

```java
    () -> true;
    x -> x.startswith("eco");
    (String x) -> x.startswith("eco");
    (x, y) -> {return x.startWith("eco");}
    (String x, String y) -> return x.startWith("eco");
``` 

Todos estos valores retornan un boolean, el 1 coge cero parámetros y devuelve boolean. El 2 coge uno y devuelve el resultado de la expresion. El tercero hace lo mismo pero define explicitamente el tipo.
El cuarto coge dos y solo usa uno, lo que es perfectamente valido y el quinto hace lo mismo definiendo los tipos entre parentesis.


```java 
1:  x, y -> x.startWith("perro") // Faltan parentesis a la izquierda.
2:  x -> {x.startWith("gato");} // Falta el return
3:  x -> {return x.startWith("gato")} // Falta el ;
4:  String x -> x.startWith("gato")  // Faltan parentesis a la izquierda.
```
- 1 y 4 Los parentesis son opcionales solo cuando hay un parametro y no tiene un tipo declarado.

```java
var invalid = (Animal a) -> a.canHoop(); // DOES NOT COMPILE 
```

- Recuerda que cuando hablamos de Java inferiedo informacion de Java por el contexto, pues bien aquí no hay contexto suficiente y por tanto java no puede determinar el tipo por lo que no compilará.

## Escribir expresiones lambda

Sintaxis lambda normal

```java
a -> a.canHop()
```

Sintaxis lambda completa

```java
(Animal a) -> { return a.canHop(); }
```

Los paréntesis se pueden omitir, solo si hay un solo parámetro y su tipo no se indica explícitamente. También podemos omitir las llaves cuando solo tenemos una declaración. Java no requiere que escriba `return` use un `;` cuando no se usan llaves.

Todas las siguientes son expresiones lambda válidas, suponiendo que haya interfaces funcionales que puedan consumirlas.

```java
() -> new Duck();
d -> { return d.quack(); }
(Duck d) -> d.quack()
(Animal a, Duck d) -> d.quack()
```

- El primero puede ser utilizado por una interfaz que contiene un método que no acepta argumentos y devuelve un Duck.
- El segundo y el tercero, ambos pueden ser utilizados por una interfaz que toma Duck como entrada y devuelve cualquiera que sea el tipo quack().
- El último puede ser utilizado por una interfaz que toma como entrada Animaly Duckobjetos y devuelve el tipo de quack().

Ahora vamos a comprobar si hay una sintaxis no válida.

```java
a, b -> a.startsWith("test");        // DOES NOT COMPILE
Duck d -> d.canQuack();              // DOES NOT COMPILE
a -> { a.startsWith("test"); }       // DOES NOT COMPILE
a -> { return a.startsWith("test") } // DOES NOT COMPILE
(Swan s, t) -> s.compareTo(t) != 0   // DOES NOT COMPILE
```

- Las líneas 1 y 2 requieren cada paréntesis alrededor de cada lista de parámetros. Los parámetros son opcionales solo cuando hay un parámetro y no tiene un tipo declarado.
- A la línea 3 le falta la `return` palabra clave, que es obligatoria ya que dijimos que la lambda debe devolver un `boolean`.
- A la línea 4 le falta el punto y coma `;` dentro de las llaves.
- A la línea 5 le falta el tipo de parámetro para t. Si el tipo de parámetro se especifica para un parámetro, debe especificarse para todos ellos.

## Trabajar con variables lambda

Lista de parámetros
Especificar el tipo de parámetros es opcional. Ahora varse puede usar en una lista de parámetros lambda. Esto significa que todos estos 3 son válidos.