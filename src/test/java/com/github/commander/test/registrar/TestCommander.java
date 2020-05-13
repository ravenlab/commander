package com.github.commander.test.registrar;

import com.github.commander.test.platform.TestCommand;
import com.github.commander.test.platform.TestPlugin;
import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

public class TestCommander extends Commander<TestPlugin, TestCommand> {

	private boolean mockStatus;
	public TestCommander(boolean mockStatus) {
		this.mockStatus = mockStatus;
	}
	
	@Override
	protected String registerAlias(TestPlugin plugin, TestCommand command, String alias, boolean forceRegister) {
		return alias;
	}

	@Override
	protected boolean unregisterAlias(String command) {
		return this.mockStatus;
	}

	@Override
	protected TestCommand createCommandWrapper(CommandData data, CommanderCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPluginName(TestPlugin plugin) {
		// TODO Auto-generated method stub
		return null;
	}
}