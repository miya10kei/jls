package com.miya10kei;

import java.awt.event.PaintEvent;
import lombok.Value;

@Value
public class ResourcePermission {

  private final Permission owner;
  private final Permission group;
  private final PaintEvent other;

  @Value
  class Permission {
    private final boolean isReadable;
    private final boolean isWritable;
    private final boolean isExecutable;
  }
}
