package br.com.taskmanager.service.priority

import br.com.taskmanager.data.priority.PriorityEntity
import br.com.taskmanager.data.priority.PriorityRepository
import org.springframework.stereotype.Service

@Service
class PriorityServiceImpl(
        private val repository: PriorityRepository,
) : PriorityService {

    override fun findAll(): List<PriorityEntity> {
        return repository.findAll()
    }
}