/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import com.digitalfishfun.modularmechanisms.ModularMechanisms;
import net.minecraft.item.Item;

public class BaseMMItem extends Item {
    public String name;

    public BaseMMItem(String name) {
        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(ModularMechanisms.MODID, name);
        setCreativeTab(ModularMechanisms.creativeTab);
    }

    public void registerItemModel() {
        ModularMechanisms.proxy.registerItemRenderer(this, 0, name);
    }
}
