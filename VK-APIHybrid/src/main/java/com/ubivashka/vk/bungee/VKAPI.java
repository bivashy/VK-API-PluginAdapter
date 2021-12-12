package com.ubivashka.vk.bungee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import com.google.gson.JsonObject;
import com.ubivashka.vk.CallbackLongpoolAPI;
import com.ubivashka.vk.LongpoolAPIListener;
import com.ubivashka.vk.VKAPIPlugin;
import com.ubivashka.vk.VKEvent;
import com.ubivashka.vk.bungee.events.VKJsonEvent;
import com.ubivashka.vk.bungee.utils.CallbackAPI;
import com.ubivashka.vk.utils.VKUtil;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class VKAPI extends Plugin implements VKAPIPlugin {
	private VKUtil vkutil;

	private CallbackAPI callbackAPI;

	private Configuration config;

	private Random random;

	private Integer ts = null;

	private GroupActor actor;

	private VkApiClient vk;

	private static VKAPI instance;

	public void onEnable() {
		instance = this;
		loadDefaultFilter();
		this.config = loadConfiguration(getDataFolder(), getResourceAsStream("config.yml"));
		registerVK();
		System.out.println("\r\n\r\n" + ChatColor.DARK_AQUA
				+ "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\r\n".replaceAll("╗",
						ChatColor.AQUA + "╗" + ChatColor.DARK_AQUA)
				+ ChatColor.AQUA + "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝\r\n" + ChatColor.BLUE
				+ "██╗░░░██╗██╗░░██╗░░░░░░░█████╗░██████╗░██╗\r\n" + ChatColor.BLUE
				+ "██║░░░██║██║░██╔╝░░░░░░██╔══██╗██╔══██╗██║\r\n" + ChatColor.BLUE
				+ "╚██╗░██╔╝█████═╝░█████╗███████║██████╔╝██║\r\n" + ChatColor.BLUE
				+ "░╚████╔╝░██╔═██╗░╚════╝██╔══██║██╔═══╝░██║\r\n" + ChatColor.BLUE
				+ "░░╚██╔╝░░██║░╚██╗░░░░░░██║░░██║██║░░░░░██║\r\n" + ChatColor.BLUE
				+ "░░░╚═╝░░░╚═╝░░╚═╝░░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝\r\n" + ChatColor.DARK_AQUA
				+ "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\r\n".replaceAll("╗",
						ChatColor.AQUA + "╗" + ChatColor.DARK_AQUA)
				+ ChatColor.AQUA + "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝\r\n");
		if (ts == null) {
			System.out.println(ChatColor.RED + "ERROR occured while enabling group");
			return;
		}
		System.out.println(ChatColor.GREEN + "Group launched");
		new LongpoolAPIListener(this);
		this.vkutil = new VKUtil(this);
		this.callbackAPI = new CallbackAPI(this);
	}

	public static VKAPI getInstance() {
		if (instance == null)
			throw new UnsupportedOperationException("Cannot use bungee API in bukkit server");
		return instance;
	}

	@Override
	public void setTS() {
		int delay = getConfig().getInt("settings.delay");
		if (delay < 10 || delay > 40) {
			System.out.println(ChatColor.RED
					+ "Ваш delay в конфиг слишком низкий, или слишком высокий, при неправильной работе, попробуйте поставить её на нормальное значение (15)");
		}
		try {
			ts = vk.messages().getLongPollServer(actor).execute().getTs();
		} catch (ApiAccessException e) {
			System.out.println(ChatColor.RED + "Код ошибки: " + e.getCode());
			System.out.println(ChatColor.RED + "В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
			if (e.getCode() == 1) {
				System.out.println(ChatColor.RED + "Попробуйте запустить плагин чуть позже");
			}
			if (e.getCode() == 15) {
				System.out.println(ChatColor.RED + "Доступ запрещён.\r\n" + "Убедитесь, что Вы не ошиблись в конфиге");
			}
			if (e.getCode() == 5) {
				System.out.println(
						ChatColor.RED + "Авторизация не удалась. \r\n" + "Убедитесь, что Вы не ошиблись в конфиге");
			}
			System.out.println("\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n");
			e.printStackTrace();
		} catch (ApiException e) {
			System.out.println(ChatColor.RED + "Ошибка:");
			e.printStackTrace();
			return;
		} catch (ClientException e) {
			System.out.println(ChatColor.RED + "Нету доступа к сайту!");
			e.printStackTrace();
		}
	}

	private Configuration loadConfiguration(File folder, InputStream resourceAsStream) {
		try {
			if (!folder.exists())
				folder.mkdir();

			File config = new File(folder + File.separator + "config.yml");
			if (!config.exists())
				Files.copy(resourceAsStream, config.toPath(), new CopyOption[0]);
			Configuration defaults = ConfigurationProvider.getProvider(YamlConfiguration.class).load(resourceAsStream);
			return ConfigurationProvider.getProvider(YamlConfiguration.class).load(config, defaults);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public Configuration getConfig() {
		return this.config;
	}

	private void loadDefaultFilter() {
		java.util.logging.Logger logger = ProxyServer.getInstance().getLogger();
		logger.setFilter(new java.util.logging.Filter() {
			public boolean isLoggable(LogRecord record) {
				String message = record.getMessage();
				if (message.startsWith("Request: https://api.vk.com/")
						|| message.startsWith("Request: https://lp.vk.com/") || message.startsWith("ERROR StatusLogger")
						|| message.startsWith("SLF4J: Failed to load class")
						|| message.startsWith("SLF4J: Defaulting to no-operation")
						|| message.startsWith("SLF4J: See http:")
						|| (message.contains("lp.vk.com") && record.getLevel() == Level.SEVERE)
						|| (message.startsWith("Event {0}") && record.getParameters().length > 0
								&& record.getParameters()[0].toString().startsWith("com.ubivashka.vk.bungee.events")
								&& record.getLevel() == Level.WARNING))
					return false;
				return true;
			}
		});
	}

	@Override
	public void callEvent(VKEvent event) {
		if (event instanceof Event)
			ProxyServer.getInstance().getPluginManager().callEvent((Event) event);
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
		HttpTransportClient httpTransportClient = HttpTransportClient.getInstance();
		this.vk = new VkApiClient((TransportClient) httpTransportClient);
		this.random = new Random();
		this.actor = new GroupActor(Integer.valueOf(getConfig().getInt("groupInfo.groupID")),
				getConfig().getString("groupInfo.groupToken"));
		setTS();
	}

	public void callEvent(JsonObject json) {
		if (json == null)
			return;
		VKJsonEvent jsonEvent = new VKJsonEvent(json);
		callEvent(jsonEvent);
		getCallbackAPI().parse(json);
	}

	@Override
	public int getDelay() {
		return getConfig().getInt("settings.delay");
	}
}
