package com.ubivashka.example.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;

public class KeyboardExample implements Listener {
	private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
			.getVkApiClient();
	private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
			.getActor();
	private final static Random RANDOM = new Random();

	@EventHandler
	public void onMessage(VKMessageEvent e) {
		if (!e.getMessage().getText().startsWith("клава"))
			return;
		try {
			// keyboard прикрепляет клавиатуру в сообщение, если прикрепить null, то
			// клавиатура исчезнет
			CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).keyboard(createKeyboard()).peerId(e.getPeer()) // https://vk.com/dev/messages.send
					.message("Текст сообщения").execute();
		} catch (ApiException | ClientException e1) {
			e1.printStackTrace();
		}
	}

	public Keyboard createKeyboard() {
		// Создаем пустую клавиатуру
		Keyboard keyboard = new Keyboard();
		// Создаем двухмерный список
		List<List<KeyboardButton>> allKey = new ArrayList<>();
		// Создаем первую линию клавиатуры
		List<KeyboardButton> line1 = new ArrayList<>();
		// Создаем кнопку
		KeyboardButton button = new KeyboardButton();
		// Создаем действие при нажатии на кнопку
		KeyboardButtonAction action = new KeyboardButtonAction();
		// Устанавливаем текст на кнопке
		action.setLabel("Текст на кнопку");
		// Тип кнопки (Доступные типы: CALLBACK, OPEN_APP, OPEN_LINK,
		// OPEN_PHOTO, START, TEXT, VKPAY)
		// CALLBACK - CallBack кнопка, и при нажатии на кнопку вызывается ивент
		// VKCallbackButtonPressEvent. Советую использовать
		// action.setPayload("тутУникальныйТекстДляКнопки") для того чтобы можно было
		// иметь много callback кнопок, и чтобы каждый из них отвечал за определенные
		// действия

		// OPEN_APP - action.setAppId(id приложения) чтобы установить какое приложение
		// ВК должно открываться

		// OPEN_LINK - action.setLink(ссылка) чтобы установить открытие ссылки при
		// нажатии

		// START - клавиатура появляется если у пользователя нету переписки с ботом

		// TEXT - вводит текст в Label (В нашем случае пользователь введёт 'Текст на
		// кнопку') от имени пользователя

		action.setType(TemplateActionTypeNames.TEXT);
		// Устанавливаем действие при клике
		button.setAction(action);

		// Устаналиваем цвет кнопки (Доступные цвета: DEFAULT, NEGATIVE, POSITIVE,
		// PRIMARY)
		button.setColor(KeyboardButtonColor.DEFAULT);
		// Добавляем кнопку в линию
		line1.add(button);
		// Создаем вторую линию клавиатуры
		List<KeyboardButton> line2 = new ArrayList<>();
		// Добавляем кнопку в вторую линию
		line2.add(new KeyboardButton()
				.setAction(new KeyboardButtonAction().setLabel("/помощь").setType(TemplateActionTypeNames.TEXT))
				.setColor(KeyboardButtonColor.NEGATIVE));
		// Добавляем линии в наш двухмерный список
		allKey.add(line1);
		allKey.add(line2);
		// keyboard.setInline(true) Прикрепляет клавиатуру на сообщение
		// keyboard.setOneTime(true) После использования клавиатура исчезает
		// ВНИМАНИЕ! Inline и OneTime вместе не работают
		// Устанавливаем кнопки (Нужно выставить двухмерный массив)
		keyboard.setButtons(allKey);
		return keyboard;
	}
}
