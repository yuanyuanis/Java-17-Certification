# Summary

El sistema de módulos de la plataforma Java organiza el código en un nivel más alto que los paquetes. Cada módulo contiene uno o más paquetes y un archivo module-info.java. El módulo java.base es el más común y se proporciona automáticamente a todos los módulos como una dependencia.

El proceso de compilación y ejecución de módulos utiliza --module-path, también conocido como -p. Ejecutar un módulo usa la opción --module, también conocida como -m. La clase a ejecutar se especifica en el formato moduleName/className.

El archivo de declaración del módulo admite una serie de directivas. La directiva de exportaciones especifica que se debe poder acceder a un paquete fuera del módulo. Opcionalmente, puede restringir esa exportación a un paquete específico. La directiva require se usa cuando un módulo depende del código de otro módulo. Además, los requisitos transitivos se pueden usar cuando todos los módulos que requieren un módulo siempre deben requerir otro. Las directivas provide y uses se utilizan al compartir y consumir un servicio. Finalmente, la directiva opens se usa para permitir el acceso a través de la reflexión.

Tanto los comandos java como jar se pueden usar para describir el contenido de un módulo. El comando java también puede enumerar los módulos disponibles y mostrar la resolución del módulo. El comando jdeps imprime información sobre los paquetes utilizados además de la información a nivel de módulo.

El comando jmod se usa cuando se trata de archivos que no cumplen con los requisitos para un JAR. El comando jlink crea una imagen de tiempo de ejecución de Java más pequeña.

Hay tres tipos de módulos. Los módulos con nombre contienen un archivo module-info.java y están en la ruta del módulo. Solo pueden leer desde la ruta del módulo. Los módulos automáticos también están en la ruta del módulo, pero aún no se han modularizado. Es posible que tengan un nombre de módulo automático establecido en el manifiesto. Los módulos sin nombre están en el classpath.

Las dos estrategias de migración más comunes son la migración de arriba hacia abajo y de abajo hacia arriba. La migración de arriba hacia abajo comienza a migrar el módulo con la mayor cantidad de dependencias y coloca todos los demás módulos en la ruta del módulo. La migración ascendente comienza a migrar un módulo sin dependencias y mueve un módulo a la ruta del módulo a la vez. Ambas estrategias requieren asegurarse de que no tenga dependencias cíclicas, ya que el sistema del módulo de la plataforma Java no permitirá la compilación de dependencias cíclicas.
