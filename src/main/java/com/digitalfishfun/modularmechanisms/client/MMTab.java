/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.client;

import com.digitalfishfun.modularmechanisms.ModularMechanisms;
import com.digitalfishfun.modularmechanisms.items.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MMTab extends CreativeTabs {

    public MMTab() {
        super(ModularMechanisms.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegistry.multiblockMaker);
    }
}
