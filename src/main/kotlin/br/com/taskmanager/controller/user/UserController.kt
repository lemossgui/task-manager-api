package br.com.taskmanager.controller.user

import br.com.taskmanager.response.ResponseModel
import br.com.taskmanager.service.user.UserService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api
@RestController
@RequestMapping("/user")
class UserController(
        private val service: UserService,
        private val mapper: UserDtoMapper,
        private val saveMapper: UserSaveDtoMapper,
) {

    @PostMapping
    fun save(@RequestBody dto: UserSaveDto): ResponseEntity<ResponseModel<String>> {
        val entity = saveMapper.mapToEntity(dto)
        val result = service.save(entity)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Usuário criado com sucesso!")
                .build()
    }

    @PatchMapping("/{id}/update-name")
    fun updateName(@PathVariable id: Long, @RequestBody dto: UserNameSaveDto): ResponseEntity<ResponseModel<Long>> {
        val result = service.updateName(id, dto.name)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Nome alterado com sucesso!")
                .build()
    }

    @PatchMapping("/{id}/update-email")
    fun updateEmail(@PathVariable id: Long, @RequestBody dto: UserEmailSaveDto): ResponseEntity<ResponseModel<Long>> {
        val result = service.updateEmail(id, dto.email)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.OK)
                .withMessage("E-mail alterado com sucesso!")
                .build()
    }

    @PatchMapping("/{id}/update-password")
    fun updatePassword(@PathVariable id: Long, @RequestBody dto: UserPasswordSaveDto): ResponseEntity<ResponseModel<Long>> {
        val result = service.updatePassword(id, dto.oldPassword, dto.newPassword, dto.newPasswordConfirmation)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Senha alterada com sucesso!")
                .build()
    }

    @PatchMapping("/{id}/update-receiving-notification")
    fun updateReceivingNotification(@PathVariable id: Long, @RequestBody dto: UserNotificationSaveDto): ResponseEntity<ResponseModel<Long>> {
        val result = service.updateReceivingNotification(id, dto.notificationIsEnable)
        val status = if (dto.notificationIsEnable) "habilitadas" else "desabilitadas"
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Notificações $status")
                .build()
    }

    @GetMapping("/logged")
    fun getLoggedUser(): ResponseEntity<ResponseModel<UserDto>> {
        val result = service.getLoggedUser()
        val dto = mapper.mapToDto(result)
        return ResponseModel(dto)
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Usuário obtido com sucesso!")
                .build()
    }
}