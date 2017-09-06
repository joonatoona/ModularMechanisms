/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks;

import com.digitalfishfun.modularmechanisms.blocks.multiblock.BlockMultiblock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {

    public static BlockPressCore pressCore = new BlockPressCore();
    public static BlockMultiblock multiBlock = new BlockMultiblock();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                pressCore,
                multiBlock
        );

        GameRegistry.registerTileEntity(multiBlock.getTileEntityClass(), multiBlock.getRegistryName().toString());
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
