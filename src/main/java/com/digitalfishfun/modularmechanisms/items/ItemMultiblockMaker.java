/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMultiblockMaker extends BaseMMItem {

    public ItemMultiblockMaker() {
        super("multiblock_maker");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if (world.isRemote) {
            if (world.getBlockState(pos).getBlock().equals(BlockRegistry.pressCore)) {
                MMLogger.info("Attempting to form MultiBlock at "+pos.toString());

                player.swingArm(hand);
                return EnumActionResult.PASS;
            }
        }

        return EnumActionResult.FAIL;
    }
}
