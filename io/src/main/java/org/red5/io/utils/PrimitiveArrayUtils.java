/*
 * RED5 Open Source Media Server - https://github.com/Red5/ Copyright 2006-2023 by respective authors (see below). All rights reserved. Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless
 * required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package org.red5.io.utils;

import java.util.Collection;

/**
 * Primitive array allocation and conversion helpers extracted from {@link ArrayUtils}.
 */
public final class PrimitiveArrayUtils {

    private PrimitiveArrayUtils() {
    }

    public static Object createPrimitiveArray(Class<?> type, int count) {
        if (type == byte.class) {
            return new byte[count];
        }
        if (type == short.class) {
            return new short[count];
        }
        if (type == int.class) {
            return new int[count];
        }
        if (type == long.class) {
            return new long[count];
        }
        if (type == float.class) {
            return new float[count];
        }
        if (type == double.class) {
            return new double[count];
        }
        if (type == boolean.class) {
            return new boolean[count];
        }
        if (type == char.class) {
            return new char[count];
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes" })
    public static Object fillPrimitiveArray(Class<?> type, Collection collection) {
        if (byte.class.isAssignableFrom(type)) {
            return toByteArray(new byte[collection.size()], collection);
        } else if (short.class.isAssignableFrom(type)) {
            return toShortArray(new short[collection.size()], collection);
        } else if (int.class.isAssignableFrom(type)) {
            return toIntegerArray(new int[collection.size()], collection);
        } else if (long.class.isAssignableFrom(type)) {
            return toLongArray(new long[collection.size()], collection);
        } else if (float.class.isAssignableFrom(type)) {
            return toFloatArray(new float[collection.size()], collection);
        } else if (double.class.isAssignableFrom(type)) {
            return toDoubleArray(new double[collection.size()], collection);
        } else if (boolean.class.isAssignableFrom(type)) {
            return toBooleanArray(new boolean[collection.size()], collection);
        } else if (char.class.isAssignableFrom(type)) {
            return toCharacterArray(new char[collection.size()], collection);
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes" })
    public static Object fillPrimitiveArray(Class<?> type, Object array, Collection collection) {
        if (byte.class.isAssignableFrom(type)) {
            return toByteArray(array, collection);
        } else if (short.class.isAssignableFrom(type)) {
            return toShortArray(array, collection);
        } else if (int.class.isAssignableFrom(type)) {
            return toIntegerArray(array, collection);
        } else if (long.class.isAssignableFrom(type)) {
            return toLongArray(array, collection);
        } else if (float.class.isAssignableFrom(type)) {
            return toFloatArray(array, collection);
        } else if (double.class.isAssignableFrom(type)) {
            return toDoubleArray(array, collection);
        } else if (boolean.class.isAssignableFrom(type)) {
            return toBooleanArray(array, collection);
        } else if (char.class.isAssignableFrom(type)) {
            return toCharacterArray(array, collection);
        }
        return null;
    }

    public static Class<?> toWrapperType(Class<?> nested) {
        if (nested == byte.class) {
            return Byte.class;
        } else if (nested == short.class) {
            return Short.class;
        } else if (nested == int.class) {
            return Integer.class;
        } else if (nested == long.class) {
            return Long.class;
        } else if (nested == float.class) {
            return Float.class;
        } else if (nested == double.class) {
            return Double.class;
        } else if (nested == boolean.class) {
            return Boolean.class;
        } else if (nested == char.class) {
            return Character.class;
        }
        return nested;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toByteArray(Object array, Collection collection) {
        byte[] ba = (byte[]) array;
        int i = 0;
        for (Object o : collection) {
            byte b = ((Byte) o).byteValue();
            ba[i++] = b;
        }
        return ba;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toShortArray(Object array, Collection collection) {
        short[] sa = (short[]) array;
        int i = 0;
        for (Object o : collection) {
            short s = ((Short) o).shortValue();
            sa[i++] = s;
        }
        return sa;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toIntegerArray(Object array, Collection collection) {
        int[] ia = (int[]) array;
        int i = 0;
        for (Object o : collection) {
            int j = ((Integer) o).intValue();
            ia[i++] = j;
        }
        return ia;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toLongArray(Object array, Collection collection) {
        long[] la = (long[]) array;
        int i = 0;
        for (Object o : collection) {
            long l = ((Long) o).longValue();
            la[i++] = l;
        }
        return la;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toFloatArray(Object array, Collection collection) {
        float[] fa = (float[]) array;
        int i = 0;
        for (Object o : collection) {
            float f = ((Float) o).floatValue();
            fa[i++] = f;
        }
        return fa;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toDoubleArray(Object array, Collection collection) {
        double[] da = (double[]) array;
        int i = 0;
        for (Object o : collection) {
            double d;
            if (o instanceof Integer) {
                d = (Integer) o;
            } else {
                d = ((Double) o).doubleValue();
            }
            da[i++] = d;
        }
        return da;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toBooleanArray(Object array, Collection collection) {
        boolean[] ba = (boolean[]) array;
        int i = 0;
        for (Object o : collection) {
            boolean b = ((Boolean) o).booleanValue();
            ba[i++] = b;
        }
        return ba;
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toCharacterArray(Object array, Collection collection) {
        char[] ca = (char[]) array;
        int i = 0;
        for (Object o : collection) {
            char c = ((Character) o).charValue();
            ca[i++] = c;
        }
        return ca;
    }
}
