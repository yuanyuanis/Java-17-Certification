## Usando method references

Las referencias a métodos surge como una forma de hacer el código mas facil 

Una referencia de método y una lambda se comportan de la misma manera en tiempo de ejecución. Puedes fingir que el compilador convierte las referencias de su método en lambdas para tí.

Hay cuatro formatos para las referencias de métodos:

- métodos estáticos
- Métodos de instancia en un objeto en particular
- Métodos de instancia en un parámetro que se determinará en tiempo de ejecución
- Constructores


## static Methods
For the first example, we use a functional interface that converts a `double` to a `long`:

```java
interface Converter { 
   long round(double num);
}
```

Podemos implementar esta interfaz con el método `round()` en `Math`. Aquí asignamos una referencia de método y una lambda a esta interfaz funcional:

```java
14: Converter methodRef = Math::round;
15: Converter lambda = x -> Math.round(x);
16:
17: System.out.println(methodRef.round(100.1));  // 100
```

En la línea 14, hacemos referencia a un método con un parámetro y Java sabe que es como una lambda con un parámetro. Además, Java sabe pasar ese parámetro al método.

Espera un minuto. Es posible que sepa que el método `round()` está sobrecargado: puede tomar un `double` o un `float`. ¿Cómo sabe Java que queremos llamar a la versión con un doble? Con  ambos, referencias a métodos y lambdas, Java infiere información del contexto. En este caso, dijimos que estábamos declarando un convertidor, que tiene un método que toma un parámetro doble. Java busca un método que coincida con esa descripción. Si no puede encontrarlo o encuentra varias coincidencias, el compilador informará un error. Este último a veces se denomina error de tipo *ambigous*.

## Métodos de instancia en un objeto en particular

Para este ejemplo, nuestra interfaz funcional verifica si una cadena comienza con un valor específico:

```java
    interface StringStart {
    boolean beginningCheck(String prefix);
    }
```
Convenientemente, la clase `String` tiene un método `beginWith()` que toma un parámetro y devuelve un valor `boolean`. Veamos cómo usar referencias de métodos con este código:

```java
    18: var str = "Zoo";
    19: StringStart methodRef = str::startsWith;
    20: StringStart lambda = s -> str.startsWith(s);
    21:
    22: System.out.println(methodRef.beginningCheck("A"));  // false
```
- La linea 19 muestra que queremos llamar a str.startsWith() y pasar un solo parámetro para que se suministre en tiempo de ejecución. Esta sería una buena manera de filtrar los datos en una lista.

Una referencia de método no tiene que tomar ningún parámetro. En este ejemplo, creamos una interfaz funcional con un método que no toma ningún parámetro pero devuelve un valor:

```java
    interface StringChecker {
        boolean check();
    }
```
Lo implementamos preguntando si está vacio

```java
    18: var str = "";
    19: StringChecker methodRef = str::isEmpty;
    20: StringChecker lambda = () -> str.isEmpty();
    21:
    22: System.out.print(methodRef.check());  // true
```
- Dado que el método en String es un método de instancia, llamamos a la referencia del método en una instancia de la clase String.

- Si bien todas los method references se pueden convertir en lambdas, lo contrario no siempre es cierto. Por ejemplo, considere este código:

```java
    var str = "";
    StringChecker lambda = () -> str.startsWith("Zoo");

```
¿Cómo podríamos escribir esto como una referencia de método? Puede intentar uno de los siguientes:

```java
    StringChecker methodReference = str::startsWith;         // DOES NOT COMPILE
    StringChecker methodReference = str::startsWith("Zoo");  // DOES NOT COMPILE
```
¡Ninguno de estos funciona! Si bien podemos pasar el str como parte de la referencia del método, no hay forma de pasar el parámetro "Zoo" con él. Por lo tanto, no es posible escribir esta lambda como referencia de método.

## Calling Instance Methods on a Parameter

Esta vez, vamos a llamar al mismo método de instancia que no toma ningún parámetro. El truco es que lo haremos sin conocer la instancia de antemano. Esta vez necesitamos una interfaz funcional diferente, ya que necesita conocer la `String`:

