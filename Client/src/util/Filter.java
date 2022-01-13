package util;

import java.io.OutputStream;
import java.io.PrintStream;

import main.Main;

public class Filter {

	public static void filter() {
		if(Main.FILTER_OUTPUT) {
			System.setErr(new Interceptor(System.err));
			System.setOut(new Interceptor(System.out));
		}
	}
}

class Interceptor extends PrintStream {
	public Interceptor(OutputStream out) {super(out, true);}
	@Override
	public void print(String s) {}
	@Override
	public void println(String s) {}
}
