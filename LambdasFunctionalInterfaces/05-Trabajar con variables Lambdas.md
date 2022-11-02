## Trabajar con variables lambda

Lista de parámetros
Especificar el tipo de parámetros es opcional. Ahora varse puede usar en una lista de parámetros lambda. Esto significa que todos estos 3 son válidos.

```java
Predicate<String> p = x -> true;
Predicate<String> p = (var x) -> true;
Predicate<String> p = (String x) -> true;

```

### Restricciones sobre el uso de var en la lista de parámetros

Si `var` se usa para uno de los tipos en la lista de parámetros, entonces debe usarse para todos los parámetros en la lista.

```java
(var num) -> 1
(var a, var b) -> "Hello"
(var b, var k, var m) -> 3.14159
```

```java
6:  var w -> 99 // DOES NOT COMPILE
7:  (var a, Integer b) -> true // DOES NOT COMPILE
8:  (String x, var y, Integer z) -> true // DOES NOT COMPILE
```

- La línea 6 no se compila porque se requieren paréntesis cuando se usa el nombre del parámetro. 
- Las líneas 7 y 8 no se compilan porque los tipos de parámetros incluyen una combinación de vary nombres de tipos.

### Variables locales dentro del cuerpo Lambda

Es legal definir una lambda como un bloque.

```java
(a, b) -> {
	int c = 0;
	return 5;
}
```
Sin embargo, no está permitido volver a declarar una variable.


```java
(a, b) -> {
	int a = 0; // DOES NOT COMPILE
	return 5;
}
```
(!) ¡ Los bloques Lambda deben terminar con un punto y coma! (!)

```java
public void variables(int a) {
	int b = 1;

	Predicate<Integer> p1 = a -> {
		int c = 0;
		return b == c; } // DOES NOT COMPILE. MISSING ;
}
```

Variables a las que se hace referencia desde el cuerpo de Lambda
Los cuerpos lambda pueden usar `static` variables, variables de instancia y variables locales si son efectivamente finales.

```java
public class Crow {
	private String color;

	public void caw(String name) {
		String volume = "loudly";
		Predicate<String> p = s -> (name+volume+color).length() == 10;
	}
}
```

(!) Si la variable local no es  efectivamente final, entonces el código no  compila. (!)

```java
public class Crow {
	private String color;

	public void caw(String name) {
		String volume = "loudly";
		color = "allowed";
		name = "not allowed";
		volume = "not allowed";

		Predicate<String> p =
			s -> (name+volume+color).length()==9; // DOES NOT COMPILE
	}
}
```