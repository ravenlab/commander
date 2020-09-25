package com.github.ravenlab.commander.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

	String value();
	String[] aliases() default {};
	String permission() default "";
	String permissionMessage() default "";
	String usage() default "";
	
}