package com.github.ravenlab.commander.world.bukkit;

import org.bukkit.World;

import com.github.ravenlab.commander.world.CommanderWorld;

public class BukkitCommanderWorld extends CommanderWorld<World> {

	public BukkitCommanderWorld(World world) {
		super(world);
	}

	@Override
	public String getName() {
		return this.getNative().getName();
	}
}