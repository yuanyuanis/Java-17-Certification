
Tabla de contenidos:

    // TODO

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

Pero NO podemos escribir campos o métodos no estáticos
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


