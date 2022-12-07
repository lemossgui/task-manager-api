package br.com.taskmanager.controller.authentication

import br.com.taskmanager.response.ResponseModel
import br.com.taskmanager.service.authentication.AuthenticationService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@RestController
@RequestMapping("/authentication")
class AuthenticationController(
        private val service: AuthenticationService,
) {

    @PostMapping("/login")
    fun doLogin(@RequestBody dto: CredentialDto): ResponseEntity<ResponseModel<String>> {
        val result = service.doLogin(dto.identifier, dto.password)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Usuário autenticado com sucesso!")
                .build()
    }
}