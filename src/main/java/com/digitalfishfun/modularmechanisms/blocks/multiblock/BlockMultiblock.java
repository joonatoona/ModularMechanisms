/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks.multiblock;

import com.digitalfishfun.modularmechanisms.blocks.BaseTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMultiblock extends BaseTileEntity<TileEntityMultiblock> {
    public BlockMultiblock() {
        super("multiblock_block", Material.IRON);
    }


    @Override
    public Class<TileEntityMultiblock> getTileEntityClass() {
        return TileEntityMultiblock.class;
    }

    @Nullable
    @Override
    public TileEntityMultiblock createTileEntity(World world, IBlockState state) {
        return new TileEntityMultiblock();
    }
}
