package io.github.candycalc.phlexiful.util;

import at.petrak.hexcasting.api.spell.SpellList;
import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.utils.NBTHelper;
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes;
import com.mojang.datafixers.types.templates.Tag;
import io.github.candycalc.phlexiful.Phlexiful;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpellLacedData {

	//init & overwriting stuff
	public static void setCaster(Object target, UUID uuid) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		nbt.putUuid("caster", uuid);
	}
	public static void setCaster(Object target, ServerPlayerEntity caster) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		nbt.putUuid("caster", caster.getUuid());
	}

	public static void setSpell(Object target, List<SpellList> spell) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		NbtList patternsList = new NbtList();
		for (int i =0; i < spell.get(0).size(); i++) {
			patternsList.add(HexIotaTypes.serialize(spell.get(0).getAt(i)));
		}
		NBTHelper.putList(nbt, "patterns", patternsList);
	}

	public static void setBattery(Object target, double battery) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		nbt.putDouble("battery", battery);
	}

	//manipulation stuff
	public static double addBattery(Object target, double addend) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		double battery = nbt.getDouble("battery");
		battery += addend;
		nbt.putDouble("battery", battery);
		return battery;
	}

	//reading stuff
	public static UUID getCasterUuid(Object target) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		return nbt.getUuid("caster");
	}

	public static List<Iota> getSpell(Object target, ServerWorld world) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();

		var patsTag = NBTHelper.getList(nbt, "patterns", NbtElement.COMPOUND_TYPE);
		var out = new ArrayList<Iota>();
		for (var patTag : patsTag) {
			NbtCompound tag = NBTHelper.getAsCompound(patTag);
			out.add(HexIotaTypes.deserialize(tag, world));
		}
		return out;
	}

	public static double getBattery(Object target) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		return nbt.getDouble("battery");
	}

	//boolean stuff
	public static boolean hasLacedSpell(Object target) {
		IEntityDataSaver data = (IEntityDataSaver) target;
		NbtCompound nbt = data.getPersistentData();
		boolean hasCaster = NBTHelper.hasUUID(nbt, "caster");
		boolean hasSpell = NBTHelper.hasList(nbt, "patterns");
		boolean hasBattery = NBTHelper.hasDouble(nbt, "battery");
		return hasCaster & hasSpell & hasBattery;
	}
}
