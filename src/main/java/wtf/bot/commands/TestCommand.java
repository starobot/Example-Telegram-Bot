package wtf.bot.commands;

import net.staro.bot.api.Bot;
import net.staro.bot.api.command.Builder;
import net.staro.bot.api.command.impl.BotCommand;

public class TestCommand extends BotCommand {
    public TestCommand(Bot bot) {
        super(bot, "test");
    }

    @Override
    public void build(Builder builder) {
        builder.execute(message -> { // This executes whenever the command is typed in chat by user.
            bot.keyboard().addReplyButtonToRow(1, "Continue");
            return "This is a test command to showcase the ability of" +
                    " the command builder. Press the appearing button to proceed with the arguments.";
        }).thenMessage(message -> { // This is an argument for when we expect a Message data from user.
            if (message.getText().equals("Continue")) {
                bot.keyboard().createInlineButton("Male", "1");
                bot.keyboard().createInlineButton("Female", "2");
                return "Alright, now choose your gender.";
            } else {
                return "Goodbye";
            }
        }).thenInline(query -> { // This is an argument for when we expect a CallbackQuery data from user.
            if (query.getData().equals("1")) {
                return "You are a male.";
            } else if (query.getData().equals("2")) {
                return "You are a female.";
            } else {
                return "This is not supposed to happen.";
            }
        });
    }

    @Override
    public boolean hasPermisions() {
        return false;
    }

    @Override
    public boolean isDeletable() {
        return true;
    }

}
