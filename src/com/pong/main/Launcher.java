package com.pong.main;

import java.awt.Font;

import com.pong.graphics.Window;
import com.pong.pong.Pong;
import com.pong.utils.PongUtils;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.PointerByReference;

public class Launcher {
	public static final Font ipBoxFont = PongUtils.loadFont(Pong.class.getResourceAsStream("/fonts/Boo City.ttf"),
			20.0f);

	public Launcher(boolean debug, boolean plugins) {
		Pong p = new Pong(new Window("Pong", Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT, ipBoxFont), debug, plugins);
		p.start();
	}

	public static void main(String[] args) {
		setCurrentProcessExplicitAppUserModelID(Pong.class.getCanonicalName());
		System.out.println("Name: " + getCurrentProcessExplicitAppUserModelID());
		if (args.length <= 0) {
			new Launcher(false, false);
		} else {
			boolean debug = false;
			boolean plugins = false;
			for (int i = 0; i < args.length; i++) {
				String s = args[i];
				if (s.equalsIgnoreCase("-plugins")) {
					plugins = true;
				}
				if (s.equalsIgnoreCase("-debug")) {
					debug = true;
				}

			}
			new Launcher(debug, plugins);
		}

	}

	public static String getCurrentProcessExplicitAppUserModelID() {
		final PointerByReference r = new PointerByReference();

		if (GetCurrentProcessExplicitAppUserModelID(r).longValue() == 0) {
			final Pointer p = r.getValue();
			return p.getString(0);// here we leak native memory by laziness
		}
		return "N/A";
	}

	public static void setCurrentProcessExplicitAppUserModelID(final String appID) {
		if (SetCurrentProcessExplicitAppUserModelID(new WString(appID)).longValue() != 0)
			throw new RuntimeException("unable to set current process explicit AppUserModelID to: " + appID);
	}

	private static native NativeLong GetCurrentProcessExplicitAppUserModelID(PointerByReference appID);

	private static native NativeLong SetCurrentProcessExplicitAppUserModelID(WString appID);

	static {
		Native.register("shell32");
	}

}
