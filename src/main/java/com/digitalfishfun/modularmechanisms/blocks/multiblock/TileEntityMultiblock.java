/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.blocks.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMultiblock extends TileEntity {
    private String baseBlock;
    private int multiPos;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setString("base", baseBlock);
        compound.setInteger("pos", multiPos);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        baseBlock = compound.getString("base");
        multiPos = compound.getInteger("pos");
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

    public String getBase() {
        return baseBlock;
    }

    public int getIndex() {
        return multiPos;
    }

    public void setBaseBlock(String base) {
        baseBlock = base;
        markDirty();
    }

    public void setIndex(int pos) {
        multiPos = pos;
        markDirty();
    }
}
