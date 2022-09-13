# Date, Strings and Localization.

1. Use Java SE 8 Date/Time API
    - Crear y gestionar date y time events incluyendo combinación de date y tiempos en un Objeto simple.
       - LocalDate
       - LocalTime
       - LocalDateTime
       - Instant
       - Period
       - Duration
    - Trabajar con Dates y Times con distintos TimeZone y gestionar cambios en los resultados de “daylight savings” incluyendo Format y date values.
    - Definir, crear y gestionar eventos que se basen en fechas time(Java 8) incluyendo:
        - Instant
        - Period 
        - Durationgit
        - TemporalUnit


2. Localización.
    - Leer y establecer el local con ayuda de Locale
    - Crear y leer archivos de propiedades 
    - Construir un resource bundle por cada locale y cargar el bundle en una aplicación.

En la versión 8 de Java Oracle rompe con la forma  que tenía para representar las fechas.  Se ha añadido a la versión 8 el paquete java.time, no obstante en el examen, si bien no se menciona como funciona la antigua API, si que puede haber preguntas que usen las anteriores clases. 

    import java.time.*;

## 1.	Creating Date and Times.

En el mundo real normalmente usamos las fechas y los timezones como si la otra persona estuviese cerca de nosotros. Por ejemplo, si dices, “Te llamaré a las 11:00 el Lunes, asumes que las 11 significa lo mismo para los dos. Pero si vives en Madrid y la otra persona en New York se necesita ser más específico”. 

En Madrid el 18 de Agosto a las 9:00 am se usan los siguientes TimeZones
| Hora      | Descripción |
| ----------- | ----------- |
| Hora estándar:      |  UTC/GMT +1 hora|       |
| Horario de verano:   | +1 hora        |


Sin embargo en New York son 6 horas menos, su hora estándar es GMT -4

| Hora      | Descripción |
| ----------- | ----------- |
| Hora estándar:      |  UTC/GMT -5 horas|       |
| Horario de verano:   | +1 hora        |


Hay una serie de abreviaturas que ayudan a entendernos EST(Eastern Standard Time) El horario de new york o el horario central Europeo CET(Central European Time) o CEST en horario de verano(Central European Summer Time).

Así que para comunicarnos con propiedad con alguien que esté en otra zona horaria tendremos que decir la zona horaria. Podremos quedar con un neoyorkino para una teleconferencia a las 13:00 EST por ejemplo, que significa que en verano quedaremos a las siete de la tarde en Centro Europa.

**LocalDate** → Simplemente un Date, sin timezone ni Time. Por ejemplo la fecha de un cumpleaños, un festivo, el día de fin de año etc.

`2020-05-27`



**LocalTime** → Solo un tiempo, sin Date, ni Zon3. Ejemplo es media noche.

`09:13:07.768`

**LocalDateTime** → Contiene las dos cosas hora y fecha, asociado a un local.

`2015-05-25T09:13:07.768`

**ZonedDateTime** → Contiene el además de la fecha y la hora la zona horaria. Un buen ejemplo es cuando, tienes una conferencia a las 9: AM EST … Si vives en Madrid y quieres asistir a esa conferencia tienes que hacer los cálculos para poder asistir. Al igual que si, por ejemplo, un youtuber anuncia de qué va a hacer un directo a las 13:00 EST(Imaginemos que tiene seguidores en todo el mundo) no va a poner en el anuncio con todas las correspondencias de las fechas horarias en todo el mundo, eres tú el que haces los cálculos a partir de su fecha, hora y zona horaria.

`2015-05-25T09:13:07.769-04:00[America/New_York].`


Con respecto a esto último, señalar que Java usa la ‘T’ para separar Date y Time, cuando lo convierte a String.

¿Qué es GMT? viene de Greenwich Mean Time y es un ‘time zone’ en Europa que es usado como la time zero cuando hablamos precisamente de la Zona Cero, es decir la hora que no tiene +1 o +N o menos -N. 

![Meridiano de Greenwich](https://services.meteored.com/img/article/desplazamiento-del-meridiano-de-greenwich---1_1024.jpg)

También habrás oído hablar, si te has tenido que pegar con las fechas de UTC que viene de Coordinated Universal Time que es es un time zone standar(Standar a nivel planetario). Lo importante es que UTC usa al mismo time Zone zero qué GMT.


Asegurémonos de que comprendemos cómo funciona UTC. Incluimos nombres de time zones en los ejemplos para que sean más fáciles de representar. El examen te dará la UTC
compensar. No se espera que memorices ninguna zona horaria.

Primero, intentemos averiguar qué tan distantes están estos momentos en el tiempo. Observa cómo la India tiene una compensación de media hora, no una hora completa. Para abordar un problema como este, resta la zona horaria(Time Zone) de la hora. Esto le da el equivalente GMT de la hora:

```
2015-06-20T07:50+02:00[Europe/Paris] --> GMT 2015-06-20 5:50

2015-06-20T06:50+05:30[Asia/Kolkata] --> GMT 2015-06-20 1:20

```

Como vemos en la misma hora UTC hay una diferencia horaria de 4 horas y media entre París y Kolkata(que no tengo ni idea de donde esta … pero para el caso, nos es igual :-)


```
Notas de Examen: El time zone offset(que significa el 
desplazamiento de zona horaria) puede ser representada de distintas formas: 
+02: 00, GMT + 2
UTC + 2  
Lo importante es que sepas que todos significan lo mismo. Es posible que veas alguno de ellos en el examen.

```