/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {

    public static ItemMultiblockMaker multiblockMaker = new ItemMultiblockMaker();
    public static ItemPressHead pressHead = new ItemPressHead();

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                multiblockMaker,
                pressHead
        );
    }

    public static void registerModels() {
        multiblockMaker.registerItemModel();
        pressHead.registerItemModel();
    }

}
