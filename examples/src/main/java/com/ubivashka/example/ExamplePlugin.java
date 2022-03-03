package com.ubivashka.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ubivashka.example.listeners.KeyboardExample;
import com.ubivashka.example.listeners.ScreenNameExample;

public class ExamplePlugin extends JavaPlugin {
//  НИ В КОЕМ СЛУЧАЕ НЕ ИНИЦАЛИЗИРУЙТЕ ВОТ ТАКИМ ОБРАЗОМ, ТАК КАК ЭТО ВЫКИНЕТ ОШИБКУ NullPointerException так как плагин BukkitVkApiPlugin не успеет включится
// 	
//	private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
//			.getVkApiClient(); 

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new KeyboardExample(), this);
		Bukkit.getPluginManager().registerEvents(new ScreenNameExample(), this);
	}
}
