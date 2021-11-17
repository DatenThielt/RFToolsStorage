package mcjty.rftoolsstorage.modules.scanner.client;

import mcjty.lib.McJtyLib;
import mcjty.lib.typed.Key;
import mcjty.lib.typed.Type;
import mcjty.rftoolsstorage.RFToolsStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.List;

public class ClientCommandHandler {

    public static final String CMD_RETURN_SCANNER_CONTENTS = "returnScannerContents";
    public static final Key<List<ItemStack>> PARAM_STACKS = new Key<>("stacks", Type.ITEMSTACK_LIST);
    public static final Key<List<ItemStack>> PARAM_CRAFTABLE = new Key<>("craftable", Type.ITEMSTACK_LIST);

    public static final String CMD_RETURN_SCANNER_SEARCH = "returnScannerSearch";
    public static final Key<List<BlockPos>> PARAM_INVENTORIES = new Key<>("inventories", Type.POS_LIST);

    public static final Key<String> PARAM_NAME = new Key<>("name", Type.STRING);
    public static final Key<Integer> PARAM_COUNTER = new Key<>("counter", Type.INTEGER);

    public static final String CMD_POSITION_TO_CLIENT = "positionToClient";
    public static final Key<BlockPos> PARAM_POS = new Key<>("pos", Type.BLOCKPOS);
    public static final Key<BlockPos> PARAM_SCAN = new Key<>("scan", Type.BLOCKPOS);

    public static void registerCommands() {
        McJtyLib.registerClientCommand(RFToolsStorage.MODID, CMD_RETURN_SCANNER_CONTENTS, (player, arguments) -> {
            GuiStorageScanner.fromServer_inventory = arguments.get(PARAM_STACKS);
            GuiStorageScanner.fromServer_craftable = arguments.get(PARAM_CRAFTABLE);
            return true;
        });
        McJtyLib.registerClientCommand(RFToolsStorage.MODID, CMD_RETURN_SCANNER_SEARCH, (player, arguments) -> {
            GuiStorageScanner.fromServer_foundInventories = new HashSet<>(arguments.get(PARAM_INVENTORIES));
            return true;
        });
    }
}
