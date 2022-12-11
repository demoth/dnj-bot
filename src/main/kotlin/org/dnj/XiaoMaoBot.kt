package org.dnj

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.time.LocalDate


class XiaoMaoBot : TelegramLongPollingBot() {
    override fun getBotUsername() = System.getenv("XIAO_BOTNAME")
        ?: throw IllegalStateException("Missing XIAO_BOTNAME env variable")
    override fun getBotToken() = System.getenv("XIAO_TOKEN")
        ?: throw IllegalStateException("Missing XIAO_TOKEN env variable")

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val message = update.message.text
            if (message == "/date") {
                val currentDate = LocalDate.now()

                println("Received update $update")

                execute(SendMessage(update.message.chatId.toString(), "The current date is: $currentDate"))
            }
        }
    }
}

fun main() {
    val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
    botsApi.registerBot(XiaoMaoBot())
}
