/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.recipes;

import com.digitalfishfun.modularmechanisms.items.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class RecipePressHead implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        List<Item> validParts = Arrays.asList(
                Items.IRON_INGOT, Items.GOLD_INGOT, Items.DIAMOND
        );

        Item chosenItem = inv.getStackInSlot(0).getItem();
        return validParts.contains(chosenItem);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        Item materialUsed = inv.getStackInSlot(0).getItem();
        ItemStack stack = new ItemStack(ItemRegistry.pressHead);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setString("base", materialUsed.getRegistryName().toString());

        return stack;
    }

    @Override
    public boolean canFit(int width, int height) {
        return (height > 2 && width > 2);
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(ItemRegistry.pressHead);
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation("modularmechanisms:test");
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return null;
    }
}
