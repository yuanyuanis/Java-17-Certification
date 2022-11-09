# Indice de contenidos

`Modules` es la forma que tiene Java SE de gestionar las librerias de terceros sin necesidad de herramientas como Maven.

Se subdivide el contenido de este tema en los siguientes ficheros independientes:

1. [Introduction Modules.md](01-Introduction-Modules.md)
2. [Crear y ejecutar un Modular Program.md](02-Crear-y-ejecutar-un-Modular-Program.md)
3. [Actualizando Nuestro Ejemplo para Multiples Modules.md](03-Actualizando-Nuestro-Ejemplo-para-Multiples-Modules.md)
4. [Inmersión en la Module Declaration.md](04-Inmersion-en-la-Module-Declaration.md)
5. [Crear un Service.md](05-Crear-un-Service.md)
6. [Descubriendo Modules.md](06-Descubriendo-Modules.md)
7. [Comparando los tipos de Modules.md](07-Comparando-los-tipos-de-Modules.md)
8. [Migrando una Aplicacion.md](08-Migrando-una-Aplicacion.md)
9. [Sumario.md](09-Sumario.md)


# Partes esenciales del examen

**Cree ficheros module-info.java.**

Coloque el fichero module-info.java en el directorio raíz del módulo. 
- Sepa cómo codificar directivas de `exports`, `requires`, `provides`, y `uses` . 
- Además, familiarícese con la directiva `opens`.

**Utilice operaciones en línea de comandos con módulos.**

- El comando `java` puede describir un módulo, enumerar los módulos disponibles, o mostrar ***Module Resolution***. 
- El comando `jar` puede describir un módulo de forma similar a como lo hace el comando java. 
- El comando `jdeps` imprime detalles sobre *modules* y *packages*. 
- El comando `jmod` proporciona varios modos para trabajar con ficheros *JMOD* en lugar de ficheros *JAR*. 
- El comando `jlink` crea imágenes Java personalizadas.

**Identifique los tres tipos de módulos.**

1. ***Named Modules*** son JAR que han sido modularizads. 
2. Los módulos sin nombre(***Unnamed***) no se han modularizado. 
3. Los ***Automatic modules*** están en el medio. Están en la ruta del módulo pero no tienen un archivo module-info.java.

**Listar los módulos JDK integrados.**

El módulo `java.base` está disponible para todos los módulos. Hay alrededor de otros 20 módulos proporcionados por el JDK que comienzan con `java.*` y alrededor de 30 que comienzan con `jdk.*`.

**Explicar las dos estratefias de migracion top-down y bottom-up**

- Una migración ***top-down*** coloca todos los archivos JAR en la ruta del módulo, lo que los convierte en módulos automáticos durante la migración ***top-down***. 
- Una migración ***bottom-up*** deja todos los archivos JAR en el classpath, lo que los convierte en módulos sin nombre mientras se migra ***bottom-up***.

**Diferencia las cuatro partes de un service.**

1. Una ***service provider interface***  declara la interfaz que debe implementar un servicio. 
2. El ***service locator***  busca el servicio.
3. Un ***consumer***  llama al servicio. 
4. Un ***service provider*** implementa el servicio.