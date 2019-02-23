package com.cyeste.games.mastermind.domain.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Syntactic sugar Validation utility
 * @author Christian Yeste Vidal
 *
 */
public class Validations {

	private static final Clause TRUE_CLAUSE = new Clause(true);
	private static final Clause FALSE_CLAUSE = new Clause(false);

	private Validations() {
	}

	/**
	 * Validations status resolver
	 * @author Christian Yeste Vidal
	 *
	 */
	public static class Clause {
		
		private final boolean conditionFailed;

		public Clause(boolean conditionFailed) {
			this.conditionFailed = conditionFailed;
		}

		/**
		 * Error genérico informando de la invalidez de un argumento
		 * @param message
		 */
		public void throwIllegalArgumentException(String message) {
			if (conditionFailed) {
				throw new IllegalArgumentException(message);
			}
		}
		
		/**
		 * Error informando que se esperaba un valor de tipo {@code clazz} pero se obtuvo un null
		 * @param clazz
		 */
		public void throwNotNullableException(Class<?> clazz) {
			throwIllegalArgumentException("NotNull."+clazz.getSimpleName());
		}	
		
		/**
		 * Error genérico informando de la invalidez de un argumento
		 * @param message
		 */
		public void throwEmptyArgumentException(String fieldName) {
			throwIllegalArgumentException("NotEmpty."+fieldName);
		}		
	}

	
	/**
	 * Check whether <code>condition</code> is True. If it's true, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause when(boolean condition) {
		return condition ? TRUE_CLAUSE : FALSE_CLAUSE;
	}
	

	/**
	 * Check whether <code>condition</code> is True. If it's true, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause whenNot(boolean condition) {
		return (!condition) ? TRUE_CLAUSE : FALSE_CLAUSE;
	}

	/**
	 * Check whether <code>object</code> is null. If it's null, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause whenNull(Object object) {
		return when(object == null);
	}

	/**
	 * Check whether <code>str</code> is null or empty. If it's, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause whenEmpty(String str) {
		return when(str == null || str.trim().length() == 0);
	}
	
	/**
	 * Check whether <code>str</code> is null or empty. If it's, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause whenEmpty(Collection<?> collection) {
		return when(collection == null || collection.isEmpty());
	}
	
	/**
	 * Check whether <code>map</code> is null or empty. If it's, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause whenEmpty(Map<?, ?> map) {
		return when(map == null || map.isEmpty());
	}

	/**
	 * Check whether <code>arrays</code> is null or empty. If it's, <code>Clause</code> will throw the selected RuntimeException 
	 * @see Clause
	 * @param condition
	 * @return Clause
	 */
	public static Clause whenEmpty(Object[] array) {
		return when(array == null || array.length == 0);
	}
	
	


}

