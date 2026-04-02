/*
 * RED5 Open Source Media Server - https://github.com/Red5/ Copyright 2006-2023 by respective authors (see below). All rights reserved. Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless
 * required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package org.red5.io.utils;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * <p>ArrayUtils class.</p>
 *
 * @author mondain
 */
public final class ArrayUtils {

    /**
     * <p>getArray.</p>
     *
     * @param type a {@link java.lang.Class} object
     * @param count a int
     * @return a {@link java.lang.Object} object
     */
    public static Object getArray(Class<?> type, int count) {
        Object primitiveArray = PrimitiveArrayUtils.createPrimitiveArray(type, count);
        if (primitiveArray != null) {
            return primitiveArray;
        } else if (type != null) {
            return Array.newInstance(type, count);
        } else {
            return new Object[count];
        }
    }

    /**
     * <p>fillArray.</p>
     *
     * @param type a {@link java.lang.Class} object
     * @param collection a {@link java.util.Collection} object
     * @return a {@link java.lang.Object} object
     */
    @SuppressWarnings({ "rawtypes" })
    public static Object fillArray(Class<?> type, Collection collection) {
        Object primitiveArray = PrimitiveArrayUtils.fillPrimitiveArray(type, collection);
        if (primitiveArray == null) {
            return toObjectArray(type, collection);
        }
        return primitiveArray;
    }

    /**
     * <p>fillArray.</p>
     *
     * @param type a {@link java.lang.Class} object
     * @param array a {@link java.lang.Object} object
     * @param collection a {@link java.util.Collection} object
     * @return a {@link java.lang.Object} object
     */
    @SuppressWarnings({ "rawtypes" })
    public static Object fillArray(Class<?> type, Object array, Collection collection) {
        Object primitiveArray = PrimitiveArrayUtils.fillPrimitiveArray(type, array, collection);
        if (primitiveArray == null) {
            return toObjectArray(array, collection);
        }
        return primitiveArray;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Object toObjectArray(Class<?> type, Collection collection) {
        return collection.toArray((Object[]) Array.newInstance(type, collection.size()));
    }

    @SuppressWarnings({ "rawtypes" })
    private static Object toObjectArray(Object array, Collection collection) {
        Object[] oa = (Object[]) array;
        int i = 0;
        for (Object o : collection) {
            oa[i++] = o;
        }
        return oa;
    }

    /**
     * <p>getGenericType.</p>
     *
     * @param nested a {@link java.lang.Class} object
     * @return a {@link java.lang.Class} object
     */
    public static Class<?> getGenericType(Class<?> nested) {
        return PrimitiveArrayUtils.toWrapperType(nested);
    }
}
