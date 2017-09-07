/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.multiblocks;

import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import com.digitalfishfun.modularmechanisms.blocks.multiblock.BlockPressComponent;
import com.digitalfishfun.modularmechanisms.blocks.multiblock.TileEntityMultiblock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class MultiblockChecker {
    public static int checkMultiblock(World world, BlockPos pos, EntityPlayer player) {
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
            if (world.isRemote)
                player.sendStatusMessage(new TextComponentTranslation("text.structure.invalid"), true);
        } else {
            buildMultiblock(succBuild, world, pos, blockList);
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

    private static void buildMultiblock(int rot, World world, BlockPos pos, List<Block> blockList) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                BlockPos npos;
                int bPos;
                if (rot == 0) {
                    npos = pos.add(x - 1, y - 1, 0);
                    bPos = ((2 - y) * 3) + x;
                } else if (rot == 1) {
                    npos = pos.add(x - 1, y - 1, 0);
                    bPos = ((2 - y) * 3) + (2 - x);
                } else if (rot == 2) {
                    npos = pos.add(0, y - 1, x - 1);
                    bPos = ((2 - y) * 3) + x;
                } else {
                    npos = pos.add(0, y - 1, x - 1);
                    bPos = ((2 - y) * 3) + (2 - x);
                }

                IBlockState newBlock = BlockRegistry.pressComponent.getDefaultState();

                world.setBlockState(npos, newBlock.withProperty(BlockPressComponent.POS, bPos), 3);

                TileEntityMultiblock tent = BlockRegistry.pressComponent.getTileEntity(world, npos);

                tent.setBaseBlock(blockList.get(bPos).getRegistryName().toString());
                tent.setIndex(bPos);
                tent.setParent(pos);
                tent.setRotation(rot);
            }
        }
    }
}