```java
interface StringParameterChecker {
   boolean check(String text);
}
```

Podemos implementar la interfaz funcional así:
```java

    23: StringParameterChecker methodRef = String::isEmpty;
    24: StringParameterChecker lambda = s -> s.isEmpty();
    25:
    26: System.out.println(methodRef.check("Zoo"));  // false
```

- La línea 23 dice que el método que queremos llamar está declarado en `String`. Parece un método estático, pero no lo es. En cambio, Java sabe que `isEmpty()` es un método de instancia que no toma ningún parámetro. Java utiliza el parámetro proporcionado en tiempo de ejecución como la instancia en la que se llama al método.

Compare las líneas 23 y 24 con las líneas 19 y 20 de nuestro ejemplo de instancia. Parecen similares, aunque uno hace referencia a una variable local llamada str, mientras que el otro solo hace referencia a los parámetros de la interfaz funcional.

Incluso puedemos combinar los dos tipos de referencias de métodos de instancia. Nuevamente, necesitamos una nueva interfaz funcional que tome dos parámetros:

```java
    interface StringTwoParameterChecker {
         boolean check(String text, String prefix);
    }
```

Pon atencion al orden de los parámetros cuando leemos la implementacion:

```java
    26: StringTwoParameterChecker methodRef = String::startsWith;
    27: StringTwoParameterChecker lambda = (s, p) -> s.startsWith(p);
    28:
    29: System.out.println(methodRef.check("Zoo", "A"));  // false
```
Dado que la interfaz funcional toma dos parámetros, Java tiene que averiguar qué representan. La primera siempre será la instancia del objeto para los métodos de instancia. Cualquier otro debe ser un parámetro de método.

Recuerde que la línea 26 puede parecer un método estático, pero en realidad es una referencia de método que declara que la instancia del objeto se especificará más adelante. La línea 27 muestra parte del poder de una referencia de método. Pudimos reemplazar dos parámetros lambda esta vez.

## Llamando a constructores

Una referencia de constructor es un tipo especial de referencia de método que usa `new` en lugar de un método, e instancia un objeto. Para este ejemplo, nuestra interfaz funcional no tomará ningún parámetro pero devolverá una `String`:

```java
    interface EmptyStringCreator {
         String create();
    }
```

Para llamar a esto, usamos `new` como si fuera el nombre de un método:

```java
    30: EmptyStringCreator methodRef = String::new;
    31: EmptyStringCreator lambda = () -> new String();
    32:
    33: var myString = methodRef.create();
    34: System.out.println(myString.equals("Snake"));  // false 
```

Se expande como las referencias de métodos que has visto hasta ahora. En el ejemplo anterior, la lambda no tiene ningún parámetro.

Las referencias a métodos pueden ser complicadas. Esta vez creamos una interfaz funcional que toma un parámetro y devuelve un resultado:

```java
    interface StringCopier {
        String copy(String value);
    }
```
En la implementación, observe que la línea 32 en el siguiente ejemplo tiene la misma referencia de método que la línea 30 en el ejemplo anterior:

```java
    32: StringCopier methodRef = String::new;
    33: StringCopier lambda = x -> new String(x);
    34:
    35: var myString = methodRef.copy("Zebra");
    36: System.out.println(myString.equals("Zebra"));  // true
```

Esto significa que no siempre puede determinar a qué método se puede llamar mirando la referencia del método. En cambio, debe mirar el contexto para ver qué parámetros se usan y si hay un tipo de devolución. En este ejemplo, Java ve que estamos pasando un parámetro String y llama al constructor de String que toma dicho parámetro.

|  Type |   Before colon|  After colon	 |   Example|   
|---|---|---|---|
| static methods  |  Method name |  Method name |   Math::random|   
|  Instance methods on a particular object|  variable name |  Method name	 |  str::startsWith |   
| Instance methods on a parameter  | Class name  |  Method name	 |   	String::isEmpty|   
| Constructor  |   Class name| new  | String::new  |  