/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks;

import com.digitalfishfun.modularmechanisms.ModularMechanisms;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BaseMMBlock extends Block {
    public String name;

    public BaseMMBlock(String name, Material material) {
        super(material);

        this.name = name;

        this.setUnlocalizedName(name);
        this.setRegistryName(ModularMechanisms.MODID, name);
        this.setHardness(2.0f);
        this.setCreativeTab(ModularMechanisms.creativeTab);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    public void registerItemModel(Item itemBlock) {
        ModularMechanisms.proxy.registerItemRenderer(itemBlock, 0, name);
    }
}
