package tw.waterballsa.utopia.utopiagamificationquest.extensions

import dev.minn.jda.ktx.messages.Embed
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction
import tw.waterballsa.utopia.utopiagamificationquest.domain.Mission
import java.time.LocalDateTime

fun String.toDate(): LocalDateTime = LocalDateTime.parse(this)

fun Mission.publishToUser(user: User): MessageCreateAction {
    val channel = user.openPrivateChannel().complete()

    return channel.sendMessageEmbeds(
        Embed {
            title = quest.title
            description = quest.description
            color = 706146

            field {
                name = "任務條件"
                //轉成 string 並去除多餘的換行
                value = "${quest.criteria}".replace(Regex("\\n{2,}"), "\n")
            }

            field {
                name = "任務位置"
                value = quest.criteria.link
                inline = true
            }
        })
}
