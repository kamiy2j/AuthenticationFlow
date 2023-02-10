package com.kamran.authenticationflow.use_cases

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ValidatePasswordTest(
    private val password: String,
    private val isValid: Boolean
) {

    lateinit var validatePassword: ValidatePassword

    @Before
    fun setUp() {
        validatePassword = ValidatePassword()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "when password is {0}, then we are getting {1}"
        )
        fun getValidateEmailResult(): Iterable<Array<Any>> {
            /* (password, isValid) */
            return arrayListOf(
                arrayOf("abcdefg", false),
                arrayOf("12345678", false),
                arrayOf("Abcd1234", true),
                arrayOf("Kamrean1234", true),
                arrayOf("abcD1234", true),
                arrayOf("12345Ab", false),
                arrayOf("QO0934556", true),
                arrayOf("password", false),
                arrayOf("./,'./,;./,;.,", false),
                arrayOf("TheGreatAbcd1", true),
                arrayOf("@.wierdpassword", false),
                arrayOf("yzr", false)
            )
        }
    }

    @Test
    fun `test validate password`() {
        val result = validatePassword(password = password)

        assertEquals(result, isValid)
    }

}