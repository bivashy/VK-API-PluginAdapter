package com.ubivashka.vk.spigot;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.ubivashka.vk.CallbackLongpoolAPI;
import com.ubivashka.vk.VKAPIPlugin;
import com.ubivashka.vk.VKEvent;
import com.ubivashka.vk.spigot.logfilter.LogFilter;
import com.ubivashka.vk.spigot.utils.CallbackAPI;
import com.ubivashka.vk.spigot.vklisteners.LongpollAPI;
import com.ubivashka.vk.utils.VKUtil;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VKAPI extends JavaPlugin implements VKAPIPlugin {
	private VKUtil vkutil;
	private CallbackAPI callbackAPI;
	private LongpollAPI longPoolAPI;

	private Random random;
	private Integer ts = null;
	private GroupActor actor;
	private VkApiClient vk;

	private static VKAPI instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();

		new LogFilter();

		registerVK();

		setTS();

		Bukkit.getConsoleSender()
				.sendMessage("\n\r" + ChatColor.BLUE + " ##      ## ##   ##           ##     #######  ##\r\n"
						+ ChatColor.BLUE + "/##     /##/##  ##           ####   /##////##/##\r\n" + ChatColor.BLUE
						+ "/##     /##/## ##           ##//##  /##   /##/##\r\n" + ChatColor.BLUE
						+ "//##    ## /####    #####  ##  //## /####### /##\r\n" + ChatColor.BLUE
						+ " //##  ##  /##/##  /////  ##########/##////  /##\r\n" + ChatColor.BLUE
						+ "  //####   /##//##       /##//////##/##      /##\r\n" + ChatColor.BLUE
						+ "   //##    /## //##      /##     /##/##      /##\r\n" + ChatColor.BLUE
						+ "    //     //   //       //      // //       // \r\n");

		debug("Group launched!");

		longPoolAPI = new LongpollAPI(this);
		vkutil = new VKUtil(this);
		callbackAPI = new CallbackAPI(this);
	}

	public void onDisable() {
		Bukkit.getServer().getScheduler().cancelTasks(this);
		Bukkit.getServer().getScheduler().cancelTask(longPoolAPI.getTask().getTaskId());
	}

	public static VKAPI getInstance() {
		if (instance == null)
			throw new UnsupportedOperationException("Cannot use bukkit API in bungee server");
		return instance;
	}

	public void setTS() {
		try {
			ts = vk.messages().getLongPollServer(actor).execute().getTs();
		} catch (ApiAccessException e) {
			error("Код ошибки: " + e.getCode());
			error("В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
			if (e.getCode() == 1) {
				error("Попробуйте запустить плагин чуть позже");
			}
			if (e.getCode() == 15) {
				error("Доступ запрещён.\r\n" + "Убедитесь, что Вы не ошиблись в конфиге");
			}
			if (e.getCode() == 5) {
				error("Авторизация не удалась. \r\n" + "Убедитесь, что Вы не ошиблись в конфиге");
			}
			error("\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n");
			e.printStackTrace();
		} catch (ApiException e) {
			error("Ошибка:");
			e.printStackTrace();
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		} catch (ClientException e) {
			error("Нету доступа к сайту!");
			e.printStackTrace();
		}
	}

	public void debug(String msg) {
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GRAY + "[" + ChatColor.BLUE + "VK-API" + ChatColor.GRAY + "] " + ChatColor.GREEN + msg);
	}

	public void warn(String msg) {
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GRAY + "[" + ChatColor.BLUE + "VK-API" + ChatColor.GRAY + "] " + ChatColor.YELLOW + msg);
	}

	public void error(String msg) {
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GRAY + "[" + ChatColor.BLUE + "VK-API" + ChatColor.GRAY + "] " + ChatColor.RED + msg);
	}

	@Override
	public void callEvent(VKEvent event) {
		if (event instanceof org.bukkit.event.Event)
			Bukkit.getPluginManager().callEvent((org.bukkit.event.Event) event);

	}

	@Override
	public VKUtil getVKUtil() {
		return vkutil;
	}

	@Override
	public Random getRandom() {
		return random;
	}

	@Override
	public Integer getTs() {
		return ts;
	}

	@Override
	public GroupActor getActor() {
		return actor;
	}

	@Override
	public VkApiClient getVK() {
		return vk;
	}

	@Override
	public CallbackLongpoolAPI getCallbackAPI() {
		return callbackAPI;
	}

	private void registerVK() {
		TransportClient transportClient = HttpTransportClient.getInstance();
		vk = new VkApiClient(transportClient);
		random = new Random();
		actor = new GroupActor(getConfig().getInt("groupInfo.groupID"), getConfig().getString("groupInfo.groupToken"));
	}

}
