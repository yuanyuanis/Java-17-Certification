# Descubriendo Modules

Hasta ahora, hemos estado trabajando con módulos que escribimos. Incluso las clases integradas en el JDK están modularizadas. En esta sección, le mostramos cómo usar los comandos para aprender sobre los módulos.

## Identificando Built-in Modules

El módulo más importante que debe conocer es `java.base`. Contiene la mayoría de los paquetes que ha estado aprendiendo para el examen. De hecho, es tan importante que ni siquiera tiene que usar la directiva `require`; está disponible para todas las aplicaciones modulares. Su archivo module-info.java aún se compilará si necesita explícitamente java.base. Sin embargo, es redundante, por lo que es mejor omitirlo. La Tabla 12.6 enumera algunos módulos comunes y lo que contienen.

![](discoveringmodules/Common-modules.png)

Los creadores del examen creen que es importante reconocer los nombres de los módulos proporcionados por el JDK. Si bien no necesita saber los nombres de memoria, sí debe poder elegirlos de una alineación.

Para el examen, debe saber que los nombres de los módulos comienzan con java para las API que probablemente usará y con jdk para las API que son específicas de JDK. La tabla 12.7 enumera todos los módulos que comienzan con ja

![](discoveringmodules/Java-modules-prefixed-with-java.png)

La Tabla 12.8 enumera todos los módulos que comienzan con jdk. Recomendamos revisar esto justo antes del examen para aumentar las posibilidades de que suenen familiares. Recuerda que no tienes que memorizarlos.

![](discoveringmodules/Java-modules-prefixed-with-jdk.png)

## Getting Details with java

El comando java tiene tres opciones relacionadas con el módulo. Uno describe un módulo, otro enumera los módulos disponibles y el tercero muestra la lógica de resolución del módulo.

También es posible agregar módulos, exportaciones y más en la línea de comando. Pero por favor no lo hagas. Es confuso y difícil de mantener. Tenga en cuenta que estos indicadores están disponibles en Java, pero no en todos los comandos.

### Describing a Module

Suponga que recibe el archivo JAR del módulo zoo.animal.feeding y desea conocer la estructura del módulo. Podrías "descomprimirlo" y abrir el archivo module-info.java. Esto le mostraría que el módulo exporta un paquete y no requiere explícitamente ningún módulo.

module zoo.animal.feeding { 

    module zoo.animal.feeding { 
        exports zoo.animal.feeding;
    }

Sin embargo, hay una manera más fácil. El comando java tiene una opción para describir un módulo. Los siguientes dos comandos son equivalentes:

    java -p mods -d zoo.animal.feeding

    java -p mods --describe-module zoo.animal.feeding

En las clases, el paquete java.lang se importa automáticamente tanto si lo escribe como si no. El módulo `java.base` funciona de la misma manera. Está automáticamente disponible para todos los demás módulos.

**More about Describing Modules**

Solo necesita saber cómo ejecutar --describe-module para el examen en lugar de interpretar el resultado. Sin embargo, es posible que encuentre algunas sorpresas al experimentar con esta función, por lo que las describimos con un poco más de detalle aquí.

Suponga que los siguientes son los contenidos de module-info.java en zoo.animal.care:

```python
    module zoo.animal.care {
        exports zoo.animal.care.medical to zoo.staff; 
        requires transitive zoo.animal.feeding;
    }
```

Now we have the command to describe the module and the output.

    java -p mods -d zoo.animal.care

    zoo.animal.care file:///absolutePath/mods/zoo.animal.care.jar 
    requires zoo.animal.feeding transitive
    requires java.base mandated
    qualified exports zoo.animal.care.medical to zoo.staff contains zoo.animal.care.details

The first line of the output is the absolute path of the module file.The two requires lines should look familiar as
well.The first is in the module-info, and the other is added to all modules. Next comes something new.The qualified
exports is the full name of the package we are exporting to a specific module.

Finally, the contains means that there is a package in the module that is not exported at all.This is true. Our module
has two packages, and one is available only to code inside the module.

### Listing Available Modules

In addition to describing modules, you can use the java command to list the modules that are available. The simplest
form lists the modules that are part of the JDK.

    java --list-modules

More interestingly, you can use this command with custom code. Let’s try again with the directory containing our zoo
modules.

    java -p mods --list-modules

Note that --list-modules exits as soon as it prints the observable mod- ules. It does not run the program.

### Showing Module Resolution

If listing the modules doesn’t give you enough output, you can also use the --show-module-resolution option. You can
think of it as a way of debugging modules. It spits out a lot of output when the program starts up. Then it runs the
program.

    java --show-module-resolution -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task

## Describing with jar

Like the java command, the jar command can describe a module. These commands are equivalent:

    jar -f mods/zoo.animal.feeding.jar -d
    jar --file mods/zoo.animal.feeding.jar --describe-module

The output is slightly different from when we used the java command to describe the module. With jar, it outputs the
following:

