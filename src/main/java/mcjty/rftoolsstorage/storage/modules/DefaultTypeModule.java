package mcjty.rftoolsstorage.storage.modules;

import mcjty.rftoolsstorage.storage.sorters.CountItemSorter;
import mcjty.rftoolsstorage.storage.sorters.ItemSorter;
import mcjty.rftoolsstorage.storage.sorters.NameItemSorter;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DefaultTypeModule implements TypeModule {
    private List<ItemSorter> sorters = null;

    @Override
    public List<ItemSorter> getSorters() {
        if (sorters == null) {
            sorters = new ArrayList<>();
            sorters.add(new NameItemSorter());
            sorters.add(new CountItemSorter());
        }
        return sorters;
    }

    @Override
    public String getLongLabel(ItemStack stack) {
        return stack.getHoverName().getString() /* was getFormattedText() */;
    }

    @Override
    public String getShortLabel(ItemStack stack) {
        return stack.getHoverName().getString() /* was getFormattedText() */;
    }
}
