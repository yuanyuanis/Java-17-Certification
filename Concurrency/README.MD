
# Capítulo 13 Concurrencia
# OBJETIVOS DEL EXAMEN OCP CUBIERTOS EN ESTE CAPÍTULO:

-   Administrar la ejecución de código concurrente
    - Cree worker threads utilizando Runnable y Callable, administre el ciclo de vida del threads, incluidas las automatizaciones proporcionadas por diferentes servicios Executor y API concurrency.
    - Desarrolle código  thread-safe, usando diferentes mecanismos de locking  y API Concurrent
    - Procese las colecciones de Java de forma concurrente, incluido el uso de  parallel streams
    - Trabajar con expresiones Streams y Lambda
    - Realizar decomposition, concatenation ayd reduction, y grouping y partitioning de forma secuencial y en parallel streams
  
Como aprenderá en el Capítulo 14, “E/S”, y el Capítulo 15, “JDBC”, las computadoras son capaces de leer y escribir datos en recursos externos. Desafortunadamente, en comparación con las operaciones de la CPU, estas operaciones de disco/red tienden a ser extremadamente lentas, tan lentas, de hecho, que si el sistema operativo de su computadora se detuviera y esperara a que finalice cada operación de disco o red, su computadora parecería congelar constantemente.

Afortunadamente, todos los sistemas operativos admiten lo que se conoce como procesamiento multiproceso. La idea detrás del procesamiento de subprocesos múltiples es permitir que una aplicación o grupo de aplicaciones ejecute varias tareas al mismo tiempo. Esto permite que las tareas que esperan otros recursos den paso a otras solicitudes de procesamiento.

En este capítulo, le presentamos el concepto de subprocesos y brindamos numerosas formas de administrar los subprocesos mediante la API de concurrencia. Los subprocesos y la concurrencia son temas difíciles de comprender para muchos programadores, ya que los problemas con los subprocesos pueden ser frustrantes incluso para los desarrolladores veteranos. En la práctica, los problemas de concurrencia se encuentran entre los problemas más difíciles de diagnosticar y resolver.

