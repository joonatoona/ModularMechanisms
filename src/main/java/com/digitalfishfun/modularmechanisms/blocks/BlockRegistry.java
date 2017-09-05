package com.digitalfishfun.modularmechanisms.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {

    public static BlockPressCore pressCore = new BlockPressCore();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                pressCore
        );
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
                pressCore.createItemBlock()
        );
    }

    public static void registerModels() {
        pressCore.registerItemModel(Item.getItemFromBlock(pressCore));
    }

}
