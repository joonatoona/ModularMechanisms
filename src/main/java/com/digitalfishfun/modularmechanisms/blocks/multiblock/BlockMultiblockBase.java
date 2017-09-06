/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks.multiblock;

import com.digitalfishfun.modularmechanisms.blocks.BaseTileEntity;
import com.digitalfishfun.modularmechanisms.blocks.BlockRegistry;
import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;

public class BlockMultiblockBase extends BaseTileEntity<TileEntityMultiblock> {
    public BlockMultiblockBase() {
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

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TileEntityMultiblock ent = BlockRegistry.multiBlock.getTileEntity(world, pos);
        MMLogger.info(ent.getBase());
        drops.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ent.getBase())), 1));
        World wrld = (World)world;
        wrld.removeTileEntity(pos);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }
}
