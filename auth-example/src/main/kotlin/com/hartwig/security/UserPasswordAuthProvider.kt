package com.hartwig.security

import com.hartwig.repository.UserRepository
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.Long.parseLong
import javax.inject.Singleton

@Singleton
class UserPasswordAuthProvider(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder): AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        val user = userRepository.findById(parseLong(authenticationRequest?.identity as String))
        if(user.isPresent) {
            if(passwordEncoder.matches(authenticationRequest.secret as String, user.get().password)) {
                return Flowable.just(UserDetails(user.get().cpf.toString(), listOf()))
            }
            return Flowable.error(AuthenticationException(AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)))
        }
        return Flowable.error(AuthenticationException(AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND)))
    }
}