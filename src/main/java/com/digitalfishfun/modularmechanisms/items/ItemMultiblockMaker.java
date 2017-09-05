/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.items;

import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Block> blockList = Arrays.asList(
                Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.GLASS,
                Blocks.COBBLESTONE_WALL, BlockRegistry.pressCore, Blocks.COBBLESTONE_WALL,
                Blocks.COBBLESTONE_WALL, Blocks.HOPPER, Blocks.IRON_BLOCK
        );

        int succBuild = -1;

        for (int r = 0; r < 4; r++) {
            if (checkMultiblock(r, pos, blockList, world)) {
                succBuild = r;
            }
        }

        if (succBuild < 0) {
            player.sendStatusMessage(generateError(), true);
        }

        return succBuild;
    }

    private static boolean checkMultiblock(int typ, BlockPos pos, List<Block> blockList, World world) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                BlockPos npos;
                Block checkBlock;
                if (typ == 0) {
                    npos = pos.add(x - 1, y - 1, 0);
                    checkBlock = blockList.get(((2 - y) * 3) + x);
                } else if (typ == 1) {
                    npos = pos.add(x - 1, y - 1, 0);
                    checkBlock = blockList.get(((2 - y) * 3) + (2 - x));
                } else if (typ == 2) {
                    npos = pos.add(0, y - 1, x - 1);
                    checkBlock = blockList.get(((2 - y) * 3) + x);
                } else {
                    npos = pos.add(0, y - 1, x - 1);
                    checkBlock = blockList.get(((2 - y) * 3) + (2 - x));
                }
                if (world.getBlockState(npos).getBlock() != checkBlock) {
                    return false;
                }
            }
        }
        return true;
    }

    private static ITextComponent generateError() {
        // TODO: Localize
        TextComponentString errMsg = new TextComponentString("Invalid structure!");
        errMsg.getStyle().setColor(TextFormatting.RED);
        errMsg.getStyle().setBold(true);
        return errMsg;
    }
}
