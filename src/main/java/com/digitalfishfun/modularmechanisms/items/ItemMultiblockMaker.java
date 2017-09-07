/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import com.digitalfishfun.modularmechanisms.blocks.multiblock.BlockPressComponent;
import com.digitalfishfun.modularmechanisms.blocks.multiblock.TileEntityMultiblock;
import com.digitalfishfun.modularmechanisms.multiblocks.MultiblockChecker;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class ItemMultiblockMaker extends BaseMMItem {

    public ItemMultiblockMaker() {
        super("multiblock_maker");
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if (world.getBlockState(pos).getBlock().equals(BlockRegistry.pressComponent) && world.isRemote) {
            TileEntityMultiblock mb = BlockRegistry.pressComponent.getTileEntity(world, pos);
            player.sendMessage(new TextComponentString(String.format("%s BLOCK: %s INDEX: %d", world.isRemote ? "[C]": "[S]", mb.getBase(), mb.getIndex())));
        }
        return EnumActionResult.FAIL;
    }
}
