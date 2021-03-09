package com.github.commander.test.command;

import com.github.ravenlab.commander.command.annotation.Command;

@Command("child")
public class ChildCommand<T> extends ParentCommand<T> {

}