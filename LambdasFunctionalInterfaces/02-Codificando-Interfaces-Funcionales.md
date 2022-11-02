## Interfaces funcionales.

Una interfaz funcional es una interfaz que contiene un solo método abstracto (SAM)

```java 
@FunctionalInterface
public interface Spring {
	public void sprint(int speed);
}

public class Tiger implements Sprint {
	public void sprint(int speed) {
		...
	}
}
```
Es una interfaz funcional

```java 
public interface Dash extends Sprint {}
```

Esta es una interfaz funcional, porque contiene exactamente un método abstracto heredado.

```java 
public interface Skip extends Sprint {
	void skip();
}
```
Esto no se debe a que tenga dos métodos abstractos. El heredado y skip()

```java 
public interface Sleep {
	private void snore() {}
	default int getZZZ() { return 1; }
}
```
Esto no es así porque ningún método coincide con los criterios.

```java 
public interface Climb {
	void reach();
	default void fall() {}
	static int getBackUp() { return 100; }
	private static boolean checkHeight() { return true; }
}
```

Esta es una interfaz funcional. A pesar de definir una gran cantidad de métodos, contiene un método abstracto: reach().

Todas las clases heredan ciertos métodos de Object. Para el examen debe estar familiarizado con

```java
String toString();

boolean equals(Object);

int hashCode();
```

Hay una excepción a la regla del método abstracto único. Si una interfaz funcional incluye un método abstracto con la misma firma que un publicmétodo que se encuentra en Object, esos métodos no cuentan para el método abstracto único.

```java
public interface Soar {
	abstract String toString();
}
```
Esta no es una interfaz funcional, ya que` toString()`es un método `public` implementado dentro de `Object`.

Por otro lado, esta es una interfaz funcional.


```java
public interface Dive {
	String toString();
	public boolean equals(Object o);
	public abstract int hashCode();
	public void dive();
}
```

(!) Ten cuidado con los ejemplos que se parecen a los métodos de la clase `Object` . (!)

```java
public interface Hibernate {
	String toString();
	public boolean equals(Hibernate o);
	public abstract int hashCode();
	public void rest();
}
```
Hibernate no es una interfaz válida.

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
- lambda variables

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
9:         play(() -> baby ? "hitch a ride": "run"); // parameter var
10:        play(() -> approach); // local variable
11:    }
12:    void play(Gorilla g) {
13:        System.out.println(g.move());
14:    }
15: }
```

- La línea 8 usa una variable de instancia en lambda. 
- La línea 9 usa un parámetro de método. Sabemos que es efectivamente final ya que no hay reasignaciones a esa variable. 
- La línea 10 utiliza una variable local efectivamente final. Si descomentamos la línea 6, habrá una reasignación y la variable ya no será efectivamente final. Esto provocaría un error del compilador en la línea 10 cuando intenta acceder a una variable final no efectiva.

## Interfaces funcionales

La unica regla que tiene que tener una interfaz funcional es que no puede tener mas que método public abstract.

La anotacion `@FunctionalInterfaz` es una interfaz de marcado explicitamente, cuya unica labor es informativa y preventiva, por lo tanto si nos saltamos la regla nos avisa con error de compilación.

```java 
@FunctionalInterface
public interface Dance { // NO COMPILA
    void  one();
    void two();
}
```

Considerando Sprint una interface funcional. Cual de las siguientes son interfaces funcionales?