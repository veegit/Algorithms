package com.vee.algorithms.others;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Test;

/**
 * 
 * function cons(x, y) {
  return function(w) { return w(x, y) };
};

function car(z) {
  return z(function(x, y) { return x });
};

function cdr(z) {
  return z(function(x, y) { return y });
};

var list = cons(1, cons(2, null));
 *
 */
public class CarCdr {

	public Function<BiFunction<Object, Object, Object>, Object> cons(Object a, Object b) {
		Function<BiFunction<Object, Object, Object>, Object> f = (x) -> { return x.apply(a, b); };
		return f;
	}
	
	public Object car(Function f) {
		BiFunction<Object, Object, Object> bi = (x, y) -> { return x; };
		return f.apply(bi);
	}
	
	public Object cdr(Function f) {
		BiFunction<Object, Object, Object> bi = (x, y) -> { return y; };
		return f.apply(bi);
	}
	
	@Test
	public void test1() {
		Function list = cons(1,cons(2,3));
		System.out.println(car(list));
		System.out.println(cdr(list));
	}
}
