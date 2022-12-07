package br.com.taskmanager.controller.priority

import br.com.taskmanager.response.ResponseModel
import br.com.taskmanager.service.priority.PriorityService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@RestController
@RequestMapping("/priority")
class PriorityController(
        private val service: PriorityService,
        private val mapper: PriorityDtoMapper,
) {

    @GetMapping
    fun findAll(): ResponseEntity<ResponseModel<List<PriorityDto>>> {
        val result = service.findAll()
        return ResponseModel(mapper.mapToDtoList(result))
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Lista obtida com sucesso!")
                .build()
    }
}