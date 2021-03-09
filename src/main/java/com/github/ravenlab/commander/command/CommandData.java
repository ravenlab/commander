package com.github.ravenlab.commander.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import com.github.ravenlab.commander.command.annotation.Command;
import com.github.ravenlab.commander.util.ChatColor;

public class CommandData {

	public static final String NO_PERMISSION_MESSAGE = "&cYou do not have permission to execute that command!";
	
	private String name;
	private Collection<String> aliases;
	private String permission;
	private String permissionMessage;
	private String usage;
	
	public CommandData(String name, Collection<String> aliases, String permission, String permissionMessage, String usage) {
		this.name = name;
		this.aliases = Collections.unmodifiableCollection(aliases);
		this.permission = permission;
		this.permissionMessage = permissionMessage;
		this.usage = usage;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Collection<String> getAliases() {
		return this.aliases;
	}
	
	public String getPermission() {
		return this.permission;
	}
	
	public String getPermissionMessage() {
		return this.permissionMessage;
	}
	
	public String getUsage() {
		return this.usage;
	}
	
	public static class Builder {
		
		private String name;
		private Collection<String> aliases;
		private String permission;
		private String permissionMessage;
		private String usage;
		
		public Builder() {
			this.name = null;
			this.aliases = new ArrayList<>();
			this.permission = "";
			this.permissionMessage = "";
			this.usage = "";
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder addAliases(Collection<String> aliases) {
			this.aliases.addAll(aliases);
			return this;
		}
		
		public Builder addAliases(String[] aliases) {
			this.addAliases(Arrays.asList(aliases));
			return this;
		}
		
		public Builder setPermission(String permission) {
			this.permission = permission;
			return this;
		}
		
		public Builder setPermissionMessage(String permissionMessage) {
			this.permissionMessage = permissionMessage;
			return this;
		}
		
		public Builder setUsage(String usage) {
			this.usage = usage;
			return this;
		}
		
		public Builder consumeCommand(CommanderCommand<?> command) {
			Command[] cmdAr = command.getClass().getAnnotationsByType(Command.class);
			if(cmdAr.length == 0) {
				return this;
			}
			
			Command found = cmdAr[0];
			this.setName(found.value());
			this.addAliases(found.aliases());
			this.setPermission(found.permission());
			this.setPermissionMessage(found.permissionMessage());
			this.setUsage(found.usage());
			return this;
		}
		
		public Optional<CommandData> build() {
			if(this.name == null) {
				return Optional.empty();
			}
			String lowerName = this.name;
			Collection<String> aliases = new HashSet<>();
			for(String alias : this.aliases) {
				aliases.add(alias.toLowerCase());
			}
			aliases.add(lowerName);
			
			if(this.permissionMessage.equals("")) {
				this.permissionMessage = NO_PERMISSION_MESSAGE;
			}
			this.permissionMessage = ChatColor.translateAlternateColorCodes('&', this.permissionMessage);
			CommandData commandData = new CommandData(lowerName, aliases, this.permission, this.permissionMessage, this.usage);
			return Optional.of(commandData);
		}
	}
}