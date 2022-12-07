package br.com.taskmanager.controller.user

import br.com.taskmanager.response.ResponseModel
import br.com.taskmanager.service.user.UserService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@RestController
@RequestMapping("/user")
class UserController(
        private val service: UserService,
        private val saveMapper: UserSaveDtoMapper,
) {

    @PostMapping
    fun save(@RequestBody dto: UserSaveDto): ResponseEntity<ResponseModel<Long>> {
        val entity = saveMapper.mapToEntity(dto)
        val result = service.save(entity)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Usuário criado com sucesso!")
                .build()
    }
}