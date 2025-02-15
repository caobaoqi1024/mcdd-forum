package dev.mcdd.backend.env.banner;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class SelfBanner implements Banner {

	private static final String BANNER = """
		  .   ____          _            __ _ _
		 /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\
		( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\
		 \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )
		  '  |____| .__|_| |_|_| |_\\__, | / / / /
		 =========|_|==============|___/=/_/_/_/
		""";
	public static final String WORD = " [shi jie ren min da tuan jie wan sui] ";

	private static final String SPRING_BOOT = " :: Spring Boot Ikun:: ";

	private static final int STRAP_LINE_SIZE = 42;

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
		printStream.println();
		printStream.println(BANNER);
		String appName = environment.getProperty("spring.application.name");
		appName = appName == null ? "spring boot app" : appName;
		String port = environment.getProperty("server.port");
		port = port == null ? "8080" : port;
		String version = String.format(" (v%s)", SpringBootVersion.getVersion());
		String padding = " ".repeat(Math.max(0, STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length())));
		printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding,
			AnsiStyle.FAINT, version));
		printStream.println();
		printStream.println(AnsiOutput.toString(AnsiColor.RED, AnsiStyle.BOLD, WORD + "\n\n" + appName + "`s port is " + port));
		printStream.println();

	}

}
