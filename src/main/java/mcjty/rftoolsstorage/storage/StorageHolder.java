package mcjty.rftoolsstorage.storage;

import mcjty.lib.worlddata.AbstractWorldData;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageHolder extends AbstractWorldData<StorageHolder> {

    public static final String NAME = "RFToolsStorageHolder";

    private final Map<UUID, StorageEntry> storageEntryMap = new HashMap<>();

    private StorageHolder() {
        super(NAME);
    }

    public static StorageHolder get(World world) {
        return getData(world, StorageHolder::new, NAME);
    }


    public StorageEntry getOrCreateStorageEntry(UUID uuid, int size, String createdBy) {
        if (!storageEntryMap.containsKey(uuid)) {
            StorageEntry entry = new StorageEntry(size, uuid, createdBy);
            storageEntryMap.put(uuid, entry);
            save();
        } else {
            // Check if the size still matches
            StorageEntry entry = storageEntryMap.get(uuid);
            if (size != entry.getStacks().size()) {
                entry.resize(size, createdBy);
            }
        }
        return storageEntryMap.get(uuid);
    }

    public StorageEntry getStorageEntry(UUID uuid) {
        return storageEntryMap.get(uuid);
    }

    public Collection<StorageEntry> getStorages() {
        return storageEntryMap.values();
    }

    @Override
    public void load(CompoundNBT nbt) {
        ListNBT storages = nbt.getList("Storages", Constants.NBT.TAG_COMPOUND);
        for (INBT storage : storages) {
            StorageEntry entry = new StorageEntry((CompoundNBT) storage);
            storageEntryMap.put(entry.getUuid(), entry);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        ListNBT storages = new ListNBT();
        for (Map.Entry<UUID, StorageEntry> entry : storageEntryMap.entrySet()) {
            storages.add(entry.getValue().write());
        }
        nbt.put("Storages", storages);
        return nbt;
    }
}