    zoo.animal.feeding jar:file:///absolutePath/mods/zoo.animal.feeding.jar /!module-info.class
    exports zoo.animal.feeding
    requires java.base mandated

The JAR version includes the module-info.class in the filename, which is not a partic- ularly significant difference in
the scheme of things. You don’t need to know this difference. You do need to know that both commands can describe a
module.

## Learning about Dependencies with jdeps

The jdeps command gives you information about dependencies within a module. Unlike describing a module, it looks at the
code in addition to the module declaration. This tells you what dependencies are actually used rather than simply
declared. Luckily, you are not expected to memorize all the options for the exam.

You are expected to understand how to use jdeps with projects that have not yet been modularized to assist in
identifying dependencies and problems.

    package zoo.dinos;

    import java.time.*;
    import java.util.*;
    
    import sun.misc.Unsafe;
    
    public class Animatronic {
        private List<String> names;
        private LocalDate visitDate;
    
        public Animatronic(List<String> names, LocalDate visitDate) {
            this.names = names;
            this.visitDate = visitDate;
        }
    
        public void unsafeMethod() {
            Unsafe unsafe = Unsafe.getUnsafe();
        }
    }

Now we can compile this file. You might have noticed that there is no module-info.java file. That is because we aren’t
creating a module. We are looking into what dependencies we will need when we do modularize this JAR.

    javac zoo/dinos/*.java

Compiling works, but it gives you some warnings about Unsafe being an internal API.

    Next, we create a JAR file.

    jar -cvf zoo.dino.jar .

We can run the jdeps command against this JAR to learn about its dependencies. First, let’s run the command without any
options. On the first two lines, the command prints the modules that we would need to add with a requires directive to
migrate to the module system. It also prints a table showing what packages are used and what modules they correspond
to.

    jdeps zoo.dino.jar

Note that java.base is always included. It also says which modules contain classes used by the JAR. If we run in summary
mode, we only see just the first part where jdeps lists the modules. There are two formats for the summary flag:

    jdeps -s zoo.dino.jar
    
    jdeps -summary zoo.dino.jar

There is also a --module-path option that you can use if you want to look for modules outside the JDK. Unlike other
commands, there is no short form for this option on jdeps.

You might have noticed that jdk.unsupported is not in the list of modules you saw in Table 12.8. It’s special because it
contains internal libraries that developers in previous versions of Java were discouraged from using, although many
people ignored this warning. You should not reference it, as it may disappear in future versions of Java.

## Using the --jdk-internals Flag

The jdeps command has an option to provide details about these unsupported APIs. The output looks something like this:

    jdeps --jdk-internals zoo.dino.jar

The --jdk-internals option lists any classes you are using that call an internal API along with which API. At the end,
it provides a table suggesting what you should do about it. If you wrote the code calling the internal API, this message
is useful. If not, the message would be useful to the team that did write the code. You, on the other hand, might need
to update or replace that JAR file entirely with one that fixes the issue. Note that -jdkinternals is equivalent to
--jdk-internals.

**About sun.misc.Unsafe**

Prior to the Java Platform Module System, classes had to be public if you wanted them to be used outside the package. It
was reasonable to use the class in JDK code since that is low-level code that is already tightly coupled to the JDK.

Since it was needed in multiple packages, the class was made public. Sun even named it Unsafe, figuring that would
prevent anyone from using it outside the JDK.

However, developers are clever and used the class since it was available. A number of widely used open source libraries
started using Unsafe. While it is quite unlikely that you are using this class in your project directly, you probably
use an open source library that is using it.

The jdeps command allows you to look at these JARs to see whether you will have any problems when Oracle finally
prevents the usage of this class. If you find any uses, you can look at whether there is a later version of the JAR that
you can upgrade to.

## Using Module Files with jmod

The final command you need to know for the exam is jmod. You might think a JMOD file is a Java module file. Not quite.
Oracle recommends using JAR files for most modules. JMOD files are recommended only when you have native libraries or
something that can’t go inside a JAR file. This is unlikely to affect you in the real world.

The most important thing to remember is that jmod is only for working with the JMOD files. Conveniently, you don’t have
to memorize the syntax for jmod. Table 12.9 lists the common modes.

![](discoveringmodules/Modes-using-jmod.png)

## Creating Java Runtimes with jlink

One of the benefits of modules is being able to supply just the parts of Java you need. Our zoo example from the
beginning of the chapter doesn’t have many dependencies.

If the user already doesn’t have Java or is on a device without much memory, downloading a JDK that is over 150 MB is a
big ask. Let’s see how big the package actually needs to be! This command creates our smaller distribution:

    jlink --module-path mods --add-modules zoo.animal.talks --output zooA

First we specify where to find the custom modules with -p or --module-path. Then we specify our module names with
--add-modules. This will include the dependencies it requires as long as they can be found. Finally, we specify the
folder name of our smaller JDK with --output.

There are many modules in the JDK we don’t need. Additionally, development tools like javac don’t need to be in a
runtime distribution.

## Reviewing Command-Line Option

This section presents a number of tables that cover what you need to know about running command-line options for the
exam.

![](discoveringmodules/Comparing-command-line-operations-1.png)

![](discoveringmodules/Comparing-command-line-operations-2.png)

![](discoveringmodules/Options-you-need-to-know-for-the-exam:javac.png)

![](discoveringmodules/Options-you-need-to-know-for-the-exam:java.png)

![](discoveringmodules/Options-you-need-to-know-for-the-exam:jar.png)

![](discoveringmodules/Options-you-need-to-know-for-the-exam:jdeps.png)