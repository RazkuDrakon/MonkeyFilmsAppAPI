package login.domain

import login.data.LoginRepository

class LoginUseCase {
    private val respository= LoginRepository()

    suspend operator fun invoke(user: String, password: String) : Boolean {
        return respository.doLogin(user, password)
    }

}