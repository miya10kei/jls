package com.miya10kei;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandParser {

  private static final Options OPTIONS;

  static {
    var options = new Options();
    options.addOption(Option.builder("a").required(false).hasArg(false).build());
    options.addOption(Option.builder("l").required(false).hasArg(false).build());
    options.addOption(Option.builder("1").required(false).hasArg(false).build());
    OPTIONS = options;
  }

  public static CommandLine parser(final String... args) throws ParseException {
    var parser = new DefaultParser();
    return parser.parse(OPTIONS, args);
  }
}
