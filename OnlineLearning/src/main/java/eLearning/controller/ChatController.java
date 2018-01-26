package eLearning.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eLearning.domain.Chat;
import eLearning.domain.ChatMessage;
import eLearning.domain.User;
import eLearning.repository.ChatMessageRepository;
import eLearning.repository.ChatRepository;
import eLearning.repository.UserRepository;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	ChatRepository chatRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ChatMessageRepository chatMessageRepo;

	public ChatController() {

	}

	@RequestMapping(value = "/")
	public String index(@RequestParam(value = "chatId", required = true) int chatId, Model model, Principal principal) {
		User user = userRepo.findByUsername(principal.getName());
		model.addAttribute("chatList", user.getChatList());
		return "chat/chatPlatform";
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("message") ChatMessage message, @RequestParam("chatId") int chatId, Principal principal, Model model) {
		Chat senderChat = chatRepo.findById(chatId);
		senderChat.getSentMessages().add(message);
		User receiver = userRepo.findByEmail(senderChat.getRecipient());
		Chat receiverChat = receiver.getChatList().stream().filter(ch -> ch.getRecipient().equals(senderChat.getRecipient())).findFirst().get();
		receiverChat.getReceivedMessages().add(message);
		chatMessageRepo.save(message);
		chatRepo.save(senderChat);
		chatRepo.save(receiverChat);
		model.addAttribute("chat", senderChat);
		return "chat/index";
	}

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public String deleteMessage(@RequestParam("chatId") int chatId, @RequestParam("messageId") int messageId, Principal principal, Model model) {
		Chat chat = chatRepo.findById(chatId);
		chat.getSentMessages().removeIf(msg -> msg.getId() == messageId);
		chatRepo.save(chat);
		model.addAttribute("chat", chat);
		return "chat/index";
	}

	@RequestMapping(value = "/newChat", method = RequestMethod.POST)
	public String newChat(@ModelAttribute("chat") Chat chat, Principal principal, Model model) {
		User user = userRepo.findByUsername(principal.getName());
		Chat tmpChat = user.getChatList().stream().filter(ch -> ch.getRecipient() == chat.getRecipient()).findFirst().orElse(null);
		if (tmpChat != null) {
			return "chat/index";
		}
		user.getChatList().add(chat);
		String recpient = chat.getRecipient();
		User receiver = userRepo.findByEmail(recpient);
		Chat recipientChat = new Chat();
		recipientChat.setRecipient(user.getEmail());
		receiver.getChatList().add(recipientChat);
		chatRepo.save(recipientChat);
		chatRepo.save(chat);
		userRepo.save(receiver);
		userRepo.save(user);
		model.addAttribute("chatList", user.getChatList());
		return "chat/index";
	}

	@RequestMapping(value = "/deleteChat", method = RequestMethod.GET)
	public String deleteChat(@RequestParam("chatId") int chatId, Principal principal) {
		User user = userRepo.findByUsername(principal.getName());
		user.getChatList().removeIf(chat -> chat.getId() == chatId);
		chatRepo.delete(chatId);
		return "chat/index";
	}

}