# commander

[![build](https://github.com/ravenlab/commander/workflows/build/badge.svg)](https://github.com/ravenlab/commander/actions?query=workflow%3Abuild)
[![codecov](https://codecov.io/gh/ravenlab/commander/branch/master/graph/badge.svg)](https://codecov.io/gh/ravenlab/commander)

## What is commander

Commander is a platform agnostic command framework for Minecraft server implementations. With Commander you can ideally write your commands once and use them everywhere. This is done through the Commander class which handles platform specific command registration. With Commander there are a variety of features: force command registration, annotation-based commands, command aliasing and child commands.

## Why

This project was mainly written for the [DynamicGui](https://github.com/ClubObsidian/DynamicGui) which currently has a wrapper for Bukkit and Sponge support but the commands have to be written seperately. This project implements a number of wrapper classes for things such as: players, senders, worlds etc so that code can be written for one platform and ran on many.

## How do I use it

Commander isn't really stable enough to use yet. The API should be stabalizing when the API hits 0.1.0 and will likely have it's last breaking change when 1.0.0 is eventually realeased.

## Supported platforms

* Bukkit - Fully feature complete
* Sponge - Next platform to be implemented
* Forge - Maybe some day
* Fabric - Maybe some day
