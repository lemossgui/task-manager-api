package br.com.taskmanager.controller.category

import br.com.taskmanager.response.ResponseModel
import br.com.taskmanager.service.category.CategoryService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api
@RestController
@RequestMapping("/category")
class CategoryController(
        private val service: CategoryService,
        private val mapper: CategoryDtoMapper,
        private val saveMapper: CategorySaveDtoMapper,
) {

    @PostMapping
    fun save(@RequestBody dto: CategorySaveDto): ResponseEntity<ResponseModel<Long>> {
        val entity = saveMapper.mapToEntity(dto)
        val result = service.save(entity)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Categoria criada com sucesso!")
                .build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: CategorySaveDto): ResponseEntity<ResponseModel<Long>> {
        val entity = saveMapper.mapToEntity(dto)
        val result = service.update(id, entity)
        return ResponseModel(result)
                .withHttpStatus(HttpStatus.CREATED)
                .withMessage("Categoria atualizada com sucesso!")
                .build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<ResponseModel<List<CategoryDto>>> {
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