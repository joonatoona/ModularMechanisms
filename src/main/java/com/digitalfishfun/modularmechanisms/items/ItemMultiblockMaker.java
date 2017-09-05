/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.awt.*;

public class ItemMultiblockMaker extends BaseMMItem {

    public ItemMultiblockMaker() {
        super("multiblock_maker");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if (world.isRemote) {
            if (world.getBlockState(pos).getBlock().equals(BlockRegistry.pressCore)) {
                checkMultiblock(world, pos, player);

                player.swingArm(hand);
                return EnumActionResult.PASS;
            }
        }

        return EnumActionResult.FAIL;
    }

    private int checkMultiblock(World world, BlockPos pos, EntityPlayer player) {
        Block above = world.getBlockState(pos.up()).getBlock();
        Block below = world.getBlockState(pos.down()).getBlock();
        Block north = world.getBlockState(pos.north()).getBlock();
        Block south = world.getBlockState(pos.south()).getBlock();
        Block east = world.getBlockState(pos.east()).getBlock();

        if (!above.equals(Blocks.IRON_BLOCK)) {
            player.sendStatusMessage(generateError(pos.up(), Blocks.IRON_BLOCK), true);
            return 0;
        }

        return 0;
    }

    private ITextComponent generateError(BlockPos pos, Block expected) {
        // TODO: Localize
        String str = String.format("Block at %d %d %d should be %s", pos.getX(), pos.getY(), pos.getZ(), expected.getLocalizedName());
        TextComponentString errMsg = new TextComponentString(str);
        errMsg.getStyle().setColor(TextFormatting.RED);
        errMsg.getStyle().setBold(true);
        return errMsg;
    }
}
