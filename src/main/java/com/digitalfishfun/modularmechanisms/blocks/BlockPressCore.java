/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks;

import com.digitalfishfun.modularmechanisms.multiblocks.MultiblockChecker;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// TODO: Move common logic elsewhere
public class BlockPressCore extends BaseMMBlock {

    public BlockPressCore() {
        super("press_core", Material.IRON);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int blockRot = MultiblockChecker.checkMultiblock(world, pos, player);
        if (blockRot > -1) {
            player.swingArm(hand);
        }
        return true;
    }
}
