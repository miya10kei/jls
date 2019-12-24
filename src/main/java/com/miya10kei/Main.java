package com.miya10kei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.time.Instant;
import java.time.ZoneId;
import java.util.function.Consumer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class Main {

  public static void main(String... args) {

    try {
      CommandLine command = CommandParser.parser(args);

      var targetPath =
          ArrayUtils.isEmpty(command.getArgs()) ? Paths.get(command.getArgs()[0]) : Paths.get(".");

      var paths = Files.walk(targetPath, 1);
      var pipeline =
          paths
              .filter(p -> !p.equals(targetPath))
              .map(
                  p -> {
                    try {
                      var basicView = Files.getFileAttributeView(p, BasicFileAttributeView.class);
                      var basicAttr = basicView.readAttributes();
                      var posixView = Files.getFileAttributeView(p, PosixFileAttributeView.class);
                      var posixAttr = posixView.readAttributes();
                      return new Resource(
                          p.toFile().getName(),
                          basicAttr.size(),
                          posixAttr.owner().getName(),
                          posixAttr.group().getName(),
                          Instant.ofEpochMilli(basicAttr.lastModifiedTime().toMillis())
                              .atZone(ZoneId.systemDefault())
                              .toLocalDateTime(),
                          null,
                          null);
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                    return null;
                  });

      if (command.hasOption("l")) {
        pipeline.forEach(r -> PrintWithList.accept(r));
          pipeline.forEach(ListConsumer::accept);
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
      System.out.println("No such file or directory");
      System.exit(1);
    }
  }

  private static final Consumer<Resource> PrintWithList =
      resource ->
          System.out.println(
              String.format(
                  "%s\t%s:%s\t%d\t%s\t%s",
                  formatSymbolAndPermission(),
                  resource.getOwner(),
                  resource.getGroup(),
                  resource.getSize(),
                  resource.getTimestamp(),
                  resource.getName()));

  private static String formatSymbolAndPermission() {
    return null;
  }
}
