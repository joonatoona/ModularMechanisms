/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemPressHead extends BaseMMItem {
    public ItemPressHead() {
        super("press_head");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        Item base = getBaseItem(stack);
        if (base == Items.AIR) {
            return I18n.translateToLocal("text.item.invalid");
        }
        return new ItemStack(base).getDisplayName() + " " + super.getItemStackDisplayName(stack);
    }

    private Item getBaseItem(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            return Items.AIR;
        }
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(stack.getTagCompound().getString("base")));
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }
    }
}
