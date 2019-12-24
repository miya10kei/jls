package com.miya10kei;

import java.lang.reflect.Array;

public class ArrayUtils {
  public static boolean isEmpty(final Object[] array) {
    return array == null || Array.getLength(array) == 0 ? false : true;
  }
}
