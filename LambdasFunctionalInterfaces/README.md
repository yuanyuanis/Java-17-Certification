# Indice 

En este tema aprenderemos todo lo relativo a Interfaces funcionales y expresiones lambda, centrandonos en sintaxis de las lambda 
programar interfaces funionales, asi como trabajar con las interfaces funionales por Estandar.

Para hacerlo he subdivido el tema en los siguientes archivos:

1.  [Nueva clase record presente en ejemplos](00-Nueva Clase record.md)
2.  [Escribir lambdas simples](01-Escribir Simples Lambdas.md)
3.  [Aprender a codificar Interfaces Funcionales](02-Codificando Interfaces Funcionales.md)
4.  [Trabajar con method References](03-Method references.md)
5.  [Trabajar con las Interfaces Funcionales estandar de Java 8](04-Trabajar con las Interfaces Funcionales estandar.md)
6.  [Usar correctamente las variables lambda](05-Trabajar con variables Lambdas.md)

# Fundamentos del examen
__Escribe expresiones lambda simples.__

-  Busque la presencia o ausencia de elementos opcionales en el código lambda. Los tipos de parámetros son opcionales. Las llaves y la palabra clave de retorno son opcionales cuando el cuerpo es una declaración única. Los paréntesis son opcionales cuando solo se especifica un parámetro y el tipo es implícito.

**Determinar si una variable se puede utilizar en un cuerpo lambda.**

-  Las variables locales y los parámetros del método deben ser finales o efectivamente finales para ser referenciados. Esto significa que el código debe compilarse si fuera a agregar la palabra clave final a estas variables. Las variables de instancia y clase siempre están permitidas.

**Traduzca las referencias de métodos a la lambda de "formato largo".**

- Ser capaz de convertir referencias de métodos en expresiones lambda regulares y viceversa. Por ejemplo, System.out::print yx -> System.out.print(x) son equivalentes. Recuerde que el orden de los parámetros del método se deduce cuando se utiliza una referencia de método.

**Determinar si una interfaz es una interfaz funcional.**

-   Utilice la regla del método abstracto único (SAM) para determinar si una interfaz es una interfaz funcional. Otros tipos de métodos de interfaz (predeterminado, privado, estático y estático privado) no cuentan para el recuento de métodos abstractos únicos, ni tampoco los métodos públicos con firmas que se encuentran en Object.

**Identifique la interfaz funcional correcta dada la cantidad de parámetros, el tipo de devolución y el nombre del método, y viceversa.**

- Las interfaces funcionales más comunes son Supplier, Consumer, Function, u Predicate. También hay versiones binarias y versiones primitivas de muchos de estos métodos. Puede usar la cantidad de parámetros y el tipo de retorno para distinguirlos.
