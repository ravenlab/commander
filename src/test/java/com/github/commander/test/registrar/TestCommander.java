package com.github.commander.test.registrar;

import java.util.Collection;

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
	protected boolean tryToRegister(String alias, boolean forceRegister, TestCommand command) {
		return this.mockStatus;
	}

	@Override
	protected boolean tryToUnregister(Collection<String> commands) {
		return this.mockStatus;
	}

	@Override
	protected TestCommand createCommandWrapper(CommandData data, CommanderCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

}
