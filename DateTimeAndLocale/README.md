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

## 1.1	Las Fechas y las zonas horarias.

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

## 1.2 Tipos de objectos Java 8 para Fechas.

**`LocalDate`** → Simplemente un Date, sin timezone ni Time. Por ejemplo la fecha de un cumpleaños, un festivo, el día de fin de año etc.

    2020-05-27



**`LocalTime`** → Solo un tiempo, sin Date, ni Zona. Ejemplo es media noche asociado al timeZone local.

    09:13:07.768

**`LocalDateTime`** → Contiene las dos cosas hora y fecha, asociado al timeZone local.

    2015-05-25T09:13:07.768

**`ZonedDateTime`** → Contiene el además de la fecha y la hora la zona horaria. Un buen ejemplo es cuando, tienes una conferencia a las 9: AM EST … Si vives en Madrid y quieres asistir a esa conferencia tienes que hacer los cálculos para poder asistir. Al igual que si, por ejemplo, un youtuber anuncia de qué va a hacer un directo a las 13:00 EST(Imaginemos que tiene seguidores en todo el mundo) no va a poner en el anuncio con todas las correspondencias de las fechas horarias en todo el mundo, eres tú el que haces los cálculos a partir de su fecha, hora y zona horaria.

    2015-05-25T09:13:07.769-04:00[America/New_York].


Con respecto a esto último, señalar que Java usa la ‘T’ para separar Date y Time, cuando lo convierte a String.

## 1.3 ¿Qué es eso de GMT y UTC? 

Viene de Greenwich Mean Time y es un ‘time zone’ en Europa que es usado como la time zero cuando hablamos precisamente de la Zona Cero, es decir la hora que no tiene +1 o +N o menos -N. 

