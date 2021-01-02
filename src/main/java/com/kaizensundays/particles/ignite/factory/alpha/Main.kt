package com.kaizensundays.particles.ignite.factory.alpha

import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created: Saturday 11/16/2019, 11:23 AM Eastern Time
 *
 * @author Sergey Chuykov
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val context = ClassPathXmlApplicationContext("/jetty.xml")
        context.registerShutdownHook()

    }

}