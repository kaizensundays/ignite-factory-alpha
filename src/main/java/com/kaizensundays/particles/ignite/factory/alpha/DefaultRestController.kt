package com.kaizensundays.particles.ignite.factory.alpha

import com.kaizensundays.particles.ignite.factory.alpha.api.PingResponse
import com.kaizensundays.particles.ignite.factory.alpha.api.Quotes
import org.apache.ignite.Ignite
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created: Saturday 11/16/2019, 2:42 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@RestController
class DefaultRestController {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    lateinit var ignite: Ignite

    @ResponseBody
    @RequestMapping("/ping")
    fun ping(): PingResponse {
        return PingResponse()
    }

    @RequestMapping("/quote")
    fun quote(): Quotes {

        val cache = ignite.getOrCreateCache<String, List<String>>("quotes")

        val key = "Ari Gold"

        var quotes = cache.get(key)

        if (quotes == null) {

            cache.put(key, listOf(
                    "You will come back stronger then ever.",
                    "Nobody's happy in this town except for the losers.",
                    "I don't worry. I just win."
            ))

            quotes = cache.get(key)
        }

        logger.info("quotes: {}", quotes)

        return Quotes(quotes)
    }

    fun start() {
        Assert.notNull(ignite, "")

        logger.info("Started")
    }

    fun stop() {

        logger.info("Stopped")
    }

}