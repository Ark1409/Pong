package com.pong.utils;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pong.pong.Pong;

public final class Logger {

	private Logger() {
	}

	public static void log(String s) {
		logInfo(s);
	}

	public static void log(Object o) {
		logInfo(o.toString());
	}

	public static void logDebug(Object o) {
		if (!Pong.getPong().isDebug())
			return;
		System.out.println(
				new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.debugPrefix + " " + o.toString());
	}

	public static void logDebug(String s) {
		if (!Pong.getPong().isDebug())
			return;
		System.out.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.debugPrefix + " " + s);
	}

	public static void logDebug(String s, PrintStream p) {
		if (!Pong.getPong().isDebug())
			return;
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.debugPrefix + " " + s);
	}

	public static void logDebug(Object o, PrintStream p) {
		if (!Pong.getPong().isDebug())
			return;
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.debugPrefix + " " + o.toString());
	}

	public static void logInfo(String s) {
		System.out.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.infoPrefix + " " + s);
	}

	public static void logInfo(Object o) {
		System.out.println(
				new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.infoPrefix + " " + o.toString());
	}

	public static void logInfo(Object o, PrintStream p) {
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.infoPrefix + " " + o.toString());
	}

	public static void logWarning(Object o, PrintStream p) {
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.warnPrefix + " " + o.toString());
	}

	public static void logWarning(Object o) {
		System.out.println(
				new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.warnPrefix + " " + o.toString());
	}

//	/**
//	 * @deprecated If you 'logError()' in this method, it wont do the same thing as
//	 *             System.err, it will just take your, PrintStream and normally
//	 *             print it.
//	 * 
//	 */
	@Deprecated
	public static void logError(Object o, PrintStream p) {
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.errPrefix + " " + o.toString());
	}

	public static void logError(Object o) {
		System.out.println(
				new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.errPrefix + " " + o.toString());
	}

	public static void logWarning(String s) {
		System.out.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.warnPrefix + " " + s);
	}

	public static void logError(String s) {
		System.err.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.errPrefix + " " + s);
	}

	public static void log(String s, PrintStream p) {
		logInfo(s, p);
	}

	public static void logInfo(String s, PrintStream p) {
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.infoPrefix + " " + s);
	}

	public static void logWarning(String s, PrintStream p) {
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.warnPrefix + " " + s);
	}

	/**
	 * @deprecated If you 'logError()' in this method, it wont do the same thing as
	 *             System.err, it will just take your, PrintStream and normally
	 *             print it.
	 * 
	 */
	@Deprecated
	public static void logError(String s, PrintStream p) {
		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + Pong.errPrefix + " " + s);
	}
	
//	public static void logWarningDebug(String s, PrintStream p) {
//		p.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + " " + Pong.warnPrefix + " " + s);
//	}

}
