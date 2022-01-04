package mcjty.rftoolsstorage.modules.scanner.tools;

import net.minecraft.world.item.Item;
import net.minecraft.core.BlockPos;

public class CachedItemKey {
    private final BlockPos pos;
    private final Item item;
    private final int meta;     // @todo 1.14 meta

    public CachedItemKey(BlockPos pos, Item item, int meta) {
        this.item = item;
        this.pos = pos;
        this.meta = meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CachedItemKey that = (CachedItemKey) o;

        if (meta != that.meta) {
            return false;
        }
        if (!pos.equals(that.pos)) {
            return false;
        }
        if (!item.equals(that.item)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = pos.hashCode();
        result = 31 * result + item.hashCode();
        result = 31 * result + meta;
        return result;
    }
}
