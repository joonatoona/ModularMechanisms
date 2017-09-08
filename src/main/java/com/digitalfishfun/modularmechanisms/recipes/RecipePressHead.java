/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.recipes;

import com.digitalfishfun.modularmechanisms.ModularMechanisms;
import com.digitalfishfun.modularmechanisms.items.ItemRegistry;
import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.init.Blocks;
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

        Item chosenItem = inv.getStackInSlot(8).getItem();

        if (!ModularMechanisms.config.headMaterials.contains(chosenItem)) return false;

        List<Item> recipe = Arrays.asList(
                Items.AIR, Item.getItemFromBlock(Blocks.COBBLESTONE_WALL), Items.AIR,
                Items.IRON_INGOT, Item.getItemFromBlock(Blocks.IRON_BARS), Items.IRON_INGOT,
                chosenItem, chosenItem, chosenItem
        );

        for (int i = 0; i < 9; i++) {
            if (inv.getStackInSlot(i).getItem() != recipe.get(i)) return false;
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        Item materialUsed = inv.getStackInSlot(8).getItem();
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