```c
    GMT 2015-06-20 5:50 // El jodido tiempo absoluto en Greenwich
```
![Meridiano de Greenwich](https://services.meteored.com/img/article/desplazamiento-del-meridiano-de-greenwich---1_1024.jpg)

También habrás oído hablar, si te has tenido que pegar con las fechas, de UTC ... que viene de Coordinated Universal Time que es es un time zone standar(Standar a nivel planetario). Lo importante a saber es que UTC usa al mismo time Zone zero qué GMT.

Asegurémonos de que comprendemos cómo funciona UTC. Incluimos nombres de time zones en los ejemplos para que sean más fáciles de representar. El examen te dará la UTC compensar. No se espera que memorices ninguna zona horaria.

```c
    2015-06-20T07:50+02:00[Europe/Paris]  // El jodido UTC te da la compensacion
````
Primero, intentemos averiguar qué tan distantes están estos momentos en el tiempo. Observa cómo la India tiene una compensación de media hora, no una hora completa. Para abordar un problema como este, resta la zona horaria(Time Zone) de la hora. Esto le da el equivalente GMT de la hora:

```c
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

## 1.4 Instanciando LocalDate

```java
    LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
    LocalDate date2 = LocalDate.of(2015, 1, 20);
```

Las dos fechas son iguales.

`Month` es un enum . Recuerda que un enum no es un int y no puede ser comparado con uno, por ejemplo:
```java
    Month month = Month.JANUARY;
    boolean b1 = month == 1; // DOES NOT COMPILE
    boolean b2 = month == Month.APRIL; // false
```

El método of está sobrescrito y puedes usar diferentes configuraciones para crear la Fecha(Date) o la hora(Time) que desees.

```java
    LocalTime time1 = LocalTime.of(6, 15); // hour and minute
    LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
    LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
```


Podemos usar `LocalDateTime` para combinar fecha y hora.

```java
    LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
    LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
```


Para crear un `ZonedDateTime`, primero necesitamos obtener la zona horaria deseada. Lo haremos use US / Eastern en nuestros ejemplos

```java
    ZoneId zone = ZoneId.of("US/Eastern");
    ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20,
    6, 15, 30, 200, zone);
    ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
    ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);
```



Descubrir su zona horaria es fácil. Simplemente puede imprimir `ZoneId.systemDefault().` `ZoneId` es una clase que también proporciona la nueva API de java 8 

```java
    ZoneId.getAvailableZoneIds().stream()
    .filter(z -> z.contains("US") || z.contains("America"))
    .sorted().forEach(System.out::println);
    //This printed 177 lines when we ran it. We prefer the US/Eastern time //zone to America/New_York since it is more general.
```

## 1.5  Manipulando fechas y horas.

Las fechas y las horas son inmutables, así que tenemos que asegurarnos de pasarla a una variable de referencia, de forma que no sean perdidas. Aquí ejemplos de cómo se suma:

```java
    LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
    System.out.println(date); // 2014-01-20
    date = date.plusDays(2);
    System.out.println(date); // 2014-01-22
    date = date.plusWeeks(1);
    System.out.println(date); // 2014-01-29
    date = date.plusMonths(1);
    System.out.println(date); // 2014-02-28
    date = date.plusYears(5);
    System.out.println(date); // 2019-02-28
```


Ejemplos de cómo se resta

```java
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    LocalTime time = LocalTime.of(5, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    System.out.println(dateTime);      // 2020-01-20T05:15
    dateTime = dateTime.minusDays(1);
    System.out.println(dateTime);      // 2020-01-19T05:15
    dateTime = dateTime.minusHours(10);
    System.out.println(dateTime);      // 2020-01-18T19:15
    dateTime = dateTime.minusSeconds(30);
    System.out.println(dateTime);      // 2020-01-18T19:14:30
```


En java 8 es común que los métodos de fecha y hora estén encadenados. Por ejemplo, sin la impresión declaraciones, el ejemplo anterior se podría reescribir de la siguiente manera:

```java
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    LocalTime time = LocalTime.of(5, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time).minusDays(1).minusHours(10).minusSeconds(30);

```
Cuando tienes que hacer muchas manipulaciones, este encadenamiento de fechas resulta útil. Existen dos formas en las que los creadores de exámenes pueden intentar engañarte. ¿Qué crees que imprime esto?

```java
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    date.plusDays(10);
    System.out.println(date);
```

Se imprime el 20 de enero de 2020. Agregar 10 días fue inútil porque el programa ignoró el resultado. Siempre que veas tipos inmutables, presta atención para asegurarte de que el valor de retorno de una llamada a un método no se ignora. 

El examen también puede probar para ver si recuerda lo que cada los objetos de LocalDate y Time incluyen. ¿Ves lo que está mal aquí?

```java
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    date = date.plusMinutes(1); // DOES NOT COMPILE
```

LocalDate no tiene hora, así que no puedes añadirle minutos …

## 1.6   Trabajar con periodos:
¡Ahora sabes lo suficiente para hacer algo divertido con las fechas! Nuestro zoológico realiza enriquecimiento animal actividades para darles a los animales algo divertido que hacer de vez en cuando. El jefe del zoológico ha decidido cambiar los juguetes todos los meses. Este sistema continuará durante tres meses para ver cómo funciona.
```java
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        performAnimalEnrichment(start, end);
    }
    private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) { // check if still before end
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plusMonths(1); // add a month
        }   
    }   
```

Este código funciona bien. Agrega un mes a la fecha hasta que llega a la fecha de finalización. El problema es que este método no se puede reutilizar. Nuestro cuidador quiere probar diferentes horarios para ver cuál funciona mejor.

Por suerte, Java tiene una clase Period class que puede ser pasada como parámetro:

```java
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        Period period = Period.ofMonths(1); // create a period
        performAnimalEnrichment(start, end, period);
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end,
    Period period) { // uses the generic period
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plus(period); // adds the period
        }      
    }
```

Distintos modos legales de crear periodos

```java
Period annually = Period.ofYears(1); // every 1 year
Period quarterly = Period.ofMonths(3); // every 3 months
Period everyThreeWeeks = Period.ofWeeks(3); // every 3 weeks
Period everyOtherDay = Period.ofDays(2); // every 2 days
Period everyYearAndAWeek = Period.of(1, 0, 7); // every year and 7 days
```

## Imprimir Periods

![Descripción de la imagen](./resources/Figure2.jpg)

```java
    System.out.println(Period.ofMonths(3));
```
El output es P3M. Recuerda que Java omite cualquier medida que sea cero. Probemos con otro.

```java
    System.out.println(Period.of(0, 20, 47));
```
El output es  P20M47D. No hay años así que se omite esa parte. Está bien tener más
días que en un mes. También está bien tener más meses de los que hay en un año. Usos de Java las medidas previstas para cada uno.

Ahora intentemos uno complicado:

```java
    System.out.println(Period.ofWeeks(3));
```
Imprime P20M47D ya que no hay semanas en Periodos para la salida.

```java
    LocalDate date = LocalDate.of(2015, 1, 20);
    LocalTime time = LocalTime.of(6, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    Period period = Period.ofMonths(1);
    System.out.println(date.plus(period)); // 2015–02–20
    System.out.println(dateTime.plus(period)); // 2015–02–20T06:15
    System.out.println(time.plus(period)); // UnsupportedTemporalTypeException
```

## 1D   ---- Trabajar con Durations

Las duraciones son para periodos más pequeños que no son fechas. Podemos especificar, días, horas, minutos, segundos o milisegundos. 
Y sí, podrías pasar 365 días para hacer un año, pero no debería hacerlo, para eso es Period.

Podemos crear Duration con diferentes granularidades:
```java
    Duration daily = Duration.ofDays(1); // PT24H
    Duration hourly = Duration.ofHours(1); // PT1H
    Duration everyMinute = Duration.ofMinutes(1); // PT1M
    Duration everyTenSeconds = Duration.ofSeconds(10); // PT10S
    Duration everyMilli = Duration.ofMillis(1); // PT0.001S
    Duration everyNano = Duration.ofNanos(1); // PT0.000000001S
```

Hay otras formas de especificar … 
```java
    Duration daily = Duration.of(1, ChronoUnit.DAYS);
    Duration hourly = Duration.of(1, ChronoUnit.HOURS);
    Duration everyMinute = Duration.of(1, ChronoUnit.MINUTES);
    Duration everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS);
    Duration everyMilli = Duration.of(1, ChronoUnit.MILLIS);
    Duration everyNano = Duration.of(1, ChronoUnit.NANOS);

    LocalTime one = LocalTime.of(5, 15);
    LocalTime two = LocalTime.of(6, 30);
    LocalDate date = LocalDate.of(2016, 1, 20);
    System.out.println(ChronoUnit.HOURS.between(one, two)); // 1
    System.out.println(ChronoUnit.MINUTES.between(one, two)); // 75
    System.out.println(ChronoUnit.MINUTES.between(one, date)); // DateTimeException
```
Using a Duration works the same way as using a Period, for example:

```java
    LocalDate date = LocalDate.of(2015, 1, 20);
    LocalTime time = LocalTime.of(6, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    Duration duration = Duration.ofHours(6);
    System.out.println(dateTime.plus(duration)); // 2015–01–20T12:15
    System.out.println(time.plus(duration)); // 12:15
    System.out.println(date.plus(duration)); // UnsupportedTemporalException
```

Let’s try that again, but add 23 hours this time.


```java

    LocalDate date = LocalDate.of(2015, 1, 20);
    LocalTime time = LocalTime.of(6, 15);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    Duration duration = Duration.ofHours(23);
    System.out.println(dateTime.plus(duration)); // 2015–01–21T05:15
    System.out.println(time.plus(duration)); // 05:15
    System.out.println(date.plus(duration)); // UnsupportedTemporalException
```
## Trabajar con Instants

Un instant representa un momento específico en el tiempo en el GMT time zone. Supón que queremos hacer correr un reloj


```java
    Instant now = Instant.now();
    // hacemos algo que consuma tiempo
    Instant later = Instant.now();
    Duration duration = Duration.between(now, later);
    System.out.println(duration.toMillis());
```

Si tienes un ZonedDateTime, lo puedes pasar a un Instant.

```java
    LocalDate date = LocalDate.of(2015, 5, 25);
    LocalTime time = LocalTime.of(11, 55, 00);
    ZoneId zone = ZoneId.of("US/Eastern");
    ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zone);
    Instant instant = zonedDateTime.toInstant(); // 2015-05-25T15:55:00Z
    System.out.println(zonedDateTime); // 2015-05-25T11:55-04:00[US/Eastern]
    System.out.println(instant); // 2015-05-25T15:55:00Z
```

Las últimas dos líneas representan el mismo momento en el tiempo.  La ZonedDateTime incluye un time zone.

Usando ese Instant, puedes hacer matemáticas. Instant le permite agregar cualquier unidad de día o menos, por ejemplo:

```java
    Instant nextDay = instant.plus(1, ChronoUnit.DAYS);
    System.out.println(nextDay); // 2015–05–26T15:55:00Z
    Instant nextHour = instant.plus(1, ChronoUnit.HOURS);
    System.out.println(nextHour); // 2015–05–25T16:55:00Z
    Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS); // exception
```

Es raro pero Instant muestra los meses y los años, pero no te deja hacer operaciones sobre ellos. Esto hay que saberlo para el examen. 

## Accounting for Daylight Saving Time.

Como sabemos muchos países tienen estas modificaciones para hacer un mejor uso de las horas solares tanto en invierno como en verano.

En el caso de USA, en Marzo se mueven los relojes una hora más en verano y una hora menos en invierno.

![Descripción de la imagen](./resources/Figure3.jpg)

Por ejemplo, el 13 de marzo de 2016, adelantamos nuestros relojes una hora y saltamos de `2:00 a.m. a 3:00 a.m.` Esto significa que no hay 2:30 a.m. ese día. 

Si quisiéramos saber la hora una hora después de la 1:30, serían las 3:30.

```java
    LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
    LocalTime time = LocalTime.of(1, 30);
    ZoneId zone = ZoneId.of("US/Eastern");
    ZonedDateTime dateTime = ZonedDateTime.of(date, time, zone);
    System.out.println(dateTime); // 2016-03-13T01:30-05:00[US/Eastern]
    dateTime = dateTime.plusHours(1);
    System.out.println(dateTime); // 2016-03-13T03:30-04:00[US/Eastern]
```

Aquí un ejemplo restando 

```java
    LocalDate date = LocalDate.of(2016, Month.NOVEMBER, 6);
    LocalTime time = LocalTime.of(1, 30);
    ZoneId zone = ZoneId.of("US/Eastern");
    ZonedDateTime dateTime = ZonedDateTime.of(date, time, zone);
    System.out.println(dateTime); // 2016-11-06T01:30-04:00[US/Eastern]
    dateTime = dateTime.plusHours(1);
    System.out.println(dateTime); // 2016-11-06T01:30-05:00[US/Eastern]
    dateTime = dateTime.plusHours(1);
    System.out.println(dateTime); // 2016-11-06T02:30-05:00[US/Eastern]
```

## Formateando Dates y Times
The date and time classes support many methods to get data out of them:

```java
LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
System.out.println(date.getDayOfWeek()); // MONDAY
System.out.println(date.getMonth()); // JANUARY
System.out.println(date.getYear()); // 2020
System.out.println(date.getDayOfYear()); // 20
```

We could use this information to display information about the date. However, it would be more work than necessary. Java provides a class called DateTimeFormatter to help us out. Unlike the LocalDateTime class, DateTimeFormatter can be used to format any type of date and/or time object. What changes is the format. DateTimeFormatter is in the package java.time.format.

```java
LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
LocalTime time = LocalTime.of(11, 12, 34);
LocalDateTime dateTime = LocalDateTime.of(date, time);
System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
```

ISO is a standard for dates. The output of the previous code looks like this:

```
2020–01–20
11:12:3
2020–01–20T11:12:34
```
This is a reasonable way for computers to communicate, but it is probably not how
you want to output the date and time in your program. Luckily, there are some predefined formats that are more useful:

```java
    DateTimeFormatter shortDateTime =
    DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    System.out.println(shortDateTime.format(dateTime)); // 1/20/20
    System.out.println(shortDateTime.format(date)); // 1/20/20
    System.out.println(
    shortDateTime.format(time)); // UnsupportedTemporalTypeException
```

Here we say that we want a localized formatter in the predefined short format. The
last line throws an exception because a time cannot be formatted as a date. The format() method is declared on both the formatter objects and the date/time objects, allowing you to reference the objects in either order. The following statements print exactly the same thing as the previous code:

```java
    DateTimeFormatter shortDateTime =
    DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    System.out.println(dateTime.format(shortDateTime));
    System.out.println(date.format(shortDateTime));
    System.out.println(time.format(shortDateTime));
```

In this book, we’ll change around the orders to get you used to seeing it both ways.
Table 5.10 shows the legal and illegal localized formatting methods.

There are two predefined formats that can show up on the exam: SHORT and MEDIUM. The
other predefined formats involve time zones, which are not on the exam.

```java
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    LocalTime time = LocalTime.of(11, 12, 34);
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    DateTimeFormatter shortF = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.SHORT);
    DateTimeFormatter mediumF = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.MEDIUM);
    System.out.println(shortF.format(dateTime)); // 1/20/20 11:12 AM
    System.out.println(mediumF.format(dateTime)); // Jan 20, 2020 11:12:34 AM
```

If you don’t want to use one of the predefined formats, you can create your own. For example, this code spells out the month:

```java
    DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
    System.out.println(dateTime.format(f)); // January 20, 2020, 11:12
```


Before we look at the syntax, know that you are not expected to memorize what the different numbers of each symbol mean. The most you will need to do is to recognize the date and time parts.
MMMM M represents the month. The more Ms you have, the more verbose the Java output. For example, M outputs 1, MM outputs 01, MMM outputs Jan, and MMMM outputs January. dd d represents the day in the month. As with month, the more ds you have, the more verbose the Java output. dd means to include the leading zero for a single-digit day. , 

Use , if you want to output a comma (this also appears after the year).

yyyy y represents the year. yy outputs a two-digit year and yyyy outputs a four-digit year.
hh h represents the hour. Use hh to include the leading zero if you’re outputting a single-digit hour.
: Use : if you want to output a colon.
mm m represents the minute omitting the leading zero if present. mm is more common and
represents the minutes using two digits.



