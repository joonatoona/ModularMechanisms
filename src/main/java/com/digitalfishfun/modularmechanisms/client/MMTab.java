package com.digitalfishfun.modularmechanisms.client;

import com.digitalfishfun.modularmechanisms.ModularMechanisms;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MMTab extends CreativeTabs {

    public MMTab() {
        super(ModularMechanisms.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        // TODO: Different creative tab icon
        return new ItemStack(Blocks.ANVIL);
    }
}
