# ¡Actualizando Nuestro Ejemplo para Multiples Modulos!

## Actualizando el modulo feeding

Dado que nuestros otros módulos tendrán código de llamada en el paquete zoo.animal.feeding, debemos declarar esta intención en la declaración del módulo.

La directiva de exportaciones se usa para indicar que un módulo pretende que esos paquetes sean usados ​​por código Java fuera del módulo. Como era de esperar, sin una directiva de exportaciones, el módulo solo está disponible para ejecutarse desde la línea de comandos por sí solo. En el siguiente ejemplo, exportamos un paquete:

```java
    module zoo.animal.feeding { 
        exports zoo.animal.feeding;
    }
```

Recompilar y volver a empaquetar el módulo actualizará module-info.class dentro de nuestro archivo zoo.animal.feeding.jar. Estos son los mismos comandos javac y jar que ejecutó anteriormente:

```console
    javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java
    jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .
```

## Creación de un módulo de atención

El paquete zoo.animal.care.medical tendrá las clases y los métodos destinados a otros módulos. El paquete zoo.animal.care.details solo será utilizado por este módulo. No se exportará desde el módulo. Piense en ello como la privacidad de la atención médica para los animales.

![](updatingourexampleformultiplemodules/Figure1.png)

El módulo contiene dos paquetes y clases básicos además del archivo module-info.java:

```java
    package zoo.animal.care.details; 
    import zoo.animal.feeding.*; 
    public class HippoBirthday {
        private Task task; 
    }

    package zoo.animal.care.medical; 
    public class Diet { }

    module zoo.animal.care {
        exports zoo.animal.care.medical; 
        requires zoo.animal.feeding;
    }
```

La instrucción require especifica que se necesita un módulo. El módulo zoo.animal.care depende del módulo zoo.animal.feeding.

```console
    javac -p mods -d care care/zoo/animal/care/details/*.java care/zoo/animal/care/medical/*.java care/module-info.java
```

Compilamos ambos paquetes y el archivo module-info.java. En el mundo real, usará una herramienta de compilación en lugar de hacerlo a mano. Para el examen, simplemente enumere todos los paquetes y/o archivos que desea compilar.

Ahora que hemos compilado el código, es hora de crear el módulo JAR:

## Crear el Módulo de Talks

Hasta ahora, hemos usado solo una declaración de exportaciones y requisitos en un módulo. Ahora aprenderá a gestionar la exportación de varios paquetes o la necesidad de varios módulos.

![](updatingourexampleformultiplemodules/Figure2.png)

Vamos a exportar los tres paquetes en este módulo.

![](updatingourexampleformultiplemodules/Figure3.png)

Primero, veamos el archivo module-info.java para zoo.animal.talks:

```java
    module zoo.animal.talks {
        exports zoo.animal.talks.content;
        exports zoo.animal.talks.media;
        exports zoo.animal.talks.schedule;
        requires zoo.animal.feeding;
        requires zoo.animal.care;
    }
```

Luego tenemos las seis clases, como se muestra aquí:

```java
    package zoo.animal.talks.content;

    public class ElephantScript {
    }

    package zoo.animal.talks.content;

    public class SeaLionScript {
    }

    package zoo.animal.talks.media;

    public class Announcement {
        public static void main(String[] args) {
            System.out.println("We will be having talks");
        }
    }

    package zoo.animal.talks.media;

    public class Signage {
    }

    package zoo.animal.talks.schedule;

    public class Weekday {
    }

    package zoo.animal.talks.schedule;

    public class Weekend {
    }

```
Si todavía está siguiendo en su computadora, cree estas clases en los paquetes. Los siguientes son los comandos para compilar y construir el módulo:

```console
    javac -p mods -d talks talks/zoo/animal/talks/content/*.java talks/zoo/animal/talks/media/*.java talks/zoo/animal/talks/schedule/*.java talks/module-info.java

    jar -cvf mods/zoo.animal.talks.jar -C talks/ .
```

## Creación del módulo de personal

Nuestro módulo final es zoo.staff. La figura 12.10 muestra que solo hay un paquete adentro. No expondremos este paquete fuera del módulo.

![](updatingourexampleformultiplemodules/Figure4.png)

![](updatingourexampleformultiplemodules/Figure5.png)

Hay tres flechas en la Figura 12.11 que apuntan desde zoo.staff a otros módulos. Estos representan los tres módulos que se requieren. Dado que no se deben exponer paquetes de zoo.staff, no hay declaraciones de exportación. Esto nos da:

```java
    module zoo.staff {
        requires zoo.animal.feeding; 
        requires zoo.animal.care; 
        requires zoo.animal.talks;
    }
```

En este módulo, tenemos una sola clase en el archivo Jobs.java:

```java
    package zoo.staff;
    public class Jobs { }
```

Para aquellos de ustedes que siguen en su computadora, creen una clase en el paquete. Los siguientes son los comandos para compilar y construir el módulo:

```console
    javac -p mods -d staff  staff/zoo/staff/*.java staff/module-info.java

    jar -cvf mods/zoo.staff.jar -C staff/ .
```