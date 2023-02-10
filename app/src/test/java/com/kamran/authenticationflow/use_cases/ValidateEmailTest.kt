package com.kamran.authenticationflow.use_cases

import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ValidateEmailTest(
    private val email: String,
    private val isValid: Boolean
) {

    lateinit var validateEmail: ValidateEmail

    @Before
    fun setUp() {
        validateEmail = ValidateEmail()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "when email is {0}, then we are getting {1}"
        )
        fun getValidateEmailResult(): Iterable<Array<Any>> {
            /* (email, isValid) */
            return arrayListOf(
                arrayOf("abc", false),
                arrayOf("abc@abc", false),
                arrayOf("abc@abc.com", true),
                arrayOf("kamran@gmail.pk", true),
                arrayOf("TheDidacticGamer@gmail.com", true),
                arrayOf("123456", false),
                arrayOf("TheDetailedGamer@gmail.com", true),
                arrayOf("username", false),
                arrayOf(".,uflu", false),
                arrayOf("TheHarmoniousGamer@gmail.com", true),
                arrayOf("@.veryshortname", false),
                arrayOf("yzr", false),
                arrayOf("TheBlueGamer@gmail.com", true),
                arrayOf("TheAmusedGamer@gmail.com", true),
                arrayOf("@abc@.com", false),
                arrayOf("randomemail.com", false),
                arrayOf("TheFascinatedGamer@gmail.com", true),
                arrayOf("https://google.com", false),
                arrayOf("niceemaildear", false),
                arrayOf("TheJudiciousGamer@gmail.co", true),
            )
        }
    }

    @Test
    fun `test validate email`() {
        val result = validateEmail(email)

        Assert.assertEquals(result, isValid)
    }

}