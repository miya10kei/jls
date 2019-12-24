package com.miya10kei;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class Resource {

    private final String name;

    private final long size;

    private final String owner;

    private final String group;

    private final LocalDateTime timestamp;

    private final ResourcePermission permission;

    private final ResourceKind kind;

}
