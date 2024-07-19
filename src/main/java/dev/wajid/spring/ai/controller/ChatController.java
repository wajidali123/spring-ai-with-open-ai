package dev.wajid.spring.ai.controller;



import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    private final OpenAiChatClient openAiChatClient;
    public ChatController(ChatClient chatClient, OpenAiChatClient openAiChatClient) {
        this.chatClient = chatClient;
        this.openAiChatClient = openAiChatClient;
    }

    @RequestMapping(value = "/dad-joke", method = RequestMethod.GET)
    public String generate(@RequestParam(value ="message", defaultValue = "Tell me a joke") String message) {
        return chatClient.call(message);
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return openAiChatClient.stream(prompt);
    }
}
