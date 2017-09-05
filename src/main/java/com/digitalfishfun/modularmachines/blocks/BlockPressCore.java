package com.digitalfishfun.modularmachines.blocks;

import com.digitalfishfun.modularmachines.ModularMachines;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

// TODO: Move common logic elsewhere
public class BlockPressCore extends Block {

    public BlockPressCore() {
        super(Material.IRON);
        this.setUnlocalizedName("press_core");
        this.setRegistryName(ModularMachines.MODID, "press_core");
        this.setHardness(2.0f);
        this.setCreativeTab(CreativeTabs.MISC);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    public void registerItemModel(Item itemBlock) {
        ModularMachines.proxy.registerItemRenderer(itemBlock, 0, "press_core");
    }
}
