/*
 * This file is part of lamp, licensed under the MIT License.
 *
 *  Copyright (c) Revxrsal <reflxction.github@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package revxrsal.commands.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

final class CommandLookup<T> extends AbstractMap<CommandPath, T> {

    private final Map<CommandPath, T> map = new HashMap<>();

    @Override public int size() {return map.size();}

    @Override public boolean isEmpty() {return map.isEmpty();}

    @Override public boolean containsKey(Object key) {return map.containsKey(key);}

    @Override public boolean containsValue(Object value) {return map.containsValue(value);}

    @Override public T get(Object key) {
        T value = map.get(key);
        if (value != null)
            return value;
        for (Entry<CommandPath, T> entry : map.entrySet()) {
            if (entry.getKey().equals(key)) // it's important to use the `equals` of CommandPath
                return entry.getValue();
        }
        return null;
    }

    @Override public T put(CommandPath key, T value) {return map.put(key, value);}

    @Override public T remove(Object key) {return map.remove(key);}

    @Override public void putAll(@NotNull Map<? extends CommandPath, ? extends T> m) {map.putAll(m);}

    @Override public void clear() {map.clear();}

    @NotNull @Override public Set<CommandPath> keySet() {return map.keySet();}

    @NotNull @Override public Collection<T> values() {return map.values();}

    @NotNull @Override public Set<Entry<CommandPath, T>> entrySet() {return map.entrySet();}

    @Override public T getOrDefault(Object key, T defaultValue) {return map.getOrDefault(key, defaultValue);}

    @Override public void forEach(BiConsumer<? super CommandPath, ? super T> action) {map.forEach(action);}

    @Override public void replaceAll(BiFunction<? super CommandPath, ? super T, ? extends T> function) {map.replaceAll(function);}

    @Nullable @Override public T putIfAbsent(CommandPath key, T value) {return map.putIfAbsent(key, value);}

    @Override public boolean remove(Object key, Object value) {return map.remove(key, value);}

    @Override public boolean replace(CommandPath key, T oldValue, T newValue) {return map.replace(key, oldValue, newValue);}

    @Nullable @Override public T replace(CommandPath key, T value) {return map.replace(key, value);}

    @Override public T computeIfAbsent(CommandPath key, @NotNull Function<? super CommandPath, ? extends T> mappingFunction) {return map.computeIfAbsent(key, mappingFunction);}

    @Override public T computeIfPresent(CommandPath key, @NotNull BiFunction<? super CommandPath, ? super T, ? extends T> remappingFunction) {return map.computeIfPresent(key, remappingFunction);}

    @Override public T compute(CommandPath key, @NotNull BiFunction<? super CommandPath, ? super T, ? extends T> remappingFunction) {return map.compute(key, remappingFunction);}

    @Override public T merge(CommandPath key, @NotNull T value, @NotNull BiFunction<? super T, ? super T, ? extends T> remappingFunction) {return map.merge(key, value, remappingFunction);}
}
