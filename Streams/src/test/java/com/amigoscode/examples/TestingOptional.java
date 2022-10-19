package com.amigoscode.examples;

import java.util.NoSuchElementException;
import java.util.Optional;

public class TestingOptional {

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		
		System.out.println("media(50, 60, 80): "+media(50, 60, 80));
		System.out.println("media(): "+media());
		
		/***
		 * Resultado:
		 *  media(50, 60, 80): Optional[63.333333333333336]
			media(): Optional.empty
		 */
		
		// Normalmente queremos ver si hay valor para mostrarlo o no
		
		var resultado = media(92,55,44);
		if(resultado.isPresent()) 
			System.out.println(resultado.get());
		
		try {

			var resultadoVacio = media();
			resultadoVacio.get(); // NoSuchElementException
		} catch (NoSuchElementException e) {
			System.out.println("Si se extre un opcional vacio con get se obtiene excepcion:"+ e.getMessage());
		}
		
		
		// Al crear un Opcional, es común querer usar vacío() cuando el valor es nulo. Puede hacer esto con una instrucción 
		
		String value = null;
		Optional op1 = (value == null) ? Optional.empty() : Optional.of(value);
		// Si el valor es nulo, a o se le asigna el Opcional vacío. De lo contrario, envolvemos el valor. 
		// Dado que este es un patrón tan común, Java proporciona un método de fábrica para hacer lo mismo.

		Optional<String> op2 =Optional.ofNullable(value);
		
		/**
		 * Ya has visto get() e isPresent(). Los otros métodos le permiten escribir código que usa un Optional en una línea 
		 * sin tener que usar el operador ternario. Esto hace que el código sea más fácil de leer. 
		 * En lugar de usar una instrucción if, que usamos al verificar el promedio anteriormente, 
		 * podemos especificar que se ejecute un Consumer cuando haya un valor dentro de Optional. 
		 * Cuando no lo hay, el método simplemente omite la ejecución del Consumer.
		 */
		
		var resultado2 = media(2,5,45);
		resultado.ifPresent(System.out::println);
		
		// DEALING WITH EMPTY OPTIONAL
		// Si nuestro resultado es empty podemos querer hacer una acción, para eso tenemos la opcion orElse y orElseGet
		var resultadoVacio2 = media();
		System.out.println(resultadoVacio2.orElse(Double.NaN));
		System.out.println(resultadoVacio2.orElseGet(() -> Math.random()));
		
		// Imprime algo asi
		// NaN
		// 0.39556685131214986
		
		// Tambien podemos querer lanzar una excepción tenemos dos opciones
		// orElseThrow
		// orElseThrow(Supplier s)

		var resultadoVacio3 = media();
		try {
			resultadoVacio3.orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			resultadoVacio3.orElseThrow(() -> new IllegalStateException(" ¡La lista de scores no puede estar vacia tronco! "));
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		/*
		System.out.println(media().orElseGet(
				   () -> new IllegalStateException())); // DOES NOT COMPILE
		*/
		
		
		
	}
	
	/**
	 * 
	 * @param scores
	 * @return OPtional.Empty si el array de scores esta vario
	 * 		  Optional con la media en otro caso
	 */
	public static Optional<Double> media(int ...scores){
		
		if(scores.length == 0) {
			return Optional.empty();
		}
		int suma = 0;
		for(int score: scores) {
			suma = suma+score;
		}
		return Optional.of((double)suma/scores.length);
		
	}

}

