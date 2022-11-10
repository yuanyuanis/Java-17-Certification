# Comparing Types of Modules

Todos los módulos que hemos usado hasta ahora en este capítulo se denominan módulos con nombre. Hay otros dos tipos de módulos: módulos automáticos y módulos sin nombre. En esta sección, describimos estos tres tipos de módulos. En el examen, deberá poder compararlos.

## Named Modules

Un módulo con nombre es uno que contiene un archivo module-info.java. Para revisar, este archivo aparece en la raíz del JAR junto con uno o más paquetes. A menos que se especifique lo contrario, un módulo es un módulo con nombre. Los módulos con nombre aparecen en la ruta del módulo en lugar de en la ruta de clases. Más tarde, aprenderá qué sucede si un archivo JAR que contiene un archivo module-info.java está en el classpath. Por ahora, sepa que no se considera un módulo con nombre porque no está en la ruta del módulo.

Como una forma de recordar esto, un módulo con nombre tiene el nombre dentro del archivo module-info.java y está en la ruta del módulo.

## Automatic Modules

Aparece un módulo automático en la ruta del módulo pero no contiene un archivo module-info.java. Es simplemente un archivo JAR normal que se coloca en la ruta del módulo y se trata como un módulo.

Como una forma de recordar esto, Java determina automáticamente el nombre del módulo. El código que hace referencia a un módulo automático lo trata como si hubiera un archivo module-info.java presente. Exporta automáticamente todos los paquetes. También determina el nombre del módulo. ¿Cómo determina el nombre del módulo, te preguntarás? Excelente pregunta.

Cada archivo JAR contiene una carpeta especial llamada META-INF y, dentro de ella, un archivo de texto llamado MANIFEST.MF. Puede ser creado automáticamente cuando se crea el JAR o a mano por el autor del JAR.

Se animó a los autores a declarar el nombre que pretendían usar para el módulo agregando una propiedad denominada Automatic-Module-Name en su archivo MANIFEST.MF.

**About the MANIFEST.MF File**

Un archivo JAR contiene un archivo de texto especial llamado META-INF/MANIFEST.MF que contiene información sobre el JAR. Ha existido durante mucho más tiempo que los módulos, desde los primeros días de Java y los JAR, para ser exactos. La figura muestra cómo encaja el manifiesto en la estructura de directorios de un archivo JAR.

El manifiesto contiene información adicional sobre el archivo JAR. Por ejemplo, a menudo contiene la versión de Java utilizada para crear el archivo JAR. Para los programas de línea de comandos, la clase con el método main() se especifica comúnmente.

Cada línea en el manifiesto es un par clave/valor separado por dos puntos. Puede pensar en el manifiesto como un mapa de nombres y valores de propiedades. El manifiesto predeterminado en Java 17 tiene este aspecto:

```python
    Manifest-Version: 1.0
    Created-By: 17 (Oracle Corporation)
```

Specifying a single property in the manifest allowed library providers to make things easier for applications that
wanted to use their library in a modular application. You can think of it as a promise that when the library becomes a
named module, it will use the specified module name.

If the JAR file does not specify an automatic module name, Java will still allow you to use it in the module path. In
this case, Java will determine the module name for you.

Java determines the automatic module name by basing it on the filename of the JAR file. Let’s go over the rules by
starting with an example. Suppose we have a JAR file named holiday-calendar-1.0.0.jar.

First Java will remove the extension .jar from the name. Then Java will remove the version from the end of the JAR
filename. This is important because we want module names to be consistent. Having a different automatic module name
every time you upgraded to a new version would not be good! After all, this would force you to change the module
declaration of your nice, clean, modularized application every time you pulled in a later version of the holiday
calendar JAR.

Removing the version and extension gives us holiday-calendar. This leaves us with a problem. Dashes (-) are not allowed
in module names. Java solves this problem by converting any special characters in the name to dots (.). As a result, the
module name is holiday.calendar. Any characters other than letters and numbers are considered special characters in this
replacement. Finally, any adjacent dots or leading/trailing dots are removed.

Since that’s a number of rules, let’s review the algorithm in a list for determining the name of an automatic module:

- If the MANIFEST.MF specifies an Automatic-Module-Name, use that. Otherwise, proceed with the remaining rules.
- Remove the file extension from the JAR name.
- Remove any version information from the end of the name. A version is digits and dots with possible extra information
  at the end: for example, -1.0.0 or -1.0-RC.
- Replace any remaining characters other than letters and numbers with dots.
- Replace any sequences of dots with a single dot.
- Remove the dot if it is the first or last character of the result.

![](comparingtypesofmodules/Practicing-with-automatic-module-names.png)

## Unnamed Modules

An unnamed module appears on the classpath. Like an automatic module, it is a regular JAR. Unlike an automatic module,
it is on the classpath rather than the module path. This means an unnamed module is treated like old code and a
second-class citizen to modules.

An unnamed module does not usually contain a module-info.java file. If it happens to contain one, that file will be
ignored since it is on the classpath.

Unnamed modules do not export any packages to named or automatic modules. The unnamed module can read from any JARs on
the classpath or module path. You can think of an unnamed module as code that works the way Java worked before modules.
Yes, we know it is confusing for something that isn’t really a module to have the word module in its name.

You can expect to get questions on the exam comparing the three types of modules. Please study Table 12.17 thoroughly
and be prepared to answer questions about these items in any combination. A key point to remember is that code on the
classpath can access the module path. By contrast, code on the module path is unable to read from the classpath.

## Reviewing Module Types

You can expect to get questions on the exam comparing the three types of modules. Please study Table 12.17 thoroughly
and be prepared to answer questions about these items in any combination. A key point to remember is that code on the
classpath can access the module path. By contrast, code on the module path is unable to read from the classpath.

![](comparingtypesofmodules/Properties-of-module-types.png)

