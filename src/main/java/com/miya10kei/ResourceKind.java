package com.miya10kei;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResourceKind {
  FILE("-"),
  DIRECTORY("d"),
  SYMBOLIC_LINK("l"),
  CHARACTER_DEVICE("c"),
  BLOCK_DEVICE("b");

  private final String symbol;
}
