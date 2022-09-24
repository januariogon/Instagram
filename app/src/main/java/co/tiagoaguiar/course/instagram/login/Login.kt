package co.tiagoaguiar.course.instagram.login

interface Login {

    interface Presenter{
        fun login(email: String, password: String)
    }

    interface View{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized()
    }
}