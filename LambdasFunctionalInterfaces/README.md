
Tabla de contenidos:

    // TODO reubicar la clase record

## Nuevas clases record

Es un tipo especial de clase con unas caracteristicas determinadas, que se hace para simplificar.
Un `record` especifica es su cabecera la descripcion de su contenido(campos). A partir de estos simplemente se generan los getters y setters automaticamente. Viene hacer lo que hace loombook pero con restricciones.

Aqui un ejemplo de `record`

```java 
    record Rectangle(double length, double width) { }
```

Esto es equivalente a la siguiente clase normal

```java 
public final class Rectangle {
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double length() { return this.length; }
    double width()  { return this.width; }

    // Implementation of equals() and hashCode(), which specify
    // that two record objects are equal if they
    // are of the same type and contain equal field values.
    public boolean equals...
    public int hashCode...

    // An implementation of toString() that returns a string
    // representation of all the record class's fields,
    // including their names.
    public String toString() {...}
}
```

Como vemos se han generado los accessors, constructor, equals, hashCode, y toString con una pequeña diferencia
no exite el set. Esto es entre otras cosas porque un record tiene las siguientes caracteristicas

Una clase `record` declara los siguientes miembros automáticamente:

- Para cada componente del encabezado, los siguientes dos miembros:
    - atributos `private final` con el mismo nombre y tipo declarado que el componente de `record`. 
    - Un método public con el mismo nombre y tipo; en el `record` *Rectangle*, estos métodos son `Rectangle::length()` y `Rectangle::width()`.

- Un constructor canónico cuya firma es la misma que la del encabezado, que asigna los valores las propiedades.

- Implementaciones de los métodos `equals` y `hashCode`, que especifican que dos clases de registros son iguales si son del mismo tipo y contienen valores de componentes iguales.
- Una implementación del `toString` método que incluye la representación de cadena de todos los componentes de la clase de `record`, con sus nombres.
- Como las clases `record` son solo tipos especiales de clases, crea un objeto de `record` (una instancia de una clase de registro) con `new`

```java
    Rectangle r = new Rectangle(4,5);
```

## Creando nuestros propios ccontructores en un record.

Podemos hacerlo perfectamente para añadir alguna funcionalidad, como por ejemplo lanzar una excepcion

```java
record Rectangle(double length, double width) {
    public Rectangle(double length, double width) {
        if (length <= 0 || width <= 0) {
            throw new java.lang.IllegalArgumentException(
                String.format("Invalid dimensions: %f, %f", length, width));
        }
        this.length = length;
        this.width = width;
    }
}
```
Se han inventado unas cosas llamadas constructores compactos, donde podemos omitir los parametros del constructor y las asignaciones, que son siempre obligatorios.

```java
record Rectangle(double length, double width) {
    public Rectangle {
        if (length <= 0 || width <= 0) {
            throw new java.lang.IllegalArgumentException(
                String.format("Invalid dimensions: %f, %f", length, width));
        }
    }
}
```

De igual modo podemos sobreescribir el método *`get`* que se recurerda SE OMITE en el accesor, ya que no hay `set`

```java
record Rectangle(double length, double width) {
 
    // Public accessor method
    public double length() {
        System.out.println("Length is " + length);
        return length;
    }
}
```

Podemos declarar campos estáticos, inicializadores estáticos o  métodos estáticos 
```java
record Rectangle(double length, double width) {
    
    // Static field
    static double goldenRatio;

    // Static initializer
    static {
        goldenRatio = (1 + Math.sqrt(5)) / 2;
    }

    // Static method
    public static Rectangle createGoldenRectangle(double width) {
        return new Rectangle(width, width * goldenRatio);
    }
}
```

Pero NO podemos escribir campos o inicializadores estáticos
El siguiente coódigo NO compila

```java
record Rectangle(double length, double width) {

    // Field declarations must be static:
    BiFunction<Double, Double, Double> diagonal;

    // Instance initializers are not allowed in records:
    {
        diagonal = (x, y) -> Math.sqrt(x*x + y*y);
    }
}
```

Puedes declarar métodos de instancia en una clase de `record`, independientemente de si implementa sus propios métodos de acceso. También puede declarar `clases` e `interfaces` anidadas en una clase de `record`, incluidas las clases de `record` anidadas (que son implícitamente estáticas). Por ejemplo:


```java
record Rectangle(double length, double width) {

    // Nested record class
    record RotationAngle(double angle) {
        public RotationAngle {
            angle = Math.toRadians(angle);
        }
    }
    
    // Public instance method
    public Rectangle getRotatedRectangleBoundingBox(double angle) {
        RotationAngle ra = new RotationAngle(angle);
        double x = Math.abs(length * Math.cos(ra.angle())) +
                   Math.abs(width * Math.sin(ra.angle()));
        double y = Math.abs(length * Math.sin(ra.angle())) +
                   Math.abs(width * Math.cos(ra.angle()));
        return new Rectangle(x, y);
    }
}
``` 

La clase `record` es final, es decir no puede ser extendida, pero mas haya de esas restricciones si podemos extender de otras clases o implementar interfaces

-  Puede crear una clase de `record` genérica, por ejemplo:

```java
record Triangle<C extends Coordinate> (C top, C left, C right) { }
```

- Puede declarar una clase de `record` que implemente una o más interfaces, por ejemplo:

```java
record Customer(...) implements Billable { }
```
- Puede usar anotaciones en una clase de `record` y sus componentes individuales, por ejemplo:

```java
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GreaterThanZero { }
```
```java
record Rectangle(
    @GreaterThanZero double length,
    @GreaterThanZero double width) { }
```

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

Since Java lambda expressions are effectively just methods, lambda expressions can take parameters just like methods. The (oldState, newState) part of the lambda expression shown earlier specifies the parameters the lambda expression takes. These parameters have to match the parameters of the method on the single method interface. In this case, these parameters have to match the parameters of the onStateChange() method of the StateChangeListener interface:

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

## Capturar o usar variables externas

## 1.1	  Una clase ‘effectively final’
En una clase singleton al marcar los constructores como privados, hemos marcado implícitamente la clase como final ''effectively final’’. 

Si se declaran todos los constructores privados en la clase singleton, entonces es imposible crear una subclase con un constructor; por lo tanto, la clase singleton es efectivamente final ''effectively final’’.

## 1.2 	‘Using Variables in Lambdas’

Las Lambda expressions pueden acceder a 
- static variables 
- instance variables
- parámetros en métodos effectively final  
- effectively final variables locales.

Las expresiones Lambda pueden acceder a variables estáticas, variables de instancia, parámetros de método finales efectivos y variables locales finales efectivas. ¿Cuántos de esos puedes encontrar en este ejemplo?

```java
1: interface Gorilla { String move(); }
2: class GorillaFamily {
3:     String walk = "walk";
4:     void everyonePlay(boolean baby) {
5:         String approach = "amble";
6:         //approach = "run";
7:
8:         play(() -> walk); // instance variable
9:         play(() -> baby ? "hitch a ride": "run");
10:        play(() -> approach);
11:    }
12:    void play(Gorilla g) {
13:        System.out.println(g.move());
14:    }
15: }
```

La línea 8 usa una variable de instancia en lambda. 
La línea 9 usa un parámetro de método. Sabemos que es efectivamente final ya que no hay reasignaciones a esa variable. 
La línea 10 utiliza una variable local efectivamente final. Si descomentamos la línea 6, habrá una reasignación y la variable ya no será efectivamente final. Esto provocaría un error del compilador en la línea 10 cuando intenta acceder a una variable final no efectiva.

## 1.3	Trabajando con interfaces funcionales preconstruidas.

vienen del paquete `java.util.function`

Este paquete contiene interfaces funcionales que proveen objetivos para usar lambda expresions y method reference

Una interfaz funcional puede tener multiples default methods pero solo uno abstracto.

![Descripción de la imagen](./resources/Figure1.jpg)


## Function

```java
public class _Funcion {

	public static void main(String[] args) {

		
		// LLamada a un método.
		int increment = increment(1);
		System.out.println(increment);
		
		// Llamada a una función.
		int increment2 = incrementByOneFunction.apply(1);
		System.out.println(increment2);
		
		// Llamada a una función.
		int multiply = multiplyBy10Function.apply(increment2);
		System.out.println(multiply);
		
		// Encadenando funciones
		Function<Integer, Integer> addByOneAndThenMultiplyBy10 = incrementByOneFunction.andThen(multiplyBy10Function);
		System.out.println(addByOneAndThenMultiplyBy10.apply(5));
		
	}

    static int increment(int number) {
		return number + 1;
	}

	static Function<Integer, Integer> incrementByOneFunction = 
			n -> n + 1;
			
	static Function<Integer, Integer> multiplyBy10Function =
			n -> n * 10;

	

}
```
En este ejemplo podemos ver la igualdad entre llamar a un metodo y llamar a una funcion, devolviendo en este caso una funcíon


## BiFunction

Una BiFunction es una Function que coge dos parametros como argumento de entrada.

## Implementing Supplier