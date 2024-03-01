package ru.n08i40k.npluginapi.util;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class PermissionBuilder {
    @Nullable
    private final PermissionBuilder parent;

    @Nullable
    private final String currentPermission;

    private PermissionBuilder(@Nullable PermissionBuilder parent, @Nullable String currentPermission) {
        this.currentPermission = currentPermission;
        this.parent = parent;
    }

    public String get(@Nullable String part) {
        if (parent != null)
            return parent.get(part);

        return part != null ?
                currentPermission + "." + part :
                currentPermission;
    }

    public PermissionBuilder extend(String appendPermission) {
        return new PermissionBuilder(this, appendPermission);
    }

    public boolean has(CommandSender sender) {
        return has(sender, null);
    }

    public boolean has(CommandSender sender, @Nullable String append) {
        if (!(sender instanceof Player)) return true;
        if (sender.isOp()) return true;

        return sender.hasPermission(get(append));
    }

    public static PermissionBuilder of(@Nullable PermissionBuilder parent, @Nullable String part) {
        Preconditions.checkState(parent != null || part != null, "Parent and Part can't be null at the same time!");

        return new PermissionBuilder(parent, part);
    }

    public static PermissionBuilder of(@NonNull PermissionBuilder parent) {
        return of(parent, null);
    }

    public static PermissionBuilder of(@NonNull String part) {
        return of(null, part);
    }
}
