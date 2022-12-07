package br.com.taskmanager.controller.userCategory

import br.com.taskmanager.response.ResponseModel
import br.com.taskmanager.service.userCategory.UserCategoryService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api
@RestController
@RequestMapping("/user-category")
class UserCategoryController(
        private val service: UserCategoryService,
        private val mapper: UserCategoryDtoMapper,
        private val saveMapper: UserCategorySaveDtoMapper,
) {

    @PostMapping
    fun save(@RequestBody dto: UserCategorySaveDto): ResponseEntity<ResponseModel<Long>> {
        val entity = saveMapper.mapToEntity(dto)
        val result = service.save(entity)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Categoria criada com sucesso!")
                .build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: UserCategorySaveDto): ResponseEntity<ResponseModel<Long>> {
        val entity = saveMapper.mapToEntity(dto)
        val result = service.update(id, entity)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Categoria atualizada com sucesso!")
                .build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<ResponseModel<List<UserCategoryDto>>> {
        val result = service.findAll()
        return ResponseModel(mapper.mapToDtoList(result))
                .withHttpStatus(HttpStatus.OK)
                .withMessage("Categorias listadas com sucesso!")
                .build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ResponseModel<Long>> {
        val result = service.delete(id)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Categoria deletada com sucesso!")
                .build()
    }
}