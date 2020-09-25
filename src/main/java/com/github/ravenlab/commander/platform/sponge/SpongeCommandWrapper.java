package com.github.ravenlab.commander.platform.sponge;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.CommanderExecutor;

public class SpongeCommandWrapper implements CommandCallable {

	private CommanderExecutor<CommandSource> executor;
	
	public SpongeCommandWrapper(CommanderCommand<CommandSource> command)  {
		this.executor = new CommanderExecutor<>(command, new SpongeTypeResolver());
	}

	@Override
	public CommandResult process(CommandSource source, String arguments) throws CommandException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSuggestions(CommandSource source, String arguments, Location<World> targetPosition)
			throws CommandException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean testPermission(CommandSource source) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Text> getShortDescription(CommandSource source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Text> getHelp(CommandSource source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Text getUsage(CommandSource source) {
		// TODO Auto-generated method stub
		return null;
	}
}