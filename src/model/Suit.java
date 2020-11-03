//package model;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//public enum Suit {
//  try {
//    Field field = this.getClass().getDeclaredField(name);
//    Method valueOf = field.getType().getMethod("valueOf", String.class);
//    Object value = valueOf.invoke(null, param);
//    field.set(test, value);
//  } catch ( ReflectiveOperationException e) {
//    // handle error here
//  }
//}
