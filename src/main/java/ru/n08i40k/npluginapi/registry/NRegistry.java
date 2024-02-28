package ru.n08i40k.npluginapi.registry;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.resource.INResourceKeyHolder;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("UnusedReturnValue")
@Getter
public abstract class NRegistry<K extends INResourceKeyHolder> {
    protected final Map<NResourceKey, K> data;

    private String getChildExtendClassName() {
        String klassName = getClass().getGenericSuperclass().getTypeName();

        Pattern pattern = Pattern.compile("[a-zA-Z0-9.]+<[a-zA-Z0-9.]+\\.([a-zA-Z]+)(<[a-zA-Z0-9?\\-_]+?>)?>");
        Matcher matcher = pattern.matcher(klassName);

        Preconditions.checkState(matcher.find(),
                "Unknown generic superclass type name! %s", klassName);

        return matcher.group(1);
    }

    public NRegistry() {
        data = new HashMap<>();

        try {
            Method getMethod = getClass().getDeclaredMethod("get" + getChildExtendClassName(), NResourceKey.class);
            Preconditions.checkState(Modifier.isPublic(getMethod.getModifiers()),
                    "Method get%s is not public!", getChildExtendClassName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contains(@NonNull NResourceKey nResourceKey) {
        return data.containsKey(nResourceKey);
    }

    public K add(@NonNull K element) {
        Preconditions.checkState(!contains(element.getNResourceKey()),
                "%s with getId %s is exists!", getChildExtendClassName(), element.getNResourceKey());

        data.put(element.getNResourceKey(), element);

        NPluginApi.getInstance().getSLF4JLogger().info("Added new {}: {}!", getChildExtendClassName(), element.getNResourceKey());

        return element;
    }

    public K remove(@NonNull NResourceKey nResourceKey) {
        Preconditions.checkState(contains(nResourceKey),
                "Cannot find %s with getId %s", getChildExtendClassName(), nResourceKey);

        return data.remove(nResourceKey);
    }

    public List<K> removeAll(@NonNull NPlugin nPlugin) {
        List<K> elements = new ArrayList<>();

        Map<NResourceKey, K> dataCopy = new HashMap<>(data);

        dataCopy.forEach(((nResourceKey, k) -> {
            if (nResourceKey.getNPlugin().equals(nPlugin)) {
                elements.add(remove(nResourceKey));
            }
        }));

        return elements;
    }

    @NonNull
    protected K getElement(@NonNull NResourceKey nResourceKey) {
        Preconditions.checkState(contains(nResourceKey),
                "Cannot find %s with id %s", getChildExtendClassName(), nResourceKey);

        return data.get(nResourceKey);
    }
}
