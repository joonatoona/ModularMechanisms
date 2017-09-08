/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks.multiblock;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TileEntityMultiblock extends TileEntity {
    private Block baseBlock;
    private int multiPos;

    private BlockPos parentPos;
    private int rotation;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setString("base", baseBlock.getRegistryName().toString());
        compound.setInteger("pos", multiPos);

        compound.setInteger("parentx", parentPos.getX());
        compound.setInteger("parenty", parentPos.getY());
        compound.setInteger("parentz", parentPos.getZ());

        compound.setInteger("rot", rotation);

        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        baseBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(compound.getString("base")));
        multiPos = compound.getInteger("pos");

        int x = compound.getInteger("parentx");
        int y = compound.getInteger("parenty");
        int z = compound.getInteger("parentz");

        parentPos = new BlockPos(x, y, z);
        rotation = compound.getInteger("rot");

        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new SPacketUpdateTileEntity(this.getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    public Block getBase() {
        return baseBlock;
    }

    public int getIndex() {
        return multiPos;
    }

    public int getRotation() {
        return rotation;
    }

    public BlockPos getParentPos() {
        return parentPos;
    }

    public void setBaseBlock(Block base) {
        baseBlock = base;
        markDirty();
    }

    public void setIndex(int pos) {
        multiPos = pos;
        markDirty();
    }

    public void setParent(BlockPos pos) {
        parentPos = pos;
        markDirty();
    }

    public void setRotation(int rot) {
        rotation = rot;
        markDirty();
    }

    public void revertBlock(BlockPos pos, World world) {
        world.setBlockState(pos, baseBlock.getDefaultState());
        world.removeTileEntity(pos);
    }
}
