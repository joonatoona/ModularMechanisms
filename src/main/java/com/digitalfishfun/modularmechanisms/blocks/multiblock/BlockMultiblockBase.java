/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks.multiblock;

import com.digitalfishfun.modularmechanisms.blocks.BaseTileEntity;
import com.digitalfishfun.modularmechanisms.utils.MMLogger;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockMultiblockBase extends BaseTileEntity<TileEntityMultiblock> {

    public static final IProperty<Integer> POS = PropertyInteger.create("pos", 0, 8);

    public BlockMultiblockBase(String name) {
        super(name, Material.IRON);
        setDefaultState(blockState.getBaseState().withProperty(POS, 0));
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
        TileEntityMultiblock ent = super.getTileEntity(world, pos);
        MMLogger.info(ent.getBase());
        drops.add(new ItemStack(ent.getBase(), 1));
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(world, player, pos, state, te, stack);

        TileEntityMultiblock ent = (TileEntityMultiblock)te;

        int rot = ent.getRotation();
        BlockPos parentPos = ent.getParentPos();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                BlockPos npos;

                if (rot == 0 || rot == 1) {
                    npos = parentPos.add(x - 1, y - 1, 0);
                } else {
                    npos = parentPos.add(0, y - 1, x - 1);
                }

                TileEntityMultiblock blk = super.getTileEntity(world, npos);
                if (blk == null) continue;
                blk.revertBlock(npos, world);
            }
        }

        world.setBlockToAir(pos);
        world.removeTileEntity(pos);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(POS, meta);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {POS});
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
